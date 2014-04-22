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
				console.log("已登录");
				
				var uid = response.authResponse.userID;
				var facebookToken = response.authResponse.accessToken;
				postFBmsg(msg,facebookToken,callback);
			} else {
				FB.login(function (response){
					
					if (response.status === 'connected') {
						console.log("回调");
						
						var facebookToken = response.authResponse.accessToken;
						postFBmsg(msg,facebookToken,callback);
					}
				});
			}
		});
}

//发布消息API调用
function postFBmsg(msg,facebookToken,callback){
	FB.api('/me/feed', 'post', {
		message : msg,
		access_token : facebookToken
	}, function(response) {
		if (!response || response.error) {
			console.log('Error occured:'+response.error);
			
			if(typeof(callback) != "undefined"){
				callback("Facebook发布失败!");
			}
		} else {
			console.log('Post ID: ' + response.id);
			
			if(typeof(callback) != "undefined"){
				callback("Facebook发布成功!");
			}

		}
	});
} 