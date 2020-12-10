<%--
  Created by IntelliJ IDEA.
  User: ZRH
  Date: 2020/12/3
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<%--form表单一定要改成post和multipart/form-data，这样每个input框都是一个部件--%>
<form method="post" action="FileUploadServlet" enctype="multipart/form-data">
    用户名：<input type="text" name="username"><br>
    头像：<input type="file" name="fileImg"><br>
    <input type="submit">
</form>
</body>
</html>
