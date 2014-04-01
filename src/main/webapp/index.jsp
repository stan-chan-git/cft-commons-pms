<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import="org.apache.shiro.SecurityUtils"%>
<%@ page import="org.apache.shiro.subject.Subject"%>

<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<br>
<%
Subject currentUser = SecurityUtils.getSubject();
out.println(currentUser.hasRole("R1001"));
currentUser.getPrincipal();



%>
<shiro:principal/>
<br>
<shiro:hasPermission name="news:create"> news:create </shiro:hasPermission>
<shiro:hasPermission name="news:ivaild"> news:ivaild </shiro:hasPermission>
</body>
</html>