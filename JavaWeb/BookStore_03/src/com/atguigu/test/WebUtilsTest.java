package com.atguigu.test;

import com.atguigu.bean.User;
import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            if("id".equals(pd.getName())) {
                Method method = pd.getWriteMethod();
                User user = new User();
                method.invoke(user,1);
            }
        }
    }
}
