package com.simon;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OauthserverApplicationTests {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	public void contextLoads() {
	}

	@Test
	public void tableTest() throws SQLException {
		getTables(jdbcTemplate.getDataSource().getConnection());
	}

	public static String convertDatabaseCharsetType(String in, String type) {
		String dbUser;
		if (in != null) {
			if (type.equals("oracle")) {
				dbUser = in.toUpperCase();
			} else if (type.equals("postgresql")) {
				dbUser = "public";
			} else if (type.equals("mysql")) {
				dbUser = null;
			} else if (type.equals("mssqlserver")) {
				dbUser = null;
			} else if (type.equals("db2")) {
				dbUser = in.toUpperCase();
			} else {
				dbUser = in;
			}
		} else {
			dbUser = "public";
		}
		return dbUser;
	}


	private static void getTables(Connection conn) throws SQLException {
		DatabaseMetaData dbMetData = conn.getMetaData();
		// mysql convertDatabaseCharsetType null
		ResultSet rs = dbMetData.getTables(null,
				convertDatabaseCharsetType("root", "mysql"), null,
				new String[] { "TABLE", "VIEW" });

		while (rs.next()) {
			if (rs.getString(4) != null
					&& (rs.getString(4).equalsIgnoreCase("TABLE") || rs
					.getString(4).equalsIgnoreCase("VIEW"))) {
				String tableName = rs.getString(3).toLowerCase();
				System.out.print(tableName + "\t");
				// 根据表名提前表里面信息：
				ResultSet colRet = dbMetData.getColumns(null, "%", tableName,
						"%");
				while (colRet.next()) {
					String columnName = colRet.getString("COLUMN_NAME");
					String columnType = colRet.getString("TYPE_NAME");
					int datasize = colRet.getInt("COLUMN_SIZE");
					int digits = colRet.getInt("DECIMAL_DIGITS");
					int nullable = colRet.getInt("NULLABLE");
					// System.out.println(columnName + " " + columnType + " "+
					// datasize + " " + digits + " " + nullable);
				}

			}
		}
		System.out.println();

		// resultSet数据下标从1开始 ResultSet tableRet =
		//conn.getMetaData().getTables(null, null, "%", new String[] { "TABLE" });
		//while (tableRet.next()) {
		//	System.out.print(tableRet.getString(3) + "\t");
		//}
		//System.out.println();

	}
}
