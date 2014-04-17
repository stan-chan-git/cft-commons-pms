<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传图片并发布一条微博</title>
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/dist/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/js/jquery-validation/validate.css">
<style type="text/css">
#ShuRu {
	background-color: #F0FFFF;
}
</style>
</head>

<body>

	<div id="ShuRu"
		style="border: 5px solid #3399FF; border-radius: 25px; moz-border-radius: 25px; width: 600px; height: 400px; padding-top: 30px;">
		<form class="form-horizontal" role="form" action="https://upload.api.weibo.com/2/statuses/upload.json" method="post"  enctype="multipart/form-data">
			
			<div class="form-group">
				<label class="col-sm-4 control-label" style="color:blue">上传图片并发布一条微博>>></label>
			</div>
			
			<div class="form-group">
				<label class="col-sm-3 control-label">access_token</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" name="access_token" value="${sina_token}">
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label">Content</label>
				<div class="col-sm-8" >
					<textarea class="form-control" rows="3" name="status" >新浪微博图片信息发布测试！</textarea>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-3 control-label">Picture</label>
				<div class="col-sm-8" >
					<input type="file" name="pic" >
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-9 ">
					<button type="submit" class="btn btn-default">Send</button>
				</div>
			</div>

		</form>
	</div>
</body>
</html>