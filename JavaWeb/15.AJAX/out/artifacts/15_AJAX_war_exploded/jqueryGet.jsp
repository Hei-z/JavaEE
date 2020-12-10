<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="jquery-1.7.2.js"></script>
    <script>
        $(function () {
            $("#btn01").click(function () {
                // url,[data]：请求参数,[callback]：响应完成后的回调函数,[type]：返回数据类型
                $.get("JQueryGetServlet?method=getUrlParam", function (data) {
                    // data为相应的数据
                    // 将json字符串转成json对象
                    var obj = JSON.parse(data);
                    var str = obj.name + "<br>" + obj.age;
                    $("#divItem").html(str);
                }, "text");
            });

            $("#btn02").click(function () {
                // url,[data]：请求参数,[callback]：响应完成后的回调函数,[type]：返回数据类型
                $.get("JQueryGetServlet?method=getUrlParam", function (data) {
                    // 指定返回类型为json对象，那么直接会将json字符串转成json对象
                    var obj = data;
                    var str = obj.name + "<br>" + obj.age;
                    $("#divItem").html(str);
                    // 指定返回类型为json对象，那么直接会将json字符串转成json对象
                }, "json");
            });
            $("#btn03").click(function () {
                // 形参中传入参数
                $.get("JQueryGetServlet?method=getDataParam", {name: "李四", age: 20}, function (data) {
                    // 指定返回类型为json对象，那么直接会将json字符串转成json对象
                    var obj = data;
                    var str = obj.name + "<br>" + obj.age;
                    $("#divItem").html(str);
                    // 指定返回类型为json对象，那么直接会将json字符串转成json对象
                }, "json");
            });
            $("#btn04").click(function () {
                // 形参中传入参数
                $.post("JQueryGetServlet?method=getDataParam", {name: "李四", age: 20}, function (data) {
                    // 指定返回类型为json对象，那么直接会将json字符串转成json对象
                    var obj = data;
                    var str = obj.name + "<br>" + obj.age;
                    $("#divItem").html(str);
                    // 指定返回类型为json对象，那么直接会将json字符串转成json对象
                }, "json");
            });

        });
    </script>
</head>
<body>
<button id="btn01">发送get请求，在url中带上参数，并且返回类型是text(string)</button>
<br>
<button id="btn02">发送get请求，在url中带上参数，并且返回类型是json对象</button>
<br>
<button id="btn03">发送get请求，在data中带上参数，并且返回类型是json对象</button>
<br>
<button id="btn04">发送post请求，在data中带上参数，并且返回类型是json对象</button>
<br>
<div id="divItem" style="height: 100px; width: 100px;">

</div>
</body>
</html>
