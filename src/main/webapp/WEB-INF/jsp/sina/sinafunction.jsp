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
			<li><a href="#.E7.B2.89.E4.B8.9D.E6.9C.8D.E5.8A.A1.E6.8E.A5.E5.8F.A3">粉丝服务接口</a></li>
                        <li><a href="#.E9.82.80.E8.AF.B7.E6.8E.A5.E5.8F.A3">邀请接口</a></li>
                        <li><a href="#.E5.BE.AE.E5.8D.9A">微博接口</a></li>
			<li><a href="#.E8.AF.84.E8.AE.BA">评论接口</a></li>
			<li><a href="#.E7.94.A8.E6.88.B7">用户接口</a></li>
                        <li><a href="#.E7.BD.AE.E9.A1.B6.E5.BE.AE.E5.8D.9A">置顶微博接口</a></li>
			<li><a href="#.E5.85.B3.E7.B3.BB">关系接口</a></li>
                        <li><a href="#.E5.A5.BD.E5.8F.8B.E5.88.86.E7.BB.84">好友分组接口</a></li>
			<li><a href="#.E8.B4.A6.E5.8F.B7">账号接口</a></li>
			<li><a href="#.E6.94.B6.E8.97.8F">收藏接口</a></li>
			<li><a href="#.E8.AF.9D.E9.A2.98">话题接口</a></li>
                        <li><a href="#.E5.BE.AE.E5.8D.9A.E6.A0.87.E7.AD.BE">微博标签接口</a></li>
			<li><a href="#.E7.94.A8.E6.88.B7.E6.A0.87.E7.AD.BE">用户标签接口</a></li>
			<li><a href="#.E6.B3.A8.E5.86.8C">注册接口</a></li>
			<li><a href="#.E6.90.9C.E7.B4.A2">搜索接口</a></li>
			<li><a href="#.E6.8E.A8.E8.8D.90">推荐接口</a></li>
			<li><a href="#.E6.8F.90.E9.86.92">提醒接口</a></li>
			<li><a href="#.E7.9F.AD.E9.93.BE">短链接口</a></li>
			<li><a href="#.E9.80.9A.E7.9F.A5">通知接口</a></li>
			<li><a href="#.E5.85.AC.E5.85.B1.E6.9C.8D.E5.8A.A1">公共服务接口</a></li>
			<li><a href="#.E4.BD.8D.E7.BD.AE.E6.9C.8D.E5.8A.A1">位置服务接口</a></li>
			<li><a href="#.E5.9C.B0.E7.90.86.E4.BF.A1.E6.81.AF">地理信息接口</a></li>
			<li><a href="/wiki/%E7%A4%BE%E4%BA%A4%E7%94%B5%E8%A7%86" title="社交电视">社交电视接口</a></li>
			<li><a href="/wiki/%E8%A7%86%E9%A2%91%E4%B8%8A%E4%BC%A0" title="视频上传">视频上传</a></li>
                       <li><a href="#OAuth2">OAuth 2.0授权接口</a></li>
                       <li><a href="/wiki/Map-JS" title="Map-JS">地图引擎接口</a></li>
                       <li><a href="/wiki/%E6%94%AF%E4%BB%98" title="支付" class="mw-redirect">支付接口</a></li>
		</ul>
	</div>
发送文字微博:
<form action="https://api.weibo.com/2/statuses/update.json" method="post" >
access_token:<input type="text" name="access_token" value="${access_token}" readonly="readonly" /><br>
status:<input type="text" name="status" value="文字微博测试" /><br>
<!-- Common Param  -->
<input type="submit" value="发送" />
<hr>
</form>

发送图片微博:
<form action="https://upload.api.weibo.com/2/statuses/upload.json" method="post" enctype="multipart/form-data">
access_token:<input type="text" name="access_token" value="${access_token}" readonly="readonly" /><br>
status:<input type="text" name="status" value="图片微博测试" /><br>
pic:<input type="file" name="pic" /><br>
<!-- Common Param  -->
<input type="submit" value="发送" />
<hr>
</form>

回复某条微博的评论:
<form action="https://api.weibo.com/2/comments/reply.json" method="post" >
access_token:<input type="text" name="access_token" value="${access_token}" readonly="readonly" /><br>
id:<input type="text" name="id" value="3697795054009534" /><br>
cid:<input type="text" name="cid" value="3697800766048920" /><br>
comment:<input type="text" name="comment" value="回复你啊" /><br>
<!-- Common Param  -->
<input type="submit" value="发送" />
<hr>
</form>

转发某条微博:
<form action="https://api.weibo.com/2/statuses/repost.json" method="post" >
access_token:<input type="text" name="access_token" value="${access_token}" readonly="readonly" /><br>
id:<input type="text" name="id" value="3697795054009534" /><br>
<!-- Common Param  -->
<input type="submit" value="发送" />
<hr>
</form>

获取粉丝列表:
<form action="https://api.weibo.com/2/friendships/followers.json" method="get" >
access_token:<input type="text" name="access_token" value="${access_token}" readonly="readonly" /><br>
uid:<input type="text" name="uid" value="${uid}" readonly="readonly" /><br>
<!-- Common Param  -->
<input type="submit" value="发送" />
<hr>
</form>

</body>
</html>