<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件列表</title>
<script type="text/javascript" src="js/jquery-3.2.0.min.js"></script>
<script type="text/javascript">
	function check() {
		return confirm("是否确认删除?");
	}

	
</script>
</head>
<body>
<%
	if(request.getAttribute("flag")==null){
		request.getRequestDispatcher("SelectAllFileServlet").forward(request, response);
	}
%>
<jsp:include page="head.jsp"></jsp:include>
<table border="1">
	<tr>
		<th>文件ID</th>
		<th>上传者</th>
		<th>文件名</th>
		<th>下载地址</th>
		<th>上传日期</th>
		<th>文件说明</th>
		<th colspan="2">操作</th>
	</tr>
	<c:forEach items="${list }" var="file" varStatus="num">
		<tr>
			<td>${file.fid }</td>
			<td>${file.username }</td>
			<td>${file.filename }</td>
			<td><a href="download.jsp?path=${file.filepath }">${file.filepath }</a></td>
			<td>${file.date }</td>
			<td>${file.note }</td>
			<td><a href="edit.jsp?index=${num.index }"><button>编辑</button></a></td>
			<td>
				<form action="DeleteFileServlet" method="post" onsubmit="return check()">
					<input type="hidden" name="fid" value="${file.fid }">
					<input type="hidden" name="filepath" value="${file.filepath }">
					<input type="submit" value="删除"> 
				</form>
			</td>
		</tr>
	</c:forEach>
	
	<tr align="center">
		<td colspan="8">${bar }</td>
	</tr>
	
	<tr align="center">
		<td colspan="8"><a href="index.jsp">上传文件</a></td>
	</tr>
</table>
</body>
</html>