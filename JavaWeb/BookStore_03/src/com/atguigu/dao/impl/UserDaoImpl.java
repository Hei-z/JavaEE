package com.atguigu.dao.impl;

import com.atguigu.bean.User;
import com.atguigu.dao.BaseDao;
import com.atguigu.dao.UserDao;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    @Override
    public User getUserByUsernameAndPwd(User user) {
        String sql = "select * from bs_user where username = ? and password = ?";
        return getBean(sql, user.getUsername(), user.getPassword());
    }

    @Override
    public boolean insertUser(User user) {
        String sql = "insert into bs_user(username, password, email) values(?,?,?)";
        int update = update(sql, user.getUsername(), user.getPassword(), user.getEmail());
        return update > 0;
    }
}
