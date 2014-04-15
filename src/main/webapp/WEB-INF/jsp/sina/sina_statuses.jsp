<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>API调试测试</title>
</head>
<body>

	<div class="wiki_taglist">
		<ul class="clearfix">			
			<li><a href="#statuses/update">发布一条微博信息</a></li>
			<li><a href="#statuses/upload">上传图片并发布一条微博</a></li>
			<li><a href="#statuses/repost">转发一条微博信息</a></li>
			<li><a href="#statuses/destroy">删除微博信息</a></li>
		</ul>
	</div>
	
<a name="statuses/update">发布一条微博信息:</a>
<form action="https://api.weibo.com/2/statuses/update.json" method="post" >
access_token:<input type="hidden" name="access_token" value="${access_token}" readonly="readonly" /><br>
status:<input type="text" name="status" value="文字微博测试" /><br>
<!-- Common Param  -->
<input type="submit" value="发送" />
<hr>
</form>

<a name="statuses/upload">上传图片并发布一条微博:</a>
<form action="https://upload.api.weibo.com/2/statuses/upload.json" method="post" enctype="multipart/form-data">
access_token:<input type="hidden" name="access_token" value="${access_token}" readonly="readonly" /><br>
status:<input type="text" name="status" value="图片微博测试" /><br>
pic:<input type="file" name="pic" /><br>
<!-- Common Param  -->
<input type="submit" value="发送" />
<hr>
</form>

<a name="statuses/repost">转发一条微博信息:</a>
<form action="https://api.weibo.com/2/statuses/repost.json" method="post" >
access_token:<input type="hidden" name="access_token" value="${access_token}" readonly="readonly" /><br>
某条微博的id:<input type="text" name="id" value="3697795054009534" /><br>
<!-- Common Param  -->
<input type="submit" value="发送" />
<hr>
</form>

<a name="statuses/repost">删除微博信息:</a>
<form action="https://api.weibo.com/2/comments/destroy.json" method="post" >
access_token:<input type="hidden" name="access_token" value="${access_token}" readonly="readonly" /><br>
某条微博的id:<input type="text" name="id" value="3699288532253942" /><br>
<!-- Common Param  -->
<input type="submit" value="发送" />
<hr>
</form>

</body>
</html>