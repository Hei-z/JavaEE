package com.atguigu.listener.session;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class People implements HttpSessionBindingListener {
    /**
     * People类对象绑定（保存setAttribute）到session中调用
     *
     * @param event
     */
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        // 获取绑定对象的key
        String name = event.getName();
        // 获取绑定对象的value
        Object value = event.getValue();
    }

    /**
     * People类对象从session中移除（解绑removeAttribute或者调用session.invalidate）时调用
     *
     * @param event
     */
    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        // 获取解绑对象的key
        String name = event.getName();
        // 获取解绑对象的value
        Object value = event.getValue();
    }
}
