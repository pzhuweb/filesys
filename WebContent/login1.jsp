<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<jsp:include page="title.jsp"></jsp:include>
<script type="text/javascript" src="js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="js/md5.min.js"></script>
<script type="text/javascript">
	function check() {
		if($("#username").val()==""){
			alert("username is empty!");
			return false;
		}
		if($("#password").val()==""){
			alert("password is empty!");
			return false;
		}
		if($("#usercode").val()==""){
			alert("usercode is empty!");
			return false;
		}
		return true;
	}

	function regist() {
		window.location.href="regist.jsp";
	}
	function login(){
		$("#form1").submit();
	}
</script>
</head>
<body>

<form id="form1" action="LoginServletWithNoMd5" method="post" onsubmit="return check()">
	username:<input type="text" name="username" id="username" value="${username }"/><br>
	password:<input type="password" name="password" id="password" value="${password }"/><br>
	checkcode:<input type="text" name="usercode" id="usercode"/><img alt="error" src="ImageServlet"><br>
	<input type="checkbox" name="isSave" ${isSave=="yes"?"checked":""}/>自动登录<br>
</form>
	<input type="button" value="regist" onclick="regist()"/>
	<input type="button" value="login" onclick="login()"/>

</body>
</html>