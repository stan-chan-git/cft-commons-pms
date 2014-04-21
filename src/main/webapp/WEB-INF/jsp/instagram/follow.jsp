<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

           

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
   
   
 <script type="text/javascript" src="${ctx}/static/js/jquery-1.11.0.min.js}"></script>

</head>
<body>

  <h1>获取关注的好友列表</h1>


       <a href="http://instagram.com/p/myoN3Yo7aN/">評論</a>
     <img src="http://origincache-prn.fbcdn.net/1516658_437535329716044_514212709_a.jpg">
		<table>


			<tr>
				<td>名字</td>
				<td>图片</td>
			</tr>
			<c:forEach var="name" items="${names }">
				<tr>
					<th>${name.username}</th>
					<th><img src="${name.photo}"></th>
				</tr>
			</c:forEach>
		</table>



	</h1>


</body>
</html>