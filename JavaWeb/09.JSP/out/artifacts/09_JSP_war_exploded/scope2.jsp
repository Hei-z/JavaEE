<%--
  Created by IntelliJ IDEA.
  User: ZRH
  Date: 2020/11/4
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>scope2</h1>
<%=pageContext.getAttribute("username_in_page")%><br>
<%=request.getAttribute("username_in_request")%><br>
<%=session.getAttribute("username_in_session")%><br>
<%=application.getAttribute("username_in_application")%><br>
</body>
</html>
