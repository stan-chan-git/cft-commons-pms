<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发送一条微博消息</title>
</head>
<body>
<p style="color:blue">发送一条微博消息>>></p>
<form action="${pageContext.request.contextPath}/tencent/sendWeiBo.do" method="post">
微博内容：<textarea name="content" rows="5" cols="40">这是测试微博.......</textarea>
<br><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="submit" value="发送" />
</form>
</body>
</html>