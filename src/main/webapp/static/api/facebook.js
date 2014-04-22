/**
 * 发布一条文字信息
 */
function writeNewPost(msg,callback){
		//与其他平台不一样，用js发布消息
		FB.init({
			appId : '137410796429161',
			status : true,
			xfbml : true
		});
		//检验是否登录
		FB.getLoginStatus(function(response) {
			if (response.status === 'connected') {
				alert("已登录");
				var uid = response.authResponse.userID;
				var facebookToken = response.authResponse.accessToken;
				postFBmsg(msg,facebookToken);
			} else {
				FB.login(function (response){
					
					if (response.status === 'connected') {
						alert("回调");
						var facebookToken = response.authResponse.accessToken;
						postFBmsg(msg,facebookToken);
					}
				});
			}
		});
		
		//回调函数
		function(data){
		  if(typeof(callback) != "undefined"){
			  if(data == "success"){
				  callback("Facebook发布成功!");
			  }else{
				  callback("Facebook发布失败!");
			  }
		  }
		}
}

//发布消息API调用
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