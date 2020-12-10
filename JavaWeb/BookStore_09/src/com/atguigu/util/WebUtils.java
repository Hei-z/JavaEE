package com.atguigu.util;

import com.atguigu.bean.Cart;
import com.atguigu.bean.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class WebUtils {
    /**
     * 获取请求参数，然后将请求参数设置给对象
     *
     * @param request 请求对象
     * @param t       需要设置属性值的对象
     * @param <T>     对象的类型
     * @return 设置好参数的类型
     */
    public static <T> T param2bean(HttpServletRequest request, T t) {
        // 如果是数组，那么如果设置给非数组变量时，只取第一个元素值
        Map<String, String[]> parameterMap = request.getParameterMap();
        try {
            BeanUtils.populate(t, parameterMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 自己实现将请求参数封装成对象
     */
    public static <T> T myParam2bean(HttpServletRequest request, T t) {
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String parameter = request.getParameter(field.getName());
            if (parameter != null) {
                Object value = parameter;
                Class<?> type = field.getType();
                // 参数转化
                if (type == Integer.class) {
                    value = Integer.parseInt(parameter);
                }
                // 调用setter方法，设置属性值
                try {
                    field.set(t, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return t;
    }

    public static Cart getCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        // 从session中获取session
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    /**
     * 获取登录的用户
     */
    public static User getLoginUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (User) session.getAttribute("user");
    }

}
