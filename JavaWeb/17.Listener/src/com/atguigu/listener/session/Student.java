package com.atguigu.listener.session;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;
import java.io.Serializable;

/**
 * 当把Student对象存放到session中时，如果session钝化了，那么会监听到，
 * 并调用相应的接口
 */
public class Student implements HttpSessionActivationListener, Serializable {
    /**
     * 当session 钝化时，如果该类的对象存放在session中，那么会调用该方法
     *
     * @param se
     */
    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
    }

    /**
     * 当session 活化时，如果该类的对象存放在session中，那么会调用该方法
     *
     * @param se
     */
    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        session.getAttribute("xxx");
    }
}
