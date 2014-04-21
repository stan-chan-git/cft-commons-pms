<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<form action="${pageContext.request.contextPath}/api/sendMessage.do" role="form" method="post">
<div class="form-group">
<label class="col-sm-3 label label-info">微博内容:</label>
<div class="col-sm-9">
<textarea  class="form-control" name="content" rows="5" cols="60">这是测试微博.......</textarea>
</div>
</div>
<br>
<!-- 选择要发送的平台 -->
<div style="margin-left:115px;padding-top:100px">
<span><input type="checkbox" /></span>&nbsp;<label class="label label-info">腾讯微博</label><br>
<input type="checkbox" />&nbsp;<label class="label label-danger">新浪微博</label><br>
<input type="checkbox" />&nbsp;<label class="label label-default">Instagram</label><br>
<input type="checkbox" />&nbsp;<label class="label label-primary">FaceBook</label><br>
</div>
<div class="form-group">
<input class="btn btn-default" style="margin-left:115px;margin-top:20px" type="submit" value="发送" />
</div>
</form>
</body>
</html>