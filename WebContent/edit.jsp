<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑文件信息</title>
<script type="text/javascript" src="js/jquery-3.2.0.min.js"></script>
<script type="text/javascript">
	function check() {
		if($("#filename").val()==""){
			alert("文件名不能为空!");
			return false;
		}			
		return true;
	}

</script>
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<%
	int index = Integer.valueOf(request.getParameter("index"));
	request.setAttribute("index", index);
	
%>
<form action="EditFileServlet" method="post" onsubmit="return check()">
	文件ID：<input type="text" name="fid" disabled="disabled" value="${list[index].fid }">
	<input type="hidden" name="fid" value="${list[index].fid }"><br/>
	上传者：<input type="text" name="username" disabled="disabled" value="${list[index].username }"><br/>
	文件名：<input type="text" name="filename" id="filename" value="${list[index].filename }"><br/>
	文件URL：<input type="text" name="filepath" disabled="disabled" value="${list[index].filepath }"><br/>
	上传日期：<input type="text" name="date" disabled="disabled" value="${list[index].date }"><br/>
	文件描述：<br><textarea cols="35" rows="5" name="note">${list[index].note }</textarea>
	<input type="submit" value="修改">
</form>
</body>
</html>