<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>
    <%@include file="/include/base.jsp" %>
    <script>
        $(function () {
            $(".delBtn").click(function () {
                // 获取图书名称
                var title = $(this).parent().parent().children().first().text();
                if (!confirm("确定删除" + title + "吗?")) {
                    return false;
                }
            });
            $("#gotoPage").click(function () {
                // 获取页码
                var pn = $("#pn_input").val();
                // 发送行的请求
                window.location.href = "manager/BookManagerServlet?method=page&pn=" + pn;
            });
        });
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">图书管理系统</span>
    <%@include file="/include/book-manager.jsp" %>
</div>
<div id="main">
    <table>
        <tr>
            <td>名称</td>
            <td>价格</td>
            <td>作者</td>
            <td>销量</td>
            <td>库存</td>
            <td colspan="2">操作</td>
        </tr>
        <c:forEach items="${requestScope.page.pageData}" var="book">
            <tr>
                <td>${book.title}</td>
                <td>${book.price}</td>
                <td>${book.author}</td>
                <td>${book.sales}</td>
                <td>${book.stock}</td>
                <td><a href="manager/BookManagerServlet?method=getOne&id=${book.id}&pn=${page.pageNo}">修改</a></td>
                <td><a class="delBtn" href="manager/BookManagerServlet?method=delete&id=${book.id}">删除</a></td>
            </tr>
        </c:forEach>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><a href="pages/manager/book_edit.jsp?pn=${page.totalPage + 1}">添加图书</a></td>
        </tr>

    </table>
    <jsp:include page="/include/page.jsp"/>
</div>

<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
</div>
</body>
</html>