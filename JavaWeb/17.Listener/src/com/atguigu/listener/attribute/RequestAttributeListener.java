package com.atguigu.listener.attribute;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;

public class RequestAttributeListener implements ServletRequestAttributeListener {
    /**
     * 当在request域中添加了属性时调用
     *
     * @param srae
     */
    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        // 获取添加的属性名(key)
        String name = srae.getName();
        // 获取添加的属性值(value)
        Object value = srae.getValue();
    }

    /**
     * 当移除了request域中的属性时调用
     *
     * @param srae
     */
    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        // 获取修改的属性名(key)
        String name = srae.getName();
        // 获取修改 前 的属性值(value)
        Object value = srae.getValue();
        ServletRequest request = srae.getServletRequest();
        // 获取修改后的属性值
        request.getAttribute(name);
    }

    /**
     * 当修改了request域中的属性时调用
     *
     * @param srae
     */
    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        // 获取删除的属性名(key)
        String name = srae.getName();
        // 获取删除的属性值(value)
        Object value = srae.getValue();
    }
}
