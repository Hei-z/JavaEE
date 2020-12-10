package com.atguigu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 采用令牌(token)机制解决表单的重复提交
 */
public class TestServlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 模拟网络延时
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession();
        // session中存放的token
        String sessionToken = (String) session.getAttribute("token");
        // 页面的令牌
        String pageToken = request.getParameter("token");
        // 将session中的令牌移除
        session.removeAttribute("token");
        // 第一次提交，进行处理
        if (pageToken != null && pageToken.equals(sessionToken)) {
            request.getRequestDispatcher("/success.jsp").forward(request, response);
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("请不要重复提交");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
