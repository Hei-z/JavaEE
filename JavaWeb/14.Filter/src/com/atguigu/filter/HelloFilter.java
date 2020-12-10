package com.atguigu.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter是单例的
 * 生命周期：
 * 1、服务器已启动就创建Filter对象，然后调用其init()方法
 * 2、每次请求都执行doFilter()方法
 * 3、项目从服务器中卸载，执行destroy()方法
 */
public class HelloFilter implements Filter {


    public HelloFilter() {
        System.out.println("HelloFilter.HelloFilter");
    }

    /**
     * 初始化方法
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 获取filter的别名
        String filterName = filterConfig.getFilterName();
        // 获取初始化参数
        String username = filterConfig.getInitParameter("username");
        // servletContext对应一个web应用
        ServletContext servletContext = filterConfig.getServletContext();
        // 获取web初始化参数，在web.xml中 <context-param>中配置的
        String initParameter = servletContext.getInitParameter("");
    }

    /**
     * 执行过滤方法
     * 如果带上了hello参数就放行，否则不放行
     *
     * @param chain 过滤器链
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("HelloFilter.doFilter");
    }

    /**
     * 销毁方法
     */
    @Override
    public void destroy() {
        System.out.println("HelloFilter.destroy");
    }
}
