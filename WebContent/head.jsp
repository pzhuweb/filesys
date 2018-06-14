<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>头部</title>
</head>
<body>
尊敬的【<a href="SelectFileByUsernameServlet">${user.username}</a>】，欢迎使用文件共享系统！[<a href="LogoutServlet">注销</a>]<br/>
所有共享的文件：【<a href="SelectAllFileServlet">点击查看</a>】<br/>
<form action="SelectFileByKeyword" method="get">
	模糊查询：<input type="text" name="keyword">
	<input type="submit" value="查询">
</form>

</body>
</html>