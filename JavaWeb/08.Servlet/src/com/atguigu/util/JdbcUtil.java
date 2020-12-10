package com.atguigu.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {
    private static Properties properties = new Properties();

    static {
        InputStream is = JdbcUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            properties.load(is);
            Class.forName(properties.getProperty("jdbcDriver"));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(properties.getProperty("url"), properties);
    }
}
