package com.atguigu.servlet;

import com.atguigu.bean.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.util.WebUtils;
import com.google.code.kaptcha.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 处理User相关的请求，比如login 和 register
 */
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        WebUtils.param2bean(request, user);
        user = userService.login(user);
        if (user == null) {
            request.setAttribute("msg", "用户名密码错误");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        } else {
            // 将用户信息存放到session中，方便资源间共享
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/pages/user/login_success.jsp");
        }
    }

    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageCode = request.getParameter("code");
        // Constants.KAPTCHA_SESSION_KEY = KAPTCHA_SESSION_KEY
        // kaptcha在生成验证码图片时，还会将验证码存放到session中，并且key为KAPTCHA_SESSION_KEY
        String sessionCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        System.out.println("session " + sessionCode);
        System.out.println("page " + pageCode);
        if (sessionCode == null || !sessionCode.equals(pageCode)) {
            request.setAttribute("msg", "验证码错误");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            return;
        }
        User user = new User();
        WebUtils.param2bean(request, user);
        boolean res = userService.register(user);
//        System.out.println(user);
        if (res) {
            response.sendRedirect(request.getContextPath() + "/pages/user/regist_success.jsp");
        } else {
            request.setAttribute("msg", "用户已存在");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        }
    }

    /**
     * 用户登出，直接销毁session
     */
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect(request.getContextPath());
    }
}
