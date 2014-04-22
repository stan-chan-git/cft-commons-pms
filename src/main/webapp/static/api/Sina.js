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