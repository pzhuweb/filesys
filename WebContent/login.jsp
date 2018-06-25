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
		var md5_pwd = md5(md5($("#password").val())+$("#secret").val());
		$("#password").val(md5_pwd);
		$("#form1").submit();
		
	}
	function turn() {
		var url = $("#img").attr("src");
		url = url.replace(/time\=(\d+)/g,'time='+new Date().getTime());		
		$('#img').attr('src',url);
	}
	function changeCode(n) {
		switch (n) {
			case 1:	$("#img").attr("src",'ImageServlet?type=og&time='+new Date().getTime()); 	
				break;
			case 2:	$("#img").attr("src",'ImageServlet?type=mul&time='+new Date().getTime()); 	
				break;
			case 3:	$("#img").attr("src",'ImageServlet?type=cn&time='+new Date().getTime()); 	
				break;
			default:$("#img").attr("src",'ImageServlet?type=og&time='+new Date().getTime()); 
				break;
		}
	}
</script>
</head>
<body>

<form id="form1" action="LoginServlet" method="post" onsubmit="return check()">
	<input type="hidden" value="${secret }" id="secret"/>
	username:<input type="text" name="username" id="username" value="${username }"/><br>
	password:<input type="password" name="password" id="password" value="${password }"/><br>
	checkcode:<input type="text" name="usercode" id="usercode"/>
	<img alt="error" id="img" src="ImageServlet?time=1" onclick="turn()" title="点击刷新">
	<a href="javascript:changeCode(1)">普通验证码</a>
	<a href="javascript:changeCode(2)">干扰验证码</a>
	<a href="javascript:changeCode(3)">中文验证码</a>
	<br>
	<input type="checkbox" name="isSave" ${isSave=="yes"?"checked":""}/>自动登录<br>
	
	<input type="button" value="regist" onclick="regist()"/>
	<input type="button" value="login" onclick="login()"/>
</form>

</body>
</html>