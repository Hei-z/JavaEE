<%--
  Created by IntelliJ IDEA.
  User: ZRH
  Date: 2020/11/4
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>scope1</h1>
<%
    pageContext.setAttribute("username_in_page", "admin_page");
    request.setAttribute("username_in_request", "admin_request");
    session.setAttribute("username_in_session", "admin_session");
    application.setAttribute("username_in_application", "admin_application");
%>

<jsp:forward page="scope2.jsp"/>
</body>
</html>
