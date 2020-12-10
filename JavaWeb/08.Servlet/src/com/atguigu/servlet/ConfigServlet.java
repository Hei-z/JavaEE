package com.atguigu.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * 测试ServletConfig
 */
public class ConfigServlet implements Servlet {
    private ServletConfig config;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("ServletName : " + config.getServletName());
        System.out.println("InitParameter username : " + config.getInitParameter("username"));
        System.out.println(config.getServletContext());
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
