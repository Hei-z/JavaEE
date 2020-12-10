<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(function () {
        $("#gotoPage").click(function () {
            // 获取页码
            var pn = $("#pn_input").val();
            // 发送行的请求
            window.location.href = "${page.url}&pn=" + pn;
        });
    });
</script>

<div id="page_nav">
    <a href="${page.url}&pn=1">首页</a>
    <c:if test="${page.pageNo > 1}">
        <a href="${page.url}&pn=${page.pageNo - 1}">上一页</a>
    </c:if>

    <%--只显示五页--%>
    <%--总页码小于等于5--%>
    <c:if test="${page.totalPage <= 5}">
        <%--在page域中设置forEach的begin和end值--%>
        <c:set var="begin" value="1" scope="page"/>
        <c:set var="end" value="${page.totalPage}" scope="page"/>
    </c:if>
    <%--总页码大于5--%>
    <c:if test="${page.totalPage > 5}">
        <c:if test="${page.pageNo < 3}">
            <c:set var="begin" value="1" scope="page"/>
            <c:set var="end" value="5" scope="page"/>
        </c:if>
        <c:if test="${page.pageNo >= 3}">
            <c:set var="begin" value="${page.pageNo - 2}" scope="page"/>
            <c:set var="end" value="${page.pageNo + 2}" scope="page"/>
        </c:if>
        <c:if test="${page.pageNo >= page.totalPage - 2}">
            <c:set var="begin" value="${page.totalPage - 4}" scope="page"/>
            <c:set var="end" value="${page.totalPage}" scope="page"/>
        </c:if>
    </c:if>
    <c:forEach begin="${begin}" end="${end}" var="pno">
        <c:if test="${pno == page.pageNo}">
            【${page.pageNo}】
        </c:if>
        <c:if test="${pno != page.pageNo}">
            <a href="${page.url}&pn=${pno}">${pno}</a>
        </c:if>
    </c:forEach>

    <c:if test="${page.pageNo < page.totalPage}">
        <a href="${page.url}&pn=${page.pageNo + 1}">下一页</a>
    </c:if>
    <a href="${page.url}&pn=${page.totalPage}">末页</a>
    共${page.totalPage}页，${page.totalCount}条记录
    到第<input value="${page.pageNo}" name="pn" id="pn_input"/>页
    <input type="button" value="确定" id="gotoPage">
</div>