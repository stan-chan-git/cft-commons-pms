<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>demo</title>
</head>
<body>
<h1>DEMO page</h1>
<form id="loginForm" action="${ctx}/api/login" method="post">
UserName : <input type="text" id="username" name="username" value="${username}" />
Password: <input type="password" id="password" name="password" />
<input type="checkbox" id="rememberMe" name="rememberMe"/> 记住我
<input id="submit_btn" class="btn" type="submit" value="登录"/>
</form>
</body>
</html>