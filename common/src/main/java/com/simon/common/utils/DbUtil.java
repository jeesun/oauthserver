package com.simon.common.utils;

import com.google.common.base.CaseFormat;
import com.simon.common.code.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 数据库工具类
 *
 * @author simon
 * @date 2018-10-06
 **/

@Slf4j
public class DbUtil {
    /**
     * 获取数据库连接
     *
     * @param driver
     * @param url
     * @param user
     * @param pwd
     * @return
     */
    public static Connection getConnection(String driver, String url, String user, String pwd) {
        Connection con = null;
        //注册驱动
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            log.error("获取数据连接失败，{}", e.getMessage());
        }
        return con;
    }

    /**
     * 获取数据表个数
     *
     * @param driver
     * @param url
     * @param user
     * @param pwd
     * @return
     * @throws Exception
     */
    public static int getTableCount(String driver, String url, String user, String pwd) throws Exception {
        int tableCount = 0;
        Connection con = null;
        //注册驱动
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pwd);
        } catch (ClassNotFoundException | SQLException e) {
            log.error("获取数据连接失败，{}", e.getMessage());
        }
        int dbType = 0;
        try {
            dbType = getDataBaseType(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "";
        PreparedStatement ps;
        ResultSet rs;

        //查询表标注
        if (dbType == DbType.MYSQL) {
            sql = "SELECT COUNT(*) FROM information_schema.TABLES WHERE table_schema='" + con.getCatalog() + "'";
        } else if (dbType == DbType.POSTGRESQL) {
            sql = "SELECT COUNT(*) FROM pg_class C";
        } else if (dbType == DbType.ORACLE) {
            sql = "select COUNT(*) from all_tab_comments";
        } else {
            throw new Exception("暂不支持其他数据库");
        }
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {
            tableCount = rs.getInt(1);
        }
        return tableCount;
    }

    /**
     * 获取表信息
     *
     * @param driver
     * @param url
     * @param user
     * @param pwd
     * @return
     * @throws Exception
     */
    public static List<TableInfo> getTables(String driver, String url, String user, String pwd) throws Exception {
        return getTables(driver, url, user, pwd, null, null);
    }

    /**
     * 根据条件查询表信息
     *
     * @param driver
     * @param url
     * @param user
     * @param pwd
     * @param tableNameKey
     * @param tableCommentKey
     * @return
     * @throws Exception
     */
    public static List<TableInfo> getTables(String driver, String url, String user, String pwd, String tableNameKey, String tableCommentKey) throws Exception {
        List<TableInfo> tableInfoList = new ArrayList<>();
        Connection con = null;
        //注册驱动
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pwd);
        } catch (ClassNotFoundException | SQLException e) {
            log.error("获取数据连接失败，{}", e.getMessage());
        }
        int dbType = 0;
        try {
            dbType = getDataBaseType(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "";
        PreparedStatement ps;
        ResultSet rs;

        //查询表标注
        if (dbType == DbType.MYSQL) {
            sql = "SELECT TABLE_NAME,TABLE_COMMENT FROM information_schema.TABLES WHERE table_schema='" + con.getCatalog() + "'";
        } else if (dbType == DbType.POSTGRESQL) {
            sql = "SELECT relname AS TABLE_NAME, CAST(obj_description(relfilenode, 'pg_class') AS VARCHAR) AS TABLE_COMMENT FROM pg_class C";
        } else if (dbType == DbType.ORACLE) {
            sql = "select TABLE_NAME,COMMENTS from all_tab_comments";
        } else {
            throw new Exception("暂不支持其他数据库");
        }
        if (StringUtils.isNotEmpty(tableNameKey)) {
            if (sql.contains("WHERE")) {
                sql += " AND TABLE_NAME LIKE '%" + tableNameKey + "%'";
            } else {
                sql += " WHERE TABLE_NAME LIKE '%" + tableNameKey + "%'";
            }
        }
        if (StringUtils.isNotEmpty(tableCommentKey)) {
            if (sql.contains("WHERE")) {
                sql += " AND TABLE_COMMENT LIKE '%" + tableCommentKey + "%'";
            } else {
                sql += " WHERE TABLE_COMMENT LIKE '%" + tableCommentKey + "%'";
            }
        }
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            String tableComment = rs.getString("TABLE_COMMENT");
            String tableName = rs.getString("TABLE_NAME");
            String entityName = tableName;
            if (entityName.startsWith("t_")) {
                entityName = entityName.substring(2);
            }
            entityName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, entityName.toLowerCase());
            /*if(StringUtils.isEmpty(tableComment)){
                tableComment = tableName;
            }else{
                if(tableComment.contains("表")&&tableComment.lastIndexOf("表") == (tableComment.length() - 1)){
                    tableComment = tableComment.substring(0, tableComment.length() - 1);
                }
            }*/
            TableInfo tableInfo = new TableInfo();
            tableInfo.setTableName(tableName);
            tableInfo.setTableComment(tableComment);
            tableInfo.setEntityName(entityName);
            tableInfoList.add(tableInfo);
        }
        return tableInfoList;
    }

    /**
     * 获取数据库类型
     *
     * @param connection
     * @return
     * @throws SQLException
     */
    public static int getDataBaseType(Connection connection) throws SQLException {
        String driverName = connection.getMetaData().getDriverName().toLowerCase();
        log.info(driverName);
        //通过driverName是否包含关键字判断
        if (driverName.contains("mysql")) {
            return 1;
        } else if (driverName.contains("postgresql")) {
            return 2;
        } else if (driverName.contains("oracle")) {
            return 3;
        }
        return -1;
    }

    /**
     * 获取表对应的实体属性，用于代码生成
     *
     * @param con
     * @param tableName
     * @param basePackage
     * @param modelName
     * @return
     * @throws Exception
     */
    public static EntityDataModel getEntityModel(Connection con, String tableName, String basePackage, String modelName)
            throws Exception {
        int dbType = getDataBaseType(con);
        EntityDataModel dataModel = new EntityDataModel();

        String sql = "";
        PreparedStatement ps;
        ResultSet rs;

        //查询表标注
        if (dbType == DbType.MYSQL) {
            sql = "SELECT TABLE_NAME,TABLE_COMMENT FROM information_schema.TABLES WHERE table_schema='" + con.getCatalog() + "' AND TABLE_NAME='" + tableName + "'";
        } else if (dbType == DbType.POSTGRESQL) {
            sql = "SELECT relname AS TABLE_NAME, CAST(obj_description(relfilenode, 'pg_class') AS VARCHAR) AS TABLE_COMMENT FROM pg_class C WHERE relname = '" + tableName + "'";
        } else if (dbType == DbType.ORACLE) {
            sql = "select TABLE_NAME,COMMENTS AS TABLE_COMMENT from all_tab_comments WHERE table_name='" + tableName.toUpperCase() + "'";
        } else {
            throw new Exception("暂不支持其他数据库");
        }
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            String tableComment = rs.getString("TABLE_COMMENT");
            if (StringUtils.isEmpty(tableComment)) {
                tableComment = tableName;
            } else {
                if (tableComment.contains("表") && tableComment.lastIndexOf("表") == (tableComment.length() - 1)) {
                    tableComment = tableComment.substring(0, tableComment.length() - 1);
                }
            }
            dataModel.setTableComment(tableComment);
        }


        //查询表属性,格式化生成实体所需属性
        if (dbType == DbType.MYSQL) {
            //log.info(con.getCatalog());
            sql = "SELECT table_name, column_name, column_comment, column_type, data_type, column_default, is_nullable "
                    + "FROM INFORMATION_SCHEMA.COLUMNS " + "WHERE table_name = '" + tableName + "' AND table_schema = '" + con.getCatalog() + "'";
        } else if (dbType == DbType.POSTGRESQL) {
            log.info(con.getCatalog());
            sql = "SELECT delta.table_name, delta.column_name, alb.column_comment, alb.column_type, delta.data_type, delta.column_default, delta.is_nullable FROM information_schema.COLUMNS AS delta, ( SELECT C .relname AS table_name, A.attname AS column_name, col_description ( A.attrelid, A.attnum ) AS column_comment, format_type ( A.atttypid, A.atttypmod ) AS column_type, A.attnotnull AS NOTNULL FROM pg_class AS C, pg_attribute AS A WHERE C.relname = '" + tableName + "' AND A.attrelid = C.oid AND A.attnum > 0 ) AS alb WHERE table_schema = 'public' AND delta.TABLE_NAME = '" + tableName + "' AND delta.COLUMN_NAME = alb.column_name";
        } else if (dbType == DbType.ORACLE) {
            log.info(con.getCatalog());
            sql = "SELECT\n" +
                    "atc.table_name,\n" +
                    "atc.column_name,\n" +
                    "acc.COMMENTS AS column_comment,\n" +
                    "atc.data_length AS column_type,\n" +
                    "atc.data_type AS data_type,\n" +
                    "atc.data_default AS column_default,\n" +
                    "atc.NULLABLE AS is_nullable \n" +
                    "FROM\n" +
                    "all_tab_columns atc\n" +
                    "FULL JOIN ( SELECT column_name, COMMENTS FROM all_col_comments WHERE Table_Name = 'USERS' ) acc ON atc.column_name = acc.column_name \n" +
                    "WHERE\n" +
                    "atc.table_name = '" + tableName.toUpperCase() + "'";
        } else {
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

            if (StringUtils.isEmpty(comment)) {
                comment = name;
            }

            String propertyType = null;
            if (dbType == DbType.MYSQL) {
                propertyType = TypeTranslator.translateMySQL(columnType, dataType);
            } else if (dbType == DbType.POSTGRESQL) {
                propertyType = TypeTranslator.translatePostgreSQL(columnType, dataType);
            } else if (dbType == DbType.ORACLE) {
                propertyType = TypeTranslator.translateOracle(columnType, dataType);
            } else {
                throw new Exception("暂不支持其他数据库");
            }

            String annotation = "";
            if ("id".equalsIgnoreCase(name)) {
                if ("Long".equalsIgnoreCase(propertyType)) {
                    //fastjson转换成map时，将Long转换成String，保证前端不丢失精度
                    annotation = "@JSONField(serializeUsing = ToStringSerializer.class)\n" +
                            "    @Id\n" +
                            "    @Column(name = \"id\")\n" +
                            "    @KeySql(genId = SnowflakeGenId.class)\n" +
                            "    @GeneratedValue(generator = \"sequenceId\")\n" +
                            "    @GenericGenerator(name = \"sequenceId\", strategy = \"" + CodeGenerator.BASE_PACKAGE + ".common.utils.snowflake.SequenceId\")";
                } else if ("String".equalsIgnoreCase(propertyType)) {
                    annotation = "@Id\n" +
                            "    @Column(name = \"id\")\n" +
                            "    @KeySql(genId = UUIdGenId.class)\n" +
                            "    @GeneratedValue(generator = \"uuid\")\n" +
                            "    @GenericGenerator(name = \"uuid\", strategy = \"" + CodeGenerator.BASE_PACKAGE + ".common.utils.UuidGenerator\")";
                } else if ("Integer".equalsIgnoreCase(propertyType)) {
                    annotation = "@Id\n" +
                            "    @Column(name = \"id\")\n" +
                            "    @GeneratedValue(strategy = GenerationType.IDENTITY)";
                } else {
                    annotation = "@Id\n" +
                            "    @Column(name = \"id\")\n" +
                            "    @GeneratedValue(strategy = GenerationType.IDENTITY)";
                }
            } else {
                if ("Date".equalsIgnoreCase(propertyType)) {
                    annotation = "@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_DATETIME, timezone = AppConfig.DATE_TIMEZONE)\n" +
                            "    @JSONField(format = AppConfig.DATE_PATTERN_DATETIME)\n";
                } else if ("Long".equalsIgnoreCase(propertyType)) {
                    //fastjson转换成map时，将Long转换成String，保证前端不丢失精度
                    annotation = "@JSONField(serializeUsing = ToStringSerializer.class)\n";
                } else if ("LocalDateTime".equalsIgnoreCase(propertyType)) {
                    annotation = "@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_DATETIME, timezone = AppConfig.DATE_TIMEZONE)\n" +
                            "    @JSONField(format = AppConfig.DATE_PATTERN_DATETIME)\n";
                } else if ("LocalDate".equalsIgnoreCase(propertyType)) {
                    annotation = "@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_DAY, timezone = AppConfig.DATE_TIMEZONE)\n" +
                            "    @JSONField(format = AppConfig.DATE_PATTERN_DAY)\n";
                } else if ("LocalTime".equalsIgnoreCase(propertyType)) {
                    annotation = "@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_PATTERN_TIME, timezone = AppConfig.DATE_TIMEZONE)\n" +
                            "    @JSONField(format = AppConfig.DATE_PATTERN_TIME)\n";
                }

                if (!"".equals(annotation)) {
                    annotation += "    ";
                }

                annotation += "@ApiModelProperty(value = \"" + comment + "\")\n";

                String charPattern = "char\\(\\d+\\)";
                if (Pattern.matches(charPattern, columnType)) {
                    //MySQL char(4)
                    if ("NO".equalsIgnoreCase(isNullable)) {
                        annotation += "    @Column(name = \"" + name + "\", nullable = false, columnDefinition =\"" + columnType + "\")";
                    } else {
                        annotation += "    @Column(name = \"" + name + "\", columnDefinition =\"" + columnType + "\")";
                    }
                } else if ("text".equalsIgnoreCase(columnType)) {
                    //MySQL text
                    if ("No".equalsIgnoreCase(isNullable)) {
                        annotation += "    @Column(name = \"" + name + "\", nullable = false, columnDefinition = \"TEXT\")";
                    } else {
                        annotation += "    @Column(name = \"" + name + "\", columnDefinition = \"TEXT\")";
                    }
                } else if ("longtext".equalsIgnoreCase(columnType)) {
                    //MySQL text
                    if ("No".equalsIgnoreCase(isNullable)) {
                        annotation += "    @Column(name = \"" + name + "\", nullable = false, columnDefinition = \"LONGTEXT\")";
                    } else {
                        annotation += "    @Column(name = \"" + name + "\", columnDefinition = \"LONGTEXT\")";
                    }
                } else {
                    if ("NO".equalsIgnoreCase(isNullable)) {
                        annotation += "    @Column(name = \"" + name + "\", nullable = false)";
                    } else {
                        annotation += "    @Column(name = \"" + name + "\")";
                    }
                }
            }

            col.setName(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name.toLowerCase()));
            col.setType(propertyType);
            col.setAnnotation(annotation);
            col.setComment(comment);
            columns.add(col);
        }

        dataModel.setBasePackage(basePackage);
        //dataModel.setEntityPackage(basePackage);
        if (StringUtils.isNotEmpty(modelName)) {
            dataModel.setEntityName(modelName);
        } else {
            dataModel.setEntityName(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase()));
        }
        dataModel.setTableName(tableName);
        dataModel.setColumns(columns);
        return dataModel;
    }

    /*public static void main(String[] args) throws Exception {
        log.info(JSON.toJSONString(getTables("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1:3306/thymelte?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&autoReconnect=true&useSSL=false", "root", "19941017", null, null)));
        log.info("" + getTableCount("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1:3306/thymelte?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&autoReconnect=true&useSSL=false", "root", "19941017"));
    }*/
}
