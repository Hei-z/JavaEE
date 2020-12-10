package com.atguigu.cookie;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    /**
     * 处理用户的登录，如果用户没有username的cookie，那么就创建该cookie，否则将原cookie返回
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username != null && password != null) {
            Cookie cookie = null;
            Cookie[] cookies = request.getCookies();
            for (Cookie c : cookies) {
                if ("username".equals(c.getName())) {
                    cookie = c;
                    break;
                }
            }
            if (cookie == null) {
                cookie = new Cookie("username", username);
                cookie.setMaxAge(60);
            }
            response.addCookie(cookie);
        }
        response.sendRedirect(request.getContextPath() + "/success.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
