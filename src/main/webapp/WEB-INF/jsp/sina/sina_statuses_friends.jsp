<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>获取关注的人信息动态</title>
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/dist/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/js/jquery-validation/validate.css">
<script type="text/javascript"
	src="${ctx}/static/js/jquery-1.11.0.min.js"></script>
		
<style type="text/css">
#ShuRu {
	background-color: #F0FFFF;
}
</style>

<script type="text/javascript">
$(document).ready(function(){
  $("button").click(function(){
	  var sina_id = $("#sina_id").text();
	  window.location.href="/pms/sina/sina_statuses_repost2?sina_id="+sina_id;
  });
});
</script>

</head>

<body>

	<div id="ShuRu"
		style="border: 5px solid #3399FF; border-radius: 25px; moz-border-radius: 25px; width: 800px; height: 1120px; padding-top: 30px;">
		
			
			<div class="form-group">
				<label class="col-sm-4 control-label" style="color:blue">获取关注的人动态信息>>></label>
			</div>
			
			<div class="form-group">
				<div class="col-sm-11" >
					<table class="table table-bordered table-hover" style="margin-left:50px">
						<tr  align="center"  class="success">
							<td>好友动态的内容</td>
							<td>好友昵称</td>
							<td style="width:250px">好友动态发表时间</td>
							<td>转发按钮</td>
						</tr>
						<c:forEach items="${sinaDTOs}" var="sinaDTO"  end="10">
							<tr align="center"  class="warning">
								<td>${sinaDTO.text}</td>
								<td>${sinaDTO.screen_name}</td>
								<td>${sinaDTO.created_at}</td>
								<td><button type="submit" class="btn btn-default">Share</button></td>
								<td class="hidden"  id="sina_id">${sinaDTO.id}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>


	</div>
</body>
</html>