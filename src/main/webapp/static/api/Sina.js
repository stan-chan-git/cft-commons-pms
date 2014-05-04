
//发一条文字微博
function sinaStatusesUpdate(status,callback){
	$.post("/pms/sina/sinaStatusesUpdate.do",
			// 参数
			{
				status:status
			},
			// 回调函数
			function(data){
			   if(typeof(callback) != "undefined"){
				   if(data == "success"){
					   callback("新浪微博发布成功!");
				   }else{
					   callback("新浪微博发布失败!");
				   }
			   }   
			},
			// 返回数据类型
			"json");
}

//发一条含有图片的微博
function sinaStatusesUpload(status,callback){
	$.post("/pms/sina/sinaStatusesUpload.do",
			// 参数
			{
				status:status
			},
			// 回调函数
			function(data){
			   if(typeof(callback) != "undefined"){
				   if(data == "success"){
					   callback("新浪微博发布成功!");
				   }else{
					   callback("新浪微博发布失败!");
				   }
			   }   
			},
			// 返回数据类型
			"json");
}

//获取好友最新动态
function sinaCommentsfriends(callback){
	$.post("/pms/sina/sinaStatuseFriends.do",
			// 回调函数
			function(data){
		 		callback(data); 
			},
			// 返回数据类型
			"json");
}

//获取话题
function sinaTrends(time,callback){
	$.post("/pms/sina/sinaTrends.do",
			//参数
			{
				time:time
			},
			// 回调函数
			function(data){
		 		callback(data); 
			},
			// 返回数据类型
			"json");
}

//分享到sina微博
function sinaShare(sinaShareContent,sinaSharePics,sinaShareSource){
	var sinaApi = 'http://v.t.sina.com.cn/share/share.php?';
	var sinaUrl = ['url=',encodeURIComponent(sinaShareSource)].join('');
	var sinaText = ['&title=',encodeURIComponent(sinaShareContent),'&appkey=4281626272'].join('');
	
	/**
	 * 数组图片处理
	 */
	var sinaTempPic = '';
	var sinaPic = '';
	if(sinaSharePics!=null) {
		for(var i = 0 ; i < sinaSharePics.length ; i++) {
			sinaTempPic = [sinaTempPic,sinaSharePics[i],'||'].join('');
		}
		sinaTempPic = [sinaTempPic,sinaSharePics[sinaSharePics.length]].join('');
	    sinaPic = ['&pic=',sinaTempPic].join('');
	}else{
		sinaPic = '&pic='
	}	
	
	var sinaTemp = [sinaApi,sinaUrl,sinaText,sinaPic,sinaTemp].join('');
	window.open(sinaTemp);
}