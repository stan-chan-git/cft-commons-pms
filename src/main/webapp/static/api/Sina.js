//发一条文字微博
function sinaStatusesUpdate(status){
	$.post("/pms/sina/sinaStatusesUpdate.do",
			// 参数
			{
				status:status
			},
			// 回调函数
			function(data){
			   if(data == "success"){
				   $("#info").append("新浪微博发布成功");
			   }else{
				   ${"#info"}.append("新浪微博发布失败");
			   }
			},
			// 返回数据类型
			"json");
}