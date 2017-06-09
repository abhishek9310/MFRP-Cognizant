package com.mms.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtil {
	private static Connection connection = null;

	public static Connection getConnection() throws ClassNotFoundException,
			SQLException, IOException {
		Properties prop = new Properties();
		InputStream inputStream = DbUtil.class.getClassLoader()
				.getResourceAsStream("/db.properties");
		prop.load(inputStream);
		String driver = prop.getProperty("driver");
		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");
		Class.forName(driver);
		connection = DriverManager.getConnection(url, user, password);
		return connection;
	}
}
