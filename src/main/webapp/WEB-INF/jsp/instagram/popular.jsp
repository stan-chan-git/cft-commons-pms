<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<script type="text/javascript">
	
</script>

</head>
<body>


	<div id="dd" class="col-sm-11">
		<table id="wbTable" class="table" style="argin-left: 40px">

			<tr>
				<th>发布人</th>
				<th>发布时间</th>
				<th>照片</th>
			</tr>
			<c:forEach items="${popularList }" var="popularList">
				<tr>
					<td>${popularList.author_name}</td>
					<td></td>
					<td><img width="80px" height="80px" src="${popularList.url }"></td>
				</tr>
			</c:forEach>
		</table>
	</div>



</body>
</html>