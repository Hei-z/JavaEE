package com.atguigu.dao;

import com.atguigu.bean.User;

public interface UserDao {
    User getUserByUsernameAndPwd(User user);

    boolean insertUser(User user);

    User getUserByUserName(User user);
}
