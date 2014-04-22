/**
 * 发布一条文字信息
 */
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