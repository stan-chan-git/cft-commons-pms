<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Share Post</title>
<script type="text/javascript"
	src="http://connect.facebook.net/en_US/all.js"></script>
<script type="text/javascript">
	function sharePost(){
		FB.init({
			appId : '137410796429161',
			status : true,
			cookie : true,
			xfbml : true
		});
		
		FB.ui({
             method: 'feed',
             name: $("#wenzi").text(),//分享的内容（文字）
             link: 'http://localhost:8080/pms/facebook/sharePost.do',//分享的链接
             picture: 'http://www.ttcnn.com/jpg/img/jpg10/201202183.jpg',
             caption: 'showcase',
             description: 'myApp'
         },
         function (response) {
             if (response && response.post_id) {
           	  alert('Posting completed.');
             } else {
           	  alert('Error while posting.');
             }
         });
	}
</script>
</head>

<body>
	<div
		style="width: 600px; height: 300px;">
		<div align="center" style="background-color: green;color: white;">
			分享文字：
		</div>
		<br>
		<span id="wenzi" style="padding-left:10px">#分享 品味生活，完善人性。存在就是机会，思考才能提高。人需要不断打碎自己，更应该重新组装自己。</span>
		<br>
		<img style="width:315px;height:210px" src="http://www.ttcnn.com/jpg/img/jpg10/201202183.jpg">
		<div>
			share to ：
	  		<a
				title="share to facebook" 
				target="_blank" 
				onclick="sharePost();">
				<img style="width:18px;height:19px" src="${pageContext.request.contextPath}/static/images/facebook.jpg">
			</a>
		</div>
	</div>
	<div id="fb-root"></div>
</body>
</html>