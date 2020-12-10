package com.atguigu.test;

import com.atguigu.bean.User;
import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import org.junit.Test;

public class UserDaoTest {
    @Test
    public void test() {
        UserDao userDao = new UserDaoImpl();
        User foo = userDao.getUserByUsernameAndPwd(new User(null, "foo", "123", ""));
        System.out.println(foo);
        userDao.insertUser(new User(null, "admin", "abc", "123@qq.com"));

    }
}
