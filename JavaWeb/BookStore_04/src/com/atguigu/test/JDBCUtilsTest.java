package com.atguigu.test;

import com.atguigu.util.JDBCUtils;

import java.sql.Connection;

public class JDBCUtilsTest {
    public static void main(String[] args) {
        Connection connection = JDBCUtils.getConnection();
        System.out.println(connection);
        JDBCUtils.releaseConnection(connection);
    }
}
