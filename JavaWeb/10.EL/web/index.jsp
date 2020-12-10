<%@ page import="com.atguigu.bean.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<%
    Student stu = new Student("alex", 10);
    int[] arr = {};
    pageContext.setAttribute("attr", arr);
%>
${empty attr}
</body>
</html>
