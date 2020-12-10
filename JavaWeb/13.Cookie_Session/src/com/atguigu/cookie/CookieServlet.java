package com.atguigu.cookie;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieServlet extends BaseServlet {
    /**
     * 创建Cookie，并返回给浏览器
     */
    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = new Cookie("username", "zrh");
        response.addCookie(cookie);
        response.getWriter().write("cookie创建好了");
    }

    /**
     * 获取浏览器带来的cookie
     */
    protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取所有的cookie
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            response.getWriter().write("cookie name = " + cookie.getName() + "<br>");
            response.getWriter().write("cookie value = " + cookie.getValue() + "<br>");
        }
    }

    /**
     * 删除cookie
     * 可以创建一个同名cookie，然后setMaxAge(0)，浏览器会自动覆盖掉同名的cookie
     */
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取所有的cookie
        Cookie cookie = new Cookie("username", "zrh");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        response.getWriter().write("删除了cookie");
    }

    /**
     * 持久化cookie，设置cookie的存活时间为1 小时
     */
    protected void persist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        for (Cookie c : cookies) {
            if ("username".equals(c.getName())) {
                cookie = c;
                break;
            }
        }
        if (cookie == null) {
            cookie = new Cookie("username", "zrh");
        }
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
        response.getWriter().write("修改了cookie存活时间为1小时");
    }

    /**
     * 设置带有访问路径的cookie，在访问指定路径时才带上该cookie
     */
    protected void setpath(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = new Cookie("setpath_cookie", "setpath_cookie_value");
        // 此处的 / 表示服务器的根目录，因此 / 是告诉浏览器访问 /hello时才带上该cookie，所以/ 是由浏览器解析
        cookie.setPath("/hello");
        response.addCookie(cookie);
        response.getWriter().write("发送了 设置了 路径的cookie");
    }

    /**
     * 修改cookie的值
     * 因为浏览器在接收到同key的cookie时，会覆盖掉原来的cookie，所以直接创建一个同key的cookie，
     * 重新发送给浏览器即可
     */
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = new Cookie("username", "hei");
        response.addCookie(cookie);
        response.getWriter().write("修改了cookie");
    }
}
