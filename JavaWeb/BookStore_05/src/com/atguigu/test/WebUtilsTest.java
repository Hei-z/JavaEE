package com.atguigu.test;

import com.atguigu.bean.User;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class WebUtilsTest {
    @Test
    public void test1() throws NoSuchFieldException, IllegalAccessException {
        User user = new User();
        Field id = user.getClass().getDeclaredField("id");
        id.setAccessible(true);
        id.set(user, 10);
        System.out.println(user);
        Class<?> type = id.getType();
        System.out.println(type);
        System.out.println(Integer.class == type);
    }

    @Test
    public void test2() throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        User user = new User();
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("username", "zd");
        map.put("xxx", "aaa");
        System.out.println(user);
        BeanUtils.populate(user, map);
        System.out.println(user);
    }
}
