package com.atguigu.test;

import com.atguigu.bean.User;
import com.atguigu.dao.impl.UserDaoImpl;

public class BaseDaoTest {
    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl();
        String sql = "select * from bs_user where id = ?";
        User user = userDao.getBean(sql, 1);
        System.out.println(user);
    }
}
