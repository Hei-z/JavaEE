package com.atguigu.service;

import com.atguigu.bean.User;

public interface UserService {
    User login(User user);

    boolean register(User user);

    boolean checkUser(User user);
}
