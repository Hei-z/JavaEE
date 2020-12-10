<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--请求主页时直接跳转到Servlet，然后查询图书并显示--%>
<jsp:forward page="/client/BookClientServlet?method=page"/>