<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Write New Post</title>
<script type="text/javascript"
	src="http://connect.facebook.net/en_US/all.js"></script>
<script>
	
	function writeNewPost(){
		
			FB.init({
				appId : '137410796429161',
				status : true,
				xfbml : true
			});
			
			FB.getLoginStatus(function(response) {
				if (response.status === 'connected') {
					alert("已登录");
					var uid = response.authResponse.userID;
					var facebookToken = response.authResponse.accessToken;
					postFBmsg(document.getElementById("msg").value,facebookToken);
				} else {
					FB.login(function (response){
						
						if (response.status === 'connected') {
							alert("回调");
							var facebookToken = response.authResponse.accessToken;
							postFBmsg(document.getElementById("msg").value,facebookToken);
						}
					});
				}
			});
	
	}
	
	function postFBmsg(msg,facebookToken){
		FB.api('/me/feed', 'post', {
			message : msg,
			access_token : facebookToken
		}, function(response) {
			if (!response || response.error) {
				alert('Error occured:'+response.error);
			} else {
				alert('Post ID: ' + response.id);
			}
		});
	} 
	
	/* function caaAPI(cllback){
		FB.getLoginStatus(function(response) {
			if (response.status === 'connected') {
				alert("已登录");
				var uid = response.authResponse.userID;
				var facebookToken = response.authResponse.accessToken;
				postFBmsg(document.getElementById("msg").value,facebookToken);
			} else {
				FB.login(function (response){
					
					if (response.status === 'connected') {
						alert("回调");
						var facebookToken = response.authResponse.accessToken;
						postFBmsg(document.getElementById("msg").value,facebookToken);
					}
				});
			}
		});
	} */
	 
	/* 
	
	function test(msg,callback){
		alert(1);
		if(undefined != callback)
			callback("ok");
		alert(msg+call);
	}
	
	test("ss",function(data){
		alert(date);
	}); 
	//依次输出：1，ok，ssok
	 */
	
</script>
</head>

<body>
	<div
		style="width: 400px; height: 200px; border: 2px solid #7CFC00; background-color: #E0FFFF">
		<textarea  class="form-control" id="msg" rows="5" cols="60">这是测试微博.......</textarea>
		<button onclick="writeNewPost();">Public</button>
	</div>
	<div id="fb-root"></div>
</body>
</html>