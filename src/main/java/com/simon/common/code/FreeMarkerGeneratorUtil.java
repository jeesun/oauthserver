package com.simon.common.code;

/**
 * @author simon
 * @create 2018-08-07 21:10
 **/

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成工具类
 * Created by wangqichang on 2018/5/30.
 */
@Slf4j
public class FreeMarkerGeneratorUtil {

    /**
     * 仅生成dao层
     *
     * @param driver
     * @param url
     * @param user
     * @param pwd
     */
    public static void generatorMvcCode(String driver, String url, String user, String pwd, String tableName,
                                        String modelName, String basePackage) {

        Connection con = null;
        //注册驱动
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            log.error("获取数据连接失败，{}", e.getMessage());
            return;
        }

        //获取模板路径
        String templatePath = CodeGenerator.TEMPLATE_FILE_PATH;
        //log.info("当前模板路径为：{}", templatePath);

        try {

            String entityDir = null;
            //根据实体包名创建目录
            File[] ls = FileUtil.ls(CodeGenerator.PROJECT_PATH);
            for (File f: ls) {
                String currModule = f.toString();
                boolean matches = currModule.matches("(.*?pojo.*?)|(.*?domain.*?)|(.*?entity.*?)");
                if (f.isDirectory()&&matches){
                    entityDir = f.toString()+ CodeGenerator.JAVA_PATH + "/" + basePackage.replace(".", "/");
                    break;
                }
            }
            if (StrUtil.isBlank(entityDir)){
                entityDir = CodeGenerator.PROJECT_PATH + CodeGenerator.JAVA_PATH + "/" + basePackage.replace(".", "/");
            }
            if (!FileUtil.exist(entityDir)) {
                FileUtil.mkdir(entityDir);
                log.info("创建目录：{} 成功！ ",entityDir);
            }
            EntityDataModel entityModel = getEntityModel(con, tableName, basePackage, modelName);
            //生成每个表实体
            generateCode(entityModel, templatePath, "entity.ftl", entityDir);

        } catch (Exception e) {
            log.error("代码生成出错 {}", e.getMessage());
        }

    }

    private static EntityDataModel getEntityModel(Connection con, String tableName, String basePackage, String modelName)
            throws Exception {
        EntityDataModel dataModel = new EntityDataModel();

        String sql = "";
        PreparedStatement ps;
        ResultSet rs;

        //查询表标注
        if(getDataBaseType(con) == 1){
            sql = "SELECT TABLE_NAME,TABLE_COMMENT FROM information_schema.TABLES WHERE table_schema='" + con.getCatalog() + "' AND TABLE_NAME='" + tableName + "'";
        }else if(getDataBaseType(con) == 2){
            sql = "SELECT relname AS TABLE_NAME, CAST(obj_description(relfilenode, 'pg_class') AS VARCHAR) AS TABLE_COMMENT FROM pg_class C WHERE relname = '" + tableName + "'";
        }else{
            throw new Exception("暂不支持其他数据库");
        }
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()){
            String tableComment = rs.getString("TABLE_COMMENT");
            if(StringUtils.isEmpty(tableComment)){
                tableComment = tableName;
            }else{
                if(tableComment.lastIndexOf("表") == (tableComment.length() - 1)){
                    tableComment = tableComment.substring(0, tableComment.length() - 1);
                }
            }
            dataModel.setTableComment(tableComment);
        }


        //查询表属性,格式化生成实体所需属性
        if(getDataBaseType(con) == 1){
            //log.info(con.getCatalog());
            sql = "SELECT table_name, column_name, column_comment, column_type, data_type, column_default, is_nullable "
                    + "FROM INFORMATION_SCHEMA.COLUMNS " + "WHERE table_name = '" + tableName + "' AND table_schema = '" + con.getCatalog() + "'";
        }else if(getDataBaseType(con) == 2){
            log.info(con.getCatalog());
            sql = "SELECT delta.table_name, delta.column_name, alb.column_comment, alb.column_type, delta.data_type, delta.column_default, delta.is_nullable FROM information_schema.COLUMNS AS delta, ( SELECT C .relname AS table_name, A.attname AS column_name, col_description ( A.attrelid, A.attnum ) AS column_comment, format_type ( A.atttypid, A.atttypmod ) AS column_type, A.attnotnull AS NOTNULL FROM pg_class AS C, pg_attribute AS A WHERE C.relname = '" + tableName + "' AND A.attrelid = C.oid AND A.attnum > 0 ) AS alb WHERE table_schema = 'public' AND delta.TABLE_NAME = '" + tableName + "' AND delta.COLUMN_NAME = alb.column_name";
        }else{
            throw new Exception("暂不支持其他数据库");
        }


        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

        List<Column> columns = new ArrayList<>();
        while (rs.next()) {
            Column col = new Column();
            String name = rs.getString("column_name");
            String columnType = rs.getString("column_type");
            String dataType = rs.getString("data_type");
            String comment = rs.getString("column_comment");
            String isNullable = rs.getString("is_nullable");

            if(StringUtils.isEmpty(comment)){
                comment = name;
            }

            String propertyType = null;
            if(getDataBaseType(con) == 1){
                propertyType = TypeTranslator.translateMySQL(columnType, dataType);
            }else if(getDataBaseType(con) == 2){
                propertyType = TypeTranslator.translatePostgreSQL(columnType, dataType);
            }else{
                throw new Exception("暂不支持其他数据库");
            }

            String annotation = null;
            if ("id".equalsIgnoreCase(name)) {
                if (propertyType.equalsIgnoreCase("Long")) {
                    annotation = "@Id\n" +
                            "    @KeySql(genId = SnowflakeGenId.class)\n" +
                            "    @GeneratedValue(generator = \"sequenceId\")\n" +
                            "    @GenericGenerator(name = \"sequenceId\", strategy = \"com.simon.common.utils.snowflake.SequenceId\")";
                }else if(propertyType.equalsIgnoreCase("String")){
                    annotation = "@Id\n" +
                            "    @KeySql(genId = UUIdGenId.class)\n" +
                            "    @GeneratedValue(generator = \"uuid\")\n" +
                            "    @GenericGenerator(name = \"uuid\", strategy = \"com.simon.common.utils.UuidGenerator\")";
                }else if(propertyType.equalsIgnoreCase("Integer")){
                    annotation = "@Id\n" +
                            "    @GeneratedValue(strategy = GenerationType.IDENTITY)";
                }else{
                    annotation = "@Id\n" +
                            "    @GeneratedValue(strategy = GenerationType.IDENTITY)";
                }
            }else{
                if(isNullable.equalsIgnoreCase("NO")){
                    annotation = "@ApiModelProperty(value = \"" + comment + "\")\n" +
                            "    @Column(name = \"" + name + "\", nullable = false)";
                }else{
                    annotation = "@ApiModelProperty(value = \"" + comment + "\")\n" +
                            "    @Column(name = \"" + name + "\")";
                }
            }

            col.setName(StrUtil.toCamelCase(name));
            col.setType(propertyType);
            col.setAnnotation(annotation);
            col.setComment(comment);
            columns.add(col);
        }

        dataModel.setEntityPackage(basePackage);
        if (StringUtils.isNotEmpty(modelName)) {
            dataModel.setEntityName(modelName);
        } else {
            dataModel.setEntityName(StrUtil.upperFirst(StrUtil.toCamelCase(tableName)));
        }
        dataModel.setTableName(tableName);
        dataModel.setColumns(columns);
        return dataModel;
    }

    private static void generateCode(EntityDataModel dataModel, String templatePath, String templateName, String outDir)
            throws IOException, TemplateException {

        String file = outDir +"/"+ dataModel.getEntityName() + dataModel.getFileSuffix();
        if (FileUtil.exist(file)){
//            log.info("文件：{}已存在，如需覆盖请先对该文件进行", file);
//            return;
            FileUtil.del(file);
        }
        //获取模板对象
        Configuration conf = new Configuration();
        File temp = new File(templatePath);
        conf.setDirectoryForTemplateLoading(temp);
        Template template = conf.getTemplate(templateName);
        Writer writer = new FileWriter(file);
        //填充数据模型
        template.process(dataModel, writer);
        writer.close();
        log.info("代码生成成功，文件位置：{}",file);
    }

    public static int getDataBaseType(Connection connection) throws SQLException {
        String driverName = connection.getMetaData().getDriverName().toLowerCase();
        //log.info(driverName);
        //通过driverName是否包含关键字判断
        if (driverName.contains("mysql")) {
            return 1;
        } else if (driverName.contains("postgresql")) {
            return 2;
        }
        return -1;
    }
}