<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>尚硅谷会员注册页面</title>
	<%--静态包含头文件--%>
	<%@include file="/pages/common/head.jsp" %>


	<script type="text/javascript">
        //页面加载完成之后
        $(function () {
            //提交按钮
            $("#sub_btn").click(function () {
                //验证用户名
                var userName = $("#username").val();
                var patt = /^\w{5,12}$/;
                if (!patt.test(userName)) {
                    $("span.errorMsg").text("用户名不合法");
                    return false;
                } else {

                    $("span.errorMsg").text("");

                }

                //验证密码
                var password = $("#password").val();
                var patt = /^\w{5,12}$/;
                if (!patt.test(password)) {
                    $("span.errorMsg").text("密码不合法");
                    return false;
                } else {
                    $("span.errorMsg").text("");
                }

                //验证确认密码
                var password = $("#password").val();
                var repwd = $("#repwd").val();
                if (repwd != password) {
                    $("span.errorMsg").text("密码不一致");
                    return false;
                } else {
                    $("span.errorMsg").text("");
                }

                //验证邮箱
                var email = $("#email").val();
                var patt = /^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,})$/;
                if (!patt.test(email)) {
                    $("span.errorMsg").text("邮箱格式不正确");
                    return false;
                } else {
                    $("span.errorMsg").text("");
                }

                //验证验证码
                var code = $("#code").val();
                //去空格
                var codeT  = $.trim(code);
                if (codeT == null || codeT == "" || codeT.length == 0) {
                    $("span.errorMsg").text("请输入验证码");
                    return false;
                } else {
                    $("span.errorMsg").text("");
                }
            });

			$("#username").change(function () {
				$.getJSON("ajaxServlet","action=checkUsername&username="+$("#username").val(),function (data) {
					$("span.errorMsg").html(data);
                })
            });
        })
	</script>
	<style type="text/css">
		.login_form {
			height: 420px;
			margin-top: 25px;
		}

	</style>
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
					<span class="errorMsg">${requestScope.message==null?"":requestScope.message}</span>
				</div>
				<div class="form">
					<form action="userServlet" method="post">
						<%--隐藏域判断注册功能--%>
						<input type="hidden" name="action" value="regist">
						<label>用户名称：</label>
						<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
							   name="username" id="username"
							   value="${requestScope.username==null?"":requestScope.username}"
						/>
						<br/>
						<br/>
						<label>用户密码：</label>
						<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
							   name="password" id="password"
						/>
						<br/>
						<br/>
						<label>确认密码：</label>
						<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1"
							   name="repwd" id="repwd"
						/>
						<br/>
						<br/>
						<label>电子邮件：</label>
						<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1"
							   name="email" id="email"
							   value="${requestScope.email==null?"":requestScope.email}"
						/>
						<br/>
						<br/>
						<label>验证码：</label>
						<input class="itxt" type="text" style="width: 150px;" name="code" id="code"/>
						<img id="codeImg" alt="" height="45px" width="90px" src="${basePath}kaptcha.jpg" style="float: right; margin-right: 40px">
						<br/>
						<br/>
						<input type="submit" value="注册" id="sub_btn"/>
							<%--验证码单击事件--%>
						<script type="text/javascript">
							$("#codeImg").click(function () {
							    //加时间戳避免浏览器使用缓存 的验证码
                                this.src = "${bathPath}kaptcha.jpg?time="+new Date();
                            })
						</script>

					</form>
				</div>

			</div>
		</div>
	</div>
</div>
<%--静态包含页脚--%>
<%@include file="/pages/common/foot.jsp"%>
</body>
</html>