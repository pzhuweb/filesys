<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传</title>
<script type="text/javascript" src="js/jquery-3.2.0.min.js"></script>
<script type="text/javascript">
	function check() {
		if($("#filepath").val()==""){
			alert("filepath is empty!");
			return false;
		}		
		return true;
	}
</script>
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<form action="UploadServlet" method="post" enctype="multipart/form-data" onsubmit="return check()">
	选择文件：<input type="file" name="filepath" id="filepath"><br/>
	文件描述：<br><textarea cols="35" rows="5" name="note"></textarea>
	<input type="submit" value="上传文件">
</form>
</body>
</html>