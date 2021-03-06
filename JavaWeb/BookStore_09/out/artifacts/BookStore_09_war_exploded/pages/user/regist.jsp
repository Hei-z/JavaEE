<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员注册页面</title>
    <%@include file="/include/base.jsp" %>
    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }
    </style>
    <script>
        $(function () {
            $("#sub_btn").click(function () {
                var userName = $("#username").val();
                var pwd = $("#password").val();
                var repwd = $("#repwd").val();
                var email = $("#email").val();
                var code = $("#code").val();
                var regUserName = /^[a-z0-9_-]{3,16}$/;
                var regPwd = /^[a-z0-9_-]{3,16}$/;
                var regEmail = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                if (!regUserName.test(userName)) {
                    alert("用户名格式不正确");
                    return false;
                } else if (!regPwd.test(pwd)) {
                    alert("密码格式不正确");
                    return false;
                } else if (pwd !== repwd) {
                    alert("密码不一致");
                    return false;
                } else if (code === "") {
                    alert("验证码为空");
                    return false;
                }
            });
            // 点击图片之后，切换验证码
            $("#imgCode").click(function () {
                // 改变图片的src属性即可
                // 注意不要这样写，因为有些浏览器发现你连续发相同的请求，那么直接去缓存中取数据，所以还是之前的数据
                // $(this).prop("src", "code.jpg");
                $(this).prop("src", "code.jpg?t=" + Math.random());
            });

            // 当用户名输入框失去焦点时，给服务器发送请求，查看用户名是否可用
            $("#username").blur(function () {
                var username = $(this).val();
                var regUserName = /^[a-z0-9_-]{3,16}$/;
                // 先检查用户名格式是否正确，正确才发送请求
                if (regUserName.test(username)) {
                    $.get("UserServlet?method=checkUser&username=" + username, function (data) {
                        $(".errorMsg").html(data);
                    });
                }
            });
        });
    </script>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册尚硅谷会员</h1>
                    <span class="errorMsg">${msg}</span>
                </div>
                <div class="form">
                    <form action="UserServlet" method="post">
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
                               name="username" id="username"
                               value="${param.username}"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                               name="password" id="password"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1"
                               name="repwd" id="repwd"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1"
                               name="email" id="email"
                               value="<%=request.getParameter("email") == null ? "" : request.getParameter("email")%>"/>
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" type="text" style="width: 80px;" id="code" name="code"/>
                        <img alt="" id="imgCode" src="code.jpg"
                             style="float: right; margin-right: 40px; width: 120px; height: 40px">
                        <br/>
                        <br/>
                        <input type="submit" value="注册" id="sub_btn"/>
                        <input type="hidden" name="method" value="register">
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<div id="bottom">
			<span>
				尚硅谷书城.Copyright &copy;2015
			</span>
</div>
</body>
</html>