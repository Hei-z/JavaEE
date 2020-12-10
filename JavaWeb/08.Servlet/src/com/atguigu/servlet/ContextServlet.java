package com.atguigu.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * 测试ServletContext对象
 */
public class ContextServlet implements Servlet {
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
        ServletContext servletContext = config.getServletContext();
        String username = servletContext.getInitParameter("username");
        System.out.println(username);
        String contextPath = servletContext.getContextPath();
        System.out.println(contextPath); // /08.Servlet
        // 获取真实路径，传入一个虚拟路径
        System.out.println(servletContext.getRealPath("/"));

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
