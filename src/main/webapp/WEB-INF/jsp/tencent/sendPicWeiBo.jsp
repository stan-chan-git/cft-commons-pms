<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发送一条微博消息</title>
</head>
<body>
<form action="https://open.t.qq.com/api/t/add_pic" role="form" method="post" enctype="multipart/form-data">
<div class="form-group">
<label>微博内容:</label>
<textarea class="form-control" name="content" rows="5" cols="40">这是带图片的测试微博.......</textarea>
<br>
<input type="file" name="pic" />
</div>

<!-- 请求需要的参数  -->
<input type="hidden" name="format" value="json" />
<input type="hidden" name="clientip" value="${clientip}" />
<input type="hidden" name="access_token" value="${sessionScope.tencent_token}" />
<input type="hidden" name="openid" value="${sessionScope.openid}" />
<input type="hidden" name="openkey" value="${sessionScope.openkey}" />
<input type="hidden" name="oauth_consumer_key" value="801495189" />
<input type="hidden" name="oauth_version" value="2.a" />
<input type="hidden" name="scope" value="all" />

<div class="form-group">
<input class="btn btn-default" type="submit" value="发送" />
</div>
</form>
</body>
</html>