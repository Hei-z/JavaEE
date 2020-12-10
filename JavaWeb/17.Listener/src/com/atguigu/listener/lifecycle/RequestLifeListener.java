package com.atguigu.listener.lifecycle;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class RequestLifeListener implements ServletRequestListener {
    /**
     * 监听Request对象的销毁，当一个请求离开最后一个servlet或者filter时，调用该方法
     *
     * @param sre 通过sre对象可以获取servletContext对象和servletRequest对象
     */
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("RequestLifeListener.requestDestroyed");
    }

    /**
     * 当一个请求进入一个servlet或者filter时，调用该方法
     *
     * @param sre 通过sre对象可以获取servletContext对象和servletRequest对象
     */
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("RequestLifeListener.requestInitialized");
    }
}
