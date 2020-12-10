<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的订单</title>
    <%@include file="/include/base.jsp" %>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="../../static/img/logo.gif">
    <span class="wel_word">我的订单</span>
    <%@include file="/include/user-info.jsp" %>
</div>

<div id="main">
    <table>
        <tr>
            <td>订单号</td>
            <td>日期</td>
            <td>金额</td>
            <td>状态</td>
            <td>详情</td>
        </tr>
        <c:forEach items="${orders}" var="order">
            <tr>
                <td>${order.orderId}</td>
                <td>${order.createDate}</td>
                <td>${order.totalPrice}</td>
                <td>
                    <c:choose>
                        <c:when test="${order.status == 0}">
                            未发货
                        </c:when>
                        <c:when test="${order.status == 1}">
                            已发货
                            <a href="client/OrderClientServlet?method=confirm&orderId=${order.orderId}">确认收货</a>
                        </c:when>
                        <c:when test="${order.status == 2}">
                            已完成
                        </c:when>
                        <c:otherwise>
                            其他
                        </c:otherwise>
                    </c:choose>
                </td>
                <td><a href="client/OrderClientServlet?method=details&orderId=${order.orderId}">查看详情</a></td>
            </tr>
        </c:forEach>

    </table>
</div>

<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
</div>
</body>
</html>