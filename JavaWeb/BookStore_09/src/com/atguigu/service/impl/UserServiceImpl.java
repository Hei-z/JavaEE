package com.atguigu.service.impl;

import com.atguigu.bean.User;
import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public User login(User user) {
        return userDao.getUserByUsernameAndPwd(user);
    }

    @Override
    public boolean register(User user) {
        return userDao.insertUser(user);
    }

    /**
     * 检查某个用户是否存在
     */
    @Override
    public boolean checkUser(User user) {
        return userDao.getUserByUserName(user) == null;
    }
}
