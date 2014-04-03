<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setAttribute("ctx", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import="org.apache.shiro.SecurityUtils"%>
<%@ page import="org.apache.shiro.subject.Subject"%>

<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function() {
		$("#signupForm").validate();
	});
</script>
</head>
<body>
	<br>
	<%
		Subject currentUser = SecurityUtils.getSubject();
		out.println(currentUser.hasRole("R1001"));
		currentUser.getPrincipal();
	%>
	<shiro:principal />
	<br>
	<shiro:hasPermission name="news:create"> news:create </shiro:hasPermission>
	<shiro:hasPermission name="news:ivaild"> news:ivaild </shiro:hasPermission>
	<form id="signupForm" method="get" action="${ctx}/view/test.do">
		<p>
			<label for="firstname">Firstname</label> <input id="firstname"
				name="firstname" class="required form-control" />
		</p>
		<p>
			<label for="email">E-Mail</label> <input id="email" name="email"
				class="required email" />
		</p>
		<p>
			<label for="password">Password</label> <input id="password"
				name="password" type="password" class="{required:true,minlength:5}" />
		</p>
		<p>
			<label for="confirm_password">确认密码</label> <input
				id="confirm_password" name="confirm_password" type="password"
				class="{required:true,minlength:5,equalTo:'#password'}" />
		</p>
		<p>
			<input class="submit" type="submit" value="Submit" />
		</p>
	</form>
	<div class="alert alert-success" style="height: 100px;">...</div>
	<div class="alert alert-info" style="height: 100px;">...</div>
	<div class="alert alert-warning" style="height: 100px;">...</div>
	<div class="alert alert-danger" style="height: 100px;">...</div>


</body>
</html>