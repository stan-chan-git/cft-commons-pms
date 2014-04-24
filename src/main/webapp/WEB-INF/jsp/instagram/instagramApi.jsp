<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<form action="create.do" role="form" method="post">
<div class="form-group">
	<span class="col-sm-3 label label-success">微博内容:</span>
	<div class="col-sm-8">
		<textarea id="content"  class="form-control" name="content" rows="5" cols="70">测试带图片的微博.......</textarea>
	</div>
</div>
<br><br>
<div class="form-group" style="padding-top:100px">
    <span class="col-sm-3 label label-success">图&nbsp;&nbsp;&nbsp;&nbsp;片:</span>
    <div class="col-sm-8">
    	<img style="width:200px;height:133px;" src="/pms/static/images/test.jpg" />
    </div>
</div>
<br>


<div class="form-group">
	<input id="send" class="btn btn-default" style="margin-left:115px;margin-top:20px" type="button" value="发送" />
</div>
</form>
   


</body>
</html>