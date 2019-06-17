package com.simon.common.code;

import com.google.common.base.CaseFormat;
import com.simon.common.exception.BusinessException;
import com.simon.common.utils.DbUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.sql.Connection;

/**
 * 代码生成器工具类
 * @author simon
 * @date 2018-08-07
 **/
@Slf4j
public class PageGeneratorUtil {

    /**
     *
     * @param con
     * @param tableName
     * @param modelName
     * @param basePackage
     */
    public static void generatorPage(Connection con, String tableName,
                                     String modelName, String basePackage, EntityDataModel entityModel) {

        //获取模板路径
        String templatePath = CodeGenerator.TEMPLATE_FILE_PATH;
        //log.info("当前模板路径为：{}", templatePath);

        try {

            String entityDir = null;
            //根据实体包名创建目录
            File projectPathFile = new File(CodeGenerator.PROJECT_PATH);
            if (!projectPathFile.isDirectory()) {
                throw new IOException("Path " + CodeGenerator.PROJECT_PATH + " is not directory!");
            }
            File[] ls = projectPathFile.listFiles();
            for (File f: ls) {
                String currModule = f.toString();
                boolean matches = currModule.matches("(.*?pojo.*?)|(.*?domain.*?)|(.*?entity.*?)");
                if (f.isDirectory()&&matches){
                    entityDir = CodeGenerator.PROJECT_PATH + CodeGenerator.RESOURCES_PATH + "/";
                    break;
                }
            }
            if (StringUtils.isBlank(entityDir)){
                entityDir = CodeGenerator.PROJECT_PATH + CodeGenerator.RESOURCES_PATH + "/";
            }

            File entityDirFile = new File(entityDir);
            if (!entityDirFile.exists()) {
                entityDirFile.mkdirs();
                log.info("创建目录：{} 成功！ ",entityDir);
            }

            //设置id列类型
            for(Column column : entityModel.getColumns()){
                if (column.getName().equalsIgnoreCase("id")) {
                    entityModel.setIdType(column.getType());
                    break;
                }
            }

            //生成页面
            entityModel.setFileSuffix(".html");
            generateCode(entityModel, templatePath, "list.ftl", entityDir);
            generateCode(entityModel, templatePath, "add.ftl", entityDir);
            generateCode(entityModel, templatePath, "edit.ftl", entityDir);
            //生成每个表实体
            entityModel.setFileSuffix(".java");
            generateCode(entityModel, templatePath, "controllerWithPage.ftl", CodeGenerator.PROJECT_PATH + CodeGenerator.JAVA_PATH + "/" + basePackage.replace(".", "/"));
        } catch (Exception e) {
            log.error("代码生成出错 {}", e.getMessage());
        }

    }

    /**
     *
     * @param con
     * @param tableName
     * @param modelName
     * @param basePackage
     */
    public static void generatorPage(Connection con, String tableName,
                                        String modelName, String basePackage) {

        //获取模板路径
        String templatePath = CodeGenerator.TEMPLATE_FILE_PATH;
        //log.info("当前模板路径为：{}", templatePath);

        try {

            String entityDir = null;
            //根据实体包名创建目录
            File projectPathFile = new File(CodeGenerator.PROJECT_PATH);
            if (!projectPathFile.isDirectory()) {
                throw new IOException("Path " + CodeGenerator.PROJECT_PATH + " is not directory!");
            }
            File[] ls = projectPathFile.listFiles();
            for (File f: ls) {
                String currModule = f.toString();
                boolean matches = currModule.matches("(.*?pojo.*?)|(.*?domain.*?)|(.*?entity.*?)");
                if (f.isDirectory()&&matches){
                    entityDir = CodeGenerator.PROJECT_PATH + CodeGenerator.RESOURCES_PATH + "/";
                    break;
                }
            }
            if (StringUtils.isBlank(entityDir)){
                entityDir = CodeGenerator.PROJECT_PATH + CodeGenerator.RESOURCES_PATH + "/";
            }

            File entityDirFile = new File(entityDir);
            if (!entityDirFile.exists()) {
                entityDirFile.mkdirs();
                log.info("创建目录：{} 成功！ ",entityDir);
            }
            EntityDataModel entityModel = DbUtil.getEntityModel(con, tableName, CodeGenerator.BASE_PACKAGE, modelName);

            //设置id列类型
            for(Column column : entityModel.getColumns()){
                if (column.getName().equalsIgnoreCase("id")) {
                    entityModel.setIdType(column.getType());
                    break;
                }
            }

            //生成页面
            entityModel.setFileSuffix(".html");
            //CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, entityModel.getEntityName()) 首字母小写
            generateCode(entityModel, templatePath, "list.ftl", entityDir);
            generateCode(entityModel, templatePath, "add.ftl", entityDir);
            generateCode(entityModel, templatePath, "edit.ftl", entityDir);
            //生成每个表实体
            entityModel.setFileSuffix(".java");
            generateCode(entityModel, templatePath, "controllerWithPage.ftl", CodeGenerator.PROJECT_PATH + CodeGenerator.JAVA_PATH + "/" + basePackage.replace(".", "/"));

            /*String providerPackage = "com.simon.provider";
            //检查provider文件夹是否存在，不存在就创建
            String providerDir = CodeGenerator.PROJECT_PATH + CodeGenerator.JAVA_PATH + "/" + providerPackage.replace(".", "/");
            File providerDirFile = new File(providerDir);
            if (!providerDirFile.exists()) {
                providerDirFile.mkdirs();
                log.info("创建目录：{} 成功！ ",providerDir);
            }

            //生成Provider
            generateCode(entityModel, templatePath, "provider.ftl", providerDir);*/
        } catch (Exception e) {
            log.error("代码生成出错 {}", e.getMessage());
        }

    }

    private static void generateCode(EntityDataModel dataModel, String templatePath, String templateName, String outDir)
            throws IOException, TemplateException {
        dataModel.setBasePackage(CodeGenerator.BASE_PACKAGE);
        dataModel.setModelNameLowerCamel(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, dataModel.getEntityName()));
        dataModel.setModelNameUpperCamel(dataModel.getEntityName());

        File outDirFile = new File(outDir);
        if(!outDirFile.exists()){
            outDirFile.mkdirs();
        }

        String file;
        if(".java".equalsIgnoreCase(dataModel.getFileSuffix())){
            //file = outDir + "/" + dataModel.getEntityName() + "Controller" + dataModel.getFileSuffix();
            if(templateName.contains("controller")){
                file = outDir + "/" + dataModel.getEntityName() + "Controller" + dataModel.getFileSuffix();
            }else if(templateName.contains("provider")){
                file = outDir + "/" + dataModel.getEntityName() + "Provider" + dataModel.getFileSuffix();
            }else{
                throw new BusinessException("不支持的模板");
            }
        }else{
            //file = outDir + "templates/" + CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, dataModel.getEntityName()) + dataModel.getFileSuffix();
            //File outDirTemplateFile = new File(outDir + "templates");
            file = outDir + "templates/vue/" + CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, dataModel.getEntityName()) + "/" + templateName.substring(0, templateName.indexOf(".")) + dataModel.getFileSuffix();
            File outDirTemplateFile = new File(outDir + "templates/vue/" + CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, dataModel.getEntityName()));
            if(!outDirTemplateFile.exists()){
                outDirTemplateFile.mkdirs();
            }
        }

        File targetFile = new File(file);
        if (targetFile.exists()){
            Files.delete(targetFile.toPath());
        }
        //获取模板对象
        Configuration conf = new Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        File temp = new File(templatePath);
        conf.setDirectoryForTemplateLoading(temp);
        Template template = conf.getTemplate(templateName);
        Writer writer = new FileWriter(file);
        //填充数据模型
        template.process(dataModel, writer);
        writer.close();
        log.info("代码生成成功，文件位置：{}",file);
    }
}