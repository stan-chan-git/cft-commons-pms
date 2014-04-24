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

//发布文字消息API调用
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
	//初始化
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


//发布带图片消息API调用
function postFBmsgpic(msg,facebookToken,callback){
	FB.api('/me/feed', "POST",
    {
		"message":msg
		,"link": "https://www.facebook.com/photo.php?fbid=1397928103820640&set=a.1397927633820687.1073741827.100008103913868&type=1&relevant_count=1"
        ,"access_token":facebookToken
           // "link":"https://fbcdn-sphotos-d-a.akamaihd.net/hphotos-ak-prn2/t1.0-9/1491654_1397928103820640_3770911688229799288_n.jpg"
    }, function(response) {
		if (!response || response.error) {
			alert('Error occured:'+response.error.message);
			
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
 * 获取好友动态
 */
function facebookFriendsDyn(callback){
	alert("i come in facebook.js");
	$.post("/pms/facebook/facebookFriendsDyn.do",
		
		   function(data){
		      callback(data); 
	       },
	
		   //返回的数据类型
	       "json");
}