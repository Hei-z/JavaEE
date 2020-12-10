<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <%@include file="/include/base.jsp" %>
    <script>
        $(function () {
            // 如果用户修改了input框中的数量，那么就重新发送请求，更新数量
            $(".countInput").change(function () {
                var count = $(this).val();
                // 获取自定义属性的值
                var id = $(this).attr("updateId");
                //location.href = "client/CartServlet?method=update&id=" + id + "&count=" + count;
                // 采用ajax发送请求
                $.getJSON("client/CartServlet?method=updateAjax&id=" + id + "&count=" + count, function (data) {
                    $(".b_count").text(data.totalCount);
                    $(".b_price").text(data.totalPrice);
                    $("#price_" + id).text(data.itemPrice);
                });
            });

            // 判断用户是否删除购物车
            $("#clearBtn").click(function () {
                // confirm 会弹出对话框，如果用户点了确定就发送请求，点了取消就不发送
                if (!confirm("确定清空购物车吗？")) {
                    return false;
                }
            });
        });
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">购物车</span>
    <%@include file="/include/user-info.jsp" %>
</div>

<div id="main">

    <%--购物车中有数据--%>
    <c:if test="${!empty cart.items}">
        <table>
            <tr>
                <td>商品名称</td>
                <td>数量</td>
                <td>单价</td>
                <td>金额</td>
                <td>操作</td>
            </tr>
            <c:forEach items="${cart.items}" var="carItem">
                <tr>
                    <td>${carItem.book.title}</td>
                    <td>
                            <%--自定义属性updateId 存放book.id--%>
                        <input updateId="${carItem.book.id}" class="countInput" type="text" name="count"
                               value="${carItem.count}"/>
                    </td>
                    <%--显示书本单价--%>
                    <td>${carItem.book.price}</td>
                    <%--显示当前书本的总价，单价乘数量--%>
                    <td id="price_${carItem.book.id}">${carItem.totalPrice}</td>
                    <td><a href="client/CartServlet?method=delete&id=${carItem.book.id}">删除</a></td>
                </tr>
            </c:forEach>

        </table>

        <div class="cart_info">
            <span class="cart_span">购物车中共有<span class="b_count">${cart.totalCount}</span>件商品</span>
            <span class="cart_span">总金额<span class="b_price">${cart.totalPrice}</span>元</span>
            <span class="cart_span"><a id="clearBtn" href="client/CartServlet?method=clear">清空购物车</a></span>
            <span class="cart_span"><a href="client/OrderClientServlet?method=checkout">去结账</a></span>
        </div>
    </c:if>

    <%--购物车中没数据--%>
    <c:if test="${empty cart.items}">
        <h1>购物车为空，<a href="index.jsp">去主页购买</a></h1>
    </c:if>

</div>

<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
</div>
</body>
</html>