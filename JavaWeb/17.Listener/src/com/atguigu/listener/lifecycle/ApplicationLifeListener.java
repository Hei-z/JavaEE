package com.atguigu.listener.lifecycle;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 项目加载进服务器时创建，项目卸载时销毁
 */
public class ApplicationLifeListener implements ServletContextListener {
    /**
     * * Notification that the web application initialization process is starting.
     * All ServletContextListeners are notified of context initialization before
     * any filter or servlet in the web application is initialized.
     *
     * @param sce Information about the ServletContext that was initialized
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {


    }

    /**
     * * Notification that the servlet context is about to be shut down. All
     * servlets and filters have been destroy()ed before any
     * ServletContextListeners are notified of context destruction.
     *
     * @param sce Information about the ServletContext that was destroyed
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
