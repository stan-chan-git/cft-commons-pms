<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<table width="100%" border="0">
<tr align="center">
<td>评论Id</td>
<td>评论时间</td>
<td>评论内容</td>
<td>评论人</td>
</tr>
<c:forEach items="${reList}" var="re">
<tr align="center">
<td width="30%">${re.id}</td>
<td width="30%">${re.timestamp}</td>
<td width="20%">${re.text}</td>
<td width="20%">${re.nick}</td>
</tr>
</c:forEach>
</table>
</body>
</html>