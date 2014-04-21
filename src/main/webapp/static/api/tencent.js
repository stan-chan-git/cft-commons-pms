//发一条文字微博
function sendTencentWeiBo(content){
	$.post("/pms/tencent/sendWeiBo.do",
			//参数
			{
		      content:content
			},
			//回调函数
			function(data){
			   if(data == "success"){
				   $("#info").append("腾讯微博发布成功");
			   }else{
				   ${"#info"}.append("腾讯微博发布失败");
			   }
			},
			//返回数据类型
			"json");
}