package com.atguigu.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtils {
    private static DataSource ds = new ComboPooledDataSource("webDataSource");
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public static Connection getConnection() {
        Connection connection = threadLocal.get();
        // 如果是第一次使用connection对象，那么就从数据库连接池中获取
        if (connection == null) {
            try {
                connection = ds.getConnection();
                // 将connection对象存放到threadLocal中
                threadLocal.set(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void releaseConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // Removes the current thread's value for this thread-local variable.
        // 一定要移除，否则会出错，因为tomcat底层使用了线程池技术，
        // 也就是如果你没有移除，那么下次在线程池中再次取到这个线程时，就会发现
        // 已经有该线程对应的Connection对象了，所以会有问题。
        threadLocal.remove();
    }
}
