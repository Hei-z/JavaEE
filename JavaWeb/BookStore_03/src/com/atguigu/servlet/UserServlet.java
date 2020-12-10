package com.atguigu.servlet;

import com.atguigu.bean.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理User相关的请求，比如login 和 register
 */
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        WebUtils.param2bean(request, user);
        System.out.println(user);
        user = userService.login(user);
        if (user == null) {
            request.setAttribute("msg", "用户名密码错误");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/pages/user/login_success.jsp");
        }
    }

    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        WebUtils.myParam2bean(request, user);
        boolean res = userService.register(user);
        System.out.println(user);
        if (res) {
            response.sendRedirect(request.getContextPath() + "/pages/user/regist_success.jsp");
        } else {
            request.setAttribute("msg", "用户已存在");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        }
    }
}
