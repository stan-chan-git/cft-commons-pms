//发一条文字微博
function sendTencentWeiBo(content,callback){
	$.post("/pms/tencent/sendWeiBo.do",
			//参数
			{
		      content:content
			},
			//回调函数
			function(data){
			  if(typeof(callback) != "undefined"){
				  if(data == "success"){
					  callback("腾讯微博发布成功!");
				  }else{
					  callback("腾讯微博发布失败!");
				  }
			  }
			},
			//返回数据类型
			"json");
}

//发一条带图片的微博
function sendTencentPicWeiBo(content,callback){
	$.post("/pms/tencent/sendPicWeiBo.do",
			
			//参数
			{
		      content:content
			},
			
			//回调函数
			function(data){
				if(typeof(callback) != "undefined"){
					callback("腾讯微博发布成功!");
				}else{
					callback("腾讯微博发布失败!");
				}
			},
			
		    //返回的数据类型
	        "json");
}