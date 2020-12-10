package com.atguigu.test;

import com.atguigu.bean.User;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class BeanUtilsTest {
    @Test
    public void test1() throws InvocationTargetException, IllegalAccessException {
        User user = new User();
        System.out.println(user);
        // 给对象的某个属性设置值
        BeanUtils.setProperty(user, "username", null);
        System.out.println(user);
        /*
          User(id=null, username=null, password=null, email=null)
          User(id=null, username=admin, password=null, email=null)
         */
    }

    @Test
    public void test2() throws InvocationTargetException, IllegalAccessException {
        User user = new User();
        System.out.println(user);
        // 给对象的某个属性设置值
        /*
         @Data
         @NoArgsConstructor
         @AllArgsConstructor public class User {
         private Integer id;
         private String username;
         private String password;
         private String email;
         }
         */
        // BeanUtils能够自动将String转为Integer
        BeanUtils.setProperty(user, "id", "1");
        System.out.println(user);
        /*
          User(id=null, username=null, password=null, email=null)
          User(id=1, username=null, password=null, email=null)
         */
    }

    @Test
    public void test3() throws InvocationTargetException, IllegalAccessException, IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class, (Class<?>) User.class.getGenericSuperclass());
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            System.out.println(pd.getPropertyType());
        }

    }
}
