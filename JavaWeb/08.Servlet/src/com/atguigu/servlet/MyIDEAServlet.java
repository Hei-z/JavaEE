package com.atguigu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HttpServlet专门为处理http请求而定制
 */
public class MyIDEAServlet extends HttpServlet {
    // 处理post请求，目前只有一种post请求，就是表单提交时，设置提交方式为post
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("success.html").forward(request, response);
    }

    /**
     * 处理get请求
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
