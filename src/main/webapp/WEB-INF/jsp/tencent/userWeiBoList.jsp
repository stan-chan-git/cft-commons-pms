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
<c:forEach items="${wbList}" var="wb">
<div style="width:300px;height:200px;border:2px solid #7CFC00;background-color:#E0FFFF">
<label>昵称:</label>${wb.nick}
<br>
<label>微博内容:</label>${wb.text}
<c:if test="${wb.origtext != null && wb.origtext != ''}">
<div style="width:200px;height:75px;background-color:#DBDBDB;border:2px solid #D9D9D9;margin-left:50px">
${wb.origtext}
</div>
</c:if>
<br>
<c:if test="${state == 'comments'}">
<a href="${pageContext.request.contextPath}/tencent/getReview.do?rootid=${wb.id}">查看评论</a>
</c:if>
<c:if test="${state == 'forward'}">
<a href="${pageContext.request.contextPath}/view/toForwardWeiBo.do?reid=${wb.id}&orgitext=${wb.text}">转发</a>
</c:if>
</div>
<br>
</c:forEach>
</body>
</html>