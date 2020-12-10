<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="jquery-1.7.2.js"></script>
    <script>
        $(function () {
            $("#btn01").click(function () {
                $.ajax({
                    url: "JQueryGetServlet?method=getUrlParam", // 请求的地址
                    data: {"name": "z33", "age": 17}, // 请求参数
                    async: true, // 发送异步请求
                    type: "POST", // 设置为post请求，默认为get请求
                    // 请求成功时的回调函数
                    success: function (data) {
                        alert(data);
                    },
                    // 请求失败时的回调函数
                    error: function () {
                        alert("occur error")
                    }
                });
            });

            $("#btn02").click(function () {
                // 找到表单并且序列化
                var dt = $("#formSub").serialize();
                alert(dt);
                $.ajax({
                    url: "JQueryGetServlet?method=getDataParam", // 请求的地址
                    data: dt, // 请求参数
                    async: true, // 发送异步请求
                    type: "POST", // 设置为post请求，默认为get请求
                    // 请求成功时的回调函数
                    success: function (data) {
                        alert(data);
                    },
                    // 请求失败时的回调函数
                    error: function () {
                        alert("occur error")
                    }
                });
            });
        });
    </script>
</head>
<body>
<form id="formSub" action="">
    name: <input name="name"><br>
    age:<input name="age"><br>
</form>

<button id="btn01">采用$.ajax方法发送请求</button>
<br>
<button id="btn02">ajax提交表单数据</button>
<%--<br>--%>
<%--<button id="btn03">发送get请求，在data中带上参数，并且返回类型是json对象</button>--%>
<%--<br>--%>
<%--<button id="btn04">发送post请求，在data中带上参数，并且返回类型是json对象</button>--%>
<%--<br>--%>
<div id="divItem" style="height: 100px; width: 100px;">

</div>
</body>
</html>
