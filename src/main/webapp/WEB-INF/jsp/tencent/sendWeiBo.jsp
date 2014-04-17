<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发送一条微博消息</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/tencent/sendWeiBo.do" role="form" method="post">
<div class="form-group">
<label>微博内容:</label>
<textarea class="form-control" name="content" rows="5" cols="40">这是测试微博.......</textarea>
</div>
<div class="form-group">
<input class="btn btn-default" type="submit" value="发送" />
</div>
</form>
</body>
</html>