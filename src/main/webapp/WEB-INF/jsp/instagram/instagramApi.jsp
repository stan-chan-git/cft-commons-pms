<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<a name="">上传图片:</a>
<form action="create.do" method="post" enctype="multipart/form-data">
access_token:<input type="hidden" name="access_token" value="${access_token}" readonly="readonly" /><br>
照片:<input type="file" name="pic" /><br>
说明:<input type="text" value="" />
<!-- Common Param  -->
<input type="submit" value="发送" />
<hr>
</form>
   


</body>
</html>