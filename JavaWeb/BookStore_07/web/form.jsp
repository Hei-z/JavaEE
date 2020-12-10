<%@ page import="java.util.UUID" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--在session中设置令牌--%>
<c:set var="token" value="<%=UUID.randomUUID().toString()%>" scope="session"/>
<form action="TestServlet1" method="post">
    <input name="username"><br>
    <input type="hidden" name="token" value="${token}">
    <input type="submit">
</form>
</body>
</html>
