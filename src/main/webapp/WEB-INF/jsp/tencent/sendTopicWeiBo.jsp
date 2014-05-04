<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发送一条微博消息</title>
</head>
<body>
<div style="width:400px;height:200px;border:2px solid #7CFC00;background-color:#E0FFFF">
	<form action="${pageContext.request.contextPath}/tencent/sendWeiBo.do" role="form" method="post">
		<div class="form-group">
				<label class="col-sm-3 control-label">微博内容:</label>
			<div class="col-sm-9">
				<textarea  class="form-control" name="content" rows="5" cols="60">${keywords}</textarea>
			</div>
		</div>
		<div class="form-group">
			<input class="btn btn-default" style="margin-left:115px;margin-top:20px" type="submit" value="发送" />
		</div>
	</form>
</div>
</body>
</html>