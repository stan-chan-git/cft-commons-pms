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

/**
 * 发布一条文字带图片信息
 */
function writeNewPostPic(msg,callback){
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
			postFBmsgpic(msg,facebookToken,callback);
		} else {
			FB.login(function (response){
				
				if (response.status === 'connected') {
					console.log("回调");
					
					var facebookToken = response.authResponse.accessToken;
					postFBmsgpic(msg,facebookToken,callback);
				}
			});
		}
	});
}


//发布消息API调用
function postFBmsgpic(msg,facebookToken,callback){
	FB.api('/me/feed', 'post', {
		 message : msg
		,access_token : facebookToken
		,picture : "https://fbcdn-photos-d-a.akamaihd.net/hphotos-ak-prn2/t1.0-0/1491654_1397928103820640_3770911688229799288_s.jpg"
		,link : "https://www.facebook.com/photo.php?fbid=1397928103820640&set=a.1397927633820687.1073741827.100008103913868&type=1&relevant_count=1"
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