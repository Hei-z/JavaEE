<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <%@include file="/include/base.jsp" %>

</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">网上书城</span>
    <%@include file="/include/user-info.jsp" %>
</div>
<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="client/BookClientServlet?method=pageByPrice&pn=1" method="post">
                价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
                <input id="max" type="text" name="max" value="${param.max}"> 元
                <input type="submit" value="查询"/>
            </form>
        </div>
        <div style="text-align: center">
            <%--<c:out value="${title}" default="0"/>:
            显示购物车中总共有几件商品
            --%>
            <span>您的购物车中有<c:out value="${cart.totalCount}" default="0"/>件商品</span>
            <c:if test="${!empty title}">
                <div>
                    您刚刚将<span style="color: red">${title}</span>加入到了购物车中
                </div>
            </c:if>
            <c:if test="${empty title}">
                <div>
                    <span style="color: red">&nbsp;</span>
                </div>
            </c:if>
        </div>
        <c:forEach items="${page.pageData}" var="book">
            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" alt="" src="static/img/default.jpg"/>
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${book.title}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${book.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">￥${book.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${book.sales}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${book.stock}</span>
                    </div>
                    <div class="book_add">
                        <a href="client/CartServlet?method=add&id=${book.id}">加入购物车</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <jsp:include page="/include/page.jsp"/>

</div>

<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
</div>
</body>
</html>