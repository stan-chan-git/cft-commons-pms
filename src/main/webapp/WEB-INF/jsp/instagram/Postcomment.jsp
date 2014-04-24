<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

</script>
</head>
<body>
<div style="width: 800px;padding-top: 30px;">			
            
           
			<div class="form-group">
				<div id="dd" class="col-sm-11" >
				<form action="createComment.do" method="post">
					<table id='wbTable' class='table table-bordered table-hover'
						style='margin-left: 40px'>

						<tr>
							<th>图片ID</th>
							<th>内容</th>
							<th>发布人</th>
							<th>类型</th>
							<th>图片</th>
							<th>写评论</th>
						</tr>
						<c:forEach var="names" items="${names }">
							<tr>
								<td><input type="text"  name="mediaId" value="${names.media_id}" /></td>
								<td>${names.title}</td>
								<td>${names.username}</td>
								<td>${names.type}</td>
								<td>${names.url}</td>
								<td><input type="text" name="comment" />
								    <input type="submit" value="评论">
								</td>
							</tr>
						</c:forEach>
					</table>
				</form>
			</div>
			</div>
			
</div>
</body>
</html>
