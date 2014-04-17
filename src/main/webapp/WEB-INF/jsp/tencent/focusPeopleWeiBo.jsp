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
<c:forEach items="${focusList}" var="f">
<div style="width:300px;height:150px;border:2px solid #7CFC00;background-color:#E0FFFF">
<label>昵称:</label>${f.nick}
<br>
<label>微博内容:</label>${f.text}
<c:if test="${f.origtext != null && f.origtext != ''}">
<div style="width:200px;height:75px;background-color:#DBDBDB;border:2px solid #D9D9D9;margin-left:50px">
${f.origtext}
</div>
</c:if>
</div>
<br>
</c:forEach>
</body>
</html>