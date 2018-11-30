package com.simon.common.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.base.CaseFormat;
import com.simon.common.code.DbType;
import com.simon.common.code.FreeMarkerGeneratorUtil;
import com.simon.common.code.TableInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库工具类
 *
 * @author simon
 * @date 2018-10-06
 **/

@Slf4j
public class DbUtil {
    public static int getTableCount(String driver, String url, String user, String pwd) throws Exception{
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
            dbType = FreeMarkerGeneratorUtil.getDataBaseType(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "";
        PreparedStatement ps;
        ResultSet rs;

        //查询表标注
        if(dbType == DbType.MYSQL){
            sql = "SELECT COUNT(*) FROM information_schema.TABLES WHERE table_schema='" + con.getCatalog() + "'";
        }else if(dbType == DbType.POSTGRESQL){
            sql = "SELECT COUNT(*) FROM pg_class C";
        }else if(dbType == DbType.ORACLE){
            sql = "select COUNT(*) from all_tab_comments";
        }else{
            throw new Exception("暂不支持其他数据库");
        }
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()){
            tableCount = rs.getInt(1);
        }
        return tableCount;
    }

    public static List<TableInfo> getTables(String driver, String url, String user, String pwd) throws Exception{
        return getTables(driver, url, user, pwd, null, null);
    }

    public static List<TableInfo> getTables(String driver, String url, String user, String pwd, String tableNameKey, String tableCommentKey) throws Exception{
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
            dbType = FreeMarkerGeneratorUtil.getDataBaseType(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "";
        PreparedStatement ps;
        ResultSet rs;

        //查询表标注
        if(dbType == DbType.MYSQL){
            sql = "SELECT TABLE_NAME,TABLE_COMMENT FROM information_schema.TABLES WHERE table_schema='" + con.getCatalog() + "'";
        }else if(dbType == DbType.POSTGRESQL){
            sql = "SELECT relname AS TABLE_NAME, CAST(obj_description(relfilenode, 'pg_class') AS VARCHAR) AS TABLE_COMMENT FROM pg_class C";
        }else if(dbType == DbType.ORACLE){
            sql = "select TABLE_NAME,COMMENTS from all_tab_comments";
        }else{
            throw new Exception("暂不支持其他数据库");
        }
        if(StringUtils.isNotEmpty(tableNameKey)){
            if(sql.contains("WHERE")){
                sql += " AND TABLE_NAME LIKE '%" + tableNameKey + "%'";
            }else{
                sql += " WHERE TABLE_NAME LIKE '%" + tableNameKey + "%'";
            }
        }
        if(StringUtils.isNotEmpty(tableCommentKey)){
            if(sql.contains("WHERE")){
                sql += " AND TABLE_COMMENT LIKE '%" + tableCommentKey + "%'";
            }else{
                sql += " WHERE TABLE_COMMENT LIKE '%" + tableCommentKey + "%'";
            }
        }
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()){
            String tableComment = rs.getString("TABLE_COMMENT");
            String tableName = rs.getString("TABLE_NAME");
            String entityName = tableName;
            if (entityName.startsWith("t_")){
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

    public static void main(String[] args) throws Exception {
        log.info(JSON.toJSONString(getTables("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1:3306/thymelte?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&autoReconnect=true&useSSL=false", "root", "19941017", null, null)));
        log.info("" + getTableCount("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1:3306/thymelte?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&autoReconnect=true&useSSL=false", "root", "19941017"));
    }
}
