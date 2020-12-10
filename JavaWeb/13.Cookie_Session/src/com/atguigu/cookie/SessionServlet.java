package com.atguigu.cookie;

import com.sun.xml.internal.bind.v2.model.core.ID;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class SessionServlet extends BaseServlet {
    /**
     * 获取一个session对象
     */
    protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // 判断session是否是新的，如果浏览器第一次访问那么会创建新的session对象，然后返回true
        boolean isNew = session.isNew();
        // 获取session的id
        String id = session.getId();
        response.getWriter().write("获取了session对象...isNew = " + isNew + " session id = " + id);
    }

    /**
     * 向session域中保存数据
     */
    protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("username", "zrh");
        response.getWriter().write("session中保存了数据");
    }

    /**
     * 获取session域中的数据
     */
    protected void getData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object username = session.getAttribute("username");
        response.getWriter().write("获取了session域中的username = " + username);
    }

    /**
     * 获取session对象在服务器中的存活时间
     */
    protected void time(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // 获取session对象在服务器中的存活时间，以秒为单位
        // 只要浏览器请求了，那么存活时间就会重新计时
        int maxInactiveInterval = session.getMaxInactiveInterval();
        response.getWriter().write("session的存活时间: " + maxInactiveInterval);
    }

    /**
     * 修改session对象在服务器中的时间
     */
    protected void updateTime(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // 只要浏览器请求了，那么存活时间就会重新计时
        session.setMaxInactiveInterval(60);
        response.getWriter().write("设置了session的存活时间");
    }

    /**
     * 强制session失效
     */
    protected void invalid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // 强制使session无效，可以理解为将session对象从服务器中删除
        /*
        注意会报异常
        IllegalStateException
                     if this method is called on an already invalidated session
         */
        session.invalidate();
        response.getWriter().write("强制使session对象失效");
    }

    /**
     * 浏览器禁止cookie，采用url重写解决
     */
    protected void prohibit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 要先获取session对象，这样才会产JSESSIONID，然后再对url重写
        HttpSession session = request.getSession();
        session.setAttribute("sessionKey", "sessionValue");
        //  The implementation of this method includes the logic to determine whether the session ID needs to be encoded in the URL.
        /*
         对请求的url重新编码，如果浏览器端禁用了cookie，那么会在url后面加上 ;jsessionid=xxxxxx，
         如果没有被禁，那么不会改变url
          */
        String url = response.encodeRedirectURL(request.getContextPath() + "/index.jsp");
        System.out.println(url);
        response.sendRedirect(url);
    }

    /**
     * 关闭浏览器保存session
     * 实际上就是将浏览器Cookie中的JSESSIONID持久化，这样再次打开浏览器时，旧的JSESSIONID还在
     */
    protected void persist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String id = session.getId();
        Cookie cookie = new Cookie("JSESSIONID", id);
        // 设置为1 小时
        cookie.setMaxAge(60 * 60);
        response.addCookie(cookie);
        response.getWriter().write("session已经持久化");
    }
}
