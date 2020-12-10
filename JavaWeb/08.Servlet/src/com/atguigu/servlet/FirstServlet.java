package com.atguigu.servlet;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 一个Servlet只能处理一个请求
 * 在web.xml中配置Servlet的响应信息
 */
public class FirstServlet implements Servlet {

    public FirstServlet() {
        System.out.println("FirstServlet.FirstServlet");
    }
    /**
     * 初始化
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("FirstServlet.init");
    }

    /**
     * 获取Servlet配置信息
     *
     * @return
     */
    @Override
    public ServletConfig getServletConfig() {
        System.out.println("FirstServlet.getServletConfig");
        return null;
    }

    /**
     * 该方法处理来自客户端的请求
     *
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("FirstServlet.service");
        //获取输出流对象
        PrintWriter writer = res.getWriter();
        // 向浏览器写数据
        writer.write("Hello World");
        writer.close();
    }

    /**
     * 返回当前Servlet的描述信息，基本上没用
     *
     * @return
     */
    @Override
    public String getServletInfo() {
        System.out.println("FirstServlet.getServletInfo");
        return null;
    }

    /**
     * 销毁方法
     */
    @Override
    public void destroy() {
        System.out.println("FirstServlet.destroy");
    }
}