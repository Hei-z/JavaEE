package com.atguigu.tag;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTag;
import java.io.IOException;

public class MyTag implements SimpleTag {
    /*
    <attribute>
        <!--name指定属性名-->
        <name>msg</name>
        <!--表示这个属性必传-->
        <required>true</required>
        <!--runtime expression value：表示该属性是否可以用jsp表达式，el表达式等
        例如设置了true，写了name="${xx}"，那么会解析该el表达式-->
        <rtexprvalue>true</rtexprvalue>
    </attribute>
     */
    // 对应标签中的msg属性
    private String msg;

    public String getMsg() {
        return msg;
    }

    // 当在页面的标签中写 msg="xxx"时，会自动调用该set方法
    public void setMsg(String msg) {
        this.msg = msg;
    }

    private PageContext pc;

    /**
     * 执行标签的功能
     */
    @Override
    public void doTag() throws JspException, IOException {
        //向页面输出属性值
        pc.getOut().write(msg);
    }

    /**
     * 设置父标签，服务器自动传入，就是如果我们在该标签外面编写了jsp标签，那么服务器会将该父标签传入到这个方法
     *
     */
    @Override
    public void setParent(JspTag parent) {

    }

    /**
     * 获取父标签
     */
    @Override
    public JspTag getParent() {
        return null;
    }

    /**
     * 设置JspContext，就是pageContext，服务器自动传入pageContext对象，就是如果我们将该标签写个某个jsp页面，
     * 那么服务器就会将这个jsp页面的pageContext对象传入到该方法中
     *
     */
    @Override
    public void setJspContext(JspContext pc) {
        this.pc = (PageContext) pc;
    }

    /**
     * 设置标签体内容，服务器自动传入，就是我们在jsp页面编写了写在该标签中的内容，服务器会自动传入到该方法中
     *
     * @return
     */
    @Override
    public void setJspBody(JspFragment jspBody) {

    }
}
