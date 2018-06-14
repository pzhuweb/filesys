<%@page import="com.jspsmart.upload.SmartUpload"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>下载页面</title>
</head>
<body>
<%
	out.clear();
	out = pageContext.pushBody();
	String path = request.getParameter("path");
	SmartUpload smart = new SmartUpload();
	smart.initialize(pageContext);
	smart.downloadFile(path);

%>
</body>
</html>