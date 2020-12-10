package com.atguigu.test;

import com.atguigu.util.JdbcUtil;

import java.sql.SQLException;

public class JdbcTest {
    public static void main(String[] args) throws SQLException {
        System.out.println(JdbcUtil.getConnection());
    }
}
