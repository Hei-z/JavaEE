package com.atguigu.servlet;

import com.atguigu.bean.Student;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JQueryGetServlet extends BaseServlet {
    // ajax发送请求
    protected void getUrlParam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student stu = new Student("张三", 22);
        Gson gson = new Gson();
        String jsonStr = gson.toJson(stu);
        response.getWriter().write(jsonStr);
    }

    // ajax发送请求，获取data形参中的参数
    protected void getDataParam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        // 发送get请求时加上，重新编码，解决乱码问题
//        name = new String(name.getBytes("ISO8859-1"), "UTF-8");
        System.out.println(name);
        String age = request.getParameter("age");
        Student stu = new Student(name, Integer.parseInt(age));
        Gson gson = new Gson();
        String jsonStr = gson.toJson(stu);
        response.getWriter().write(jsonStr);
    }
}
