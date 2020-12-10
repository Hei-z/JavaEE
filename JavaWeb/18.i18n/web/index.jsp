<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入国家化的标签库--%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<%--通过fmt:setLocale可以设置区域信息(语言_国家)，这里从请求参数中获取，如果没有，那么就采用当前服务器系统默认的--%>
<fmt:setLocale value="${param.locale}"/>
<%--通过该标签可以设置好bundle对象，其中basename为资源文件的基础名--%>
<fmt:setBundle basename="login"/>
<h6>
    <fmt:message key="title_info">
        <%--将参数传入资源文件--%>
        <fmt:param>zrh</fmt:param>
        <fmt:param>
            <%--fmt:formatDate显示日期，其中vlaue表示要的日期
                type:表示对日期(date)还是时间(time)还是两者(both)格式化
                dateStyle:表示对日期格式化的格式,full,long,short,medium
                timeStyle:表示对时间格式化的格式，同上
             --%>
            <fmt:formatDate value="<%=new Date()%>" type="both" dateStyle="full" timeStyle="full"/>
        </fmt:param>
    </fmt:message>
</h6>
<form>
    <%--从资源文件中获取相应的值--%>
    <fmt:message key="username"/>: <input type="text" name="username"><br>
    <fmt:message key="password"/>: <input type="password" name="password"><br>
    <input type="submit" value="<fmt:message key="login"/>">
</form>
<br>
<%--点击切换中英文--%>
<a href="index.jsp?locale=zh_CN">中文</a> | <a href="index.jsp?locale=en_US">英文</a>
</body>
</html>
