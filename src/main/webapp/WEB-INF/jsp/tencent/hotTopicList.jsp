<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTFF-8">
<title></title>
<script type="text/javascript">
$(function(){
  function hiddeTip(){
	  $("#tip").hidden();
  }
})
</script>
</head>
<body>
<c:choose>
	<c:when test="${message != null && message != ''}">
		${message}
	</c:when>
	<c:otherwise>
	    <div style="width:800px">
			<table class="table table-bordered table-hover">
				<tr align="center">
					<td width="100px">序号</td>
					<td width="175px">搜索关键字</td>
					<td width="175px">话题关键字</td>
					<td width="100px">收藏数</td>
					<td width="100px">微博数</td>
					<td width="130px">操作</td>
				</tr>
				
				<c:forEach var="td" items="${tdList}" varStatus="status">
				    <tr align="center">
					    <td width="100px">${status.index + 1}</td>
					    <td width="175px">${td.keywords}</td>
					    <td width="175px">${td.name}</td>
					    <td width="100px">${td.favnum}</td>
					    <td width="100px">${td.tweetnum}</td>
					    <td width="130px">
					        <a href="${pageContext.request.contextPath}/view/sendTopicWeiBoView.do?keywords=${td.keywords}">发微博</a>
					    </td>
				    </tr>
				</c:forEach>
			</table>
		</div>
    </c:otherwise>
</c:choose>
</body>
</html>