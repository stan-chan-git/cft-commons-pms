<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>获取评论</title>
</head>
<body>
   
   <h1>获取評論列表</h1>




	<table  border="1">
         <tr align="center">
            <th>链接</th>
            <th>图片</th>
			<th>评论人名字</th>
			<th>评论</th>
		</tr>
		<c:forEach var="comment" items="${comments }">
			<tr>
                <td><a href="${comment.link}">链接</a></td>
                <td><img src="${comment.profile_picture}"></td>
				<td>${comment.commentName}</td>
				<td>${comment.text}</td>
			</tr>
		</c:forEach>
	</table>



</body>
</html>