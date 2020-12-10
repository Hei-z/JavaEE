package com.atguigu.filter;

import com.atguigu.bean.User;
import com.atguigu.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // 验证用户是否登录
        User loginUser = WebUtils.getLoginUser((HttpServletRequest) req);
        if (loginUser != null) {
            // 用户已经登录，放行
            chain.doFilter(req, resp);
        } else {
            // 转发到登录页面
            req.setAttribute("msg", "请先登录");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
