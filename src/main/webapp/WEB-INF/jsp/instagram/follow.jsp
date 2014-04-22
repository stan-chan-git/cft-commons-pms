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


      
    
	<table  border="1" style="width:3cm;height:3cm;">
         <tr align="center">
             <th>标题</th>
            <th>好友名字</th>
            <th>好友头像</th>
			<th>发布的类型</th>
			<th >图像</th>
			
		</tr>
		<c:forEach var="names" items="${names }">
			<tr>
                <td>${names.title }</td>
                <td>${names.username}</td>
                 <td><img alt=""  src="${names.photo}" width="50px" height="50px"></td>
				<td>${names.type}</td>
				<td style="width:3cm;height:3cm;"><img alt=""  src="${names.url}" width="50px" height="50px"></td>
				
			</tr>
		</c:forEach>
	</table>



	


</body>
</html>