<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
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
		return true;
	}
	
	function checkUser() {
		if($("#username").val()!="") {
			$.get("CheckUserNameServlet",{username:$("#username").val()},
					function(data){
						$("#note").html(data);
					}	
				)
		}else{
			$("#note").html("The username is empty!");
		}
	}
	
	function regist(){
		var md5_pwd = md5($("#password").val());
		$("#password").val(md5_pwd);
		$("#form1").submit();
	}
</script>
</head>
<body>
<form id="form1" action="RegistServlet" method="post" onsubmit="return check()">
	username:<input type="text" name="username" id="username" onchange="checkUser()"/><span id="note"></span><br>
	password:<input type="password" name="password" id="password"/><br>		
</form>
<button onclick="regist()">注册</button>	
</body>
</html>