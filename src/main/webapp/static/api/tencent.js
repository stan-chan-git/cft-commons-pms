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
	
//获取好友最新动态(即最新微博)
function getFocusPeopleWeiBo(callback){
	$.post("/pms/tencent/getFocusPeopleWeiBo.do",
			
		   function(data){
		      callback(data); 
	       },
	
		   //返回的数据类型
	       "json");
}


//分享
function tencentShare(tencent_title,tencent_pic,tencent_url){ 
	tencent_title = encodeURIComponent(tencent_title);
	tencent_url = encodeURIComponent(tencent_url);
	
	var pic = [];
	var url = "http://share.v.t.qq.com/index.php?c=share&a=index&f=f1&appkey=801500590&url="+tencent_url+"&title="
		      + tencent_title + "&pic=";
	
	if(tencent_pic.length == 1){
		url = url + encodeURIComponent(tencent_pic[0]);
	}else if(tencent_pic.length > 1){
		
		for(var i = 0 ; i < tencent_pic.length ; i++){
			pic.push(encodeURIComponent(tencent_pic[i]));
		}
		
		url = url + pic.join("|");
	}
	
	window.open(url);
}
