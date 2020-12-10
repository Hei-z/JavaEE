<%--
  Created by IntelliJ IDEA.
  User: ZRH
  Date: 2020/11/30
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <script src="jquery-1.7.2.js"></script>
    <script>
        $(function () {
            // 定义一个json对象--->object类型
            var stu = {"name": "zrh", "age": 18};
            // 将json对象转成json字符串--->string类型，方便传输
            var str = JSON.stringify(stu);
            // 将json字符串转成对象，方便调用对象的属性
            var obj = JSON.parse(str);
        });

        function ajaxRequestJQuery() {
            // 可以传入四个参数
            // url：请求地址
            // [data]：请求的参数，待发送 Key/value 参数。一般用于post请求
            // [callback]：回调函数，响应完之后调用，可以接收一个形参，其会存放响应的数据
            // [type]：响应的数据的类型 返回内容格式，xml, html, script, json, text, _default
            $.get("AJAXServlet?p=1&r=" + Math.random(), function (dt) {
                alert(dt);
            }, "text");
        }


        function ajaxRequest() {
            var xhr = new XMLHttpRequest();
            // 设置请求信息
            // method：请求方法 GET, POST
            // url：请求地址
            // async：是否异步，默认异步(true)
            xhr.open("GET", "AJAXServlet?p=1&r=" + Math.random(), true);
            // 可以传入请求体，把发送的数据放在请求体中
            // 给POST请求用，发送get请求时不用传
            xhr.send(null);

            // 必须接收响应的数据
            //xhr 对象有两个属性
            // responseText，获取字符串形式的响应数据
            // responseXML，获取XML形式的响应内容
            // 必须在响应完成接收响应的数据，否则接收不到数据
            // 通过监听请求对象的状态(readyState)，来判断请求是否完成
            // onreadystatechange函数，只要xhr对象状态一改变就会被调用
            xhr.onreadystatechange = function () {
                // xhr.readyState 存放了请求的状态
                /*
                0 请求未初始化（在调用 open() 之前）
                1 请求已提出（调用 send() 之前）
                2 请求已发送（这里通常可以从响应得到内容头部）
                3 请求处理中（响应中通常有部分数据可用，但是服务器还没有完成响应）
                4 请求已完成（可以访问服务器响应并使用它）
                 */
                // xhr.status存放了状态码
                // 响应完成，并且响应成功，接收响应的数据
                if (xhr.readyState === 4 && xhr.status === 200) {
                    // alert(xhr.responseText);
                    // 将接收到的数据存放到div中
                    var divTime = document.getElementById("div_time");
                    divTime.innerHTML = xhr.responseText;
                }
            }
        }
    </script>
</head>
<body>
<button onclick="ajaxRequest()">ajax request</button>
<br>
<div id="div_time"></div>
<br>
<button onclick="ajaxRequestJQuery()">jquery ajax request</button>
</body>
</html>
