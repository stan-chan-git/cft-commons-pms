//发一条文字微博
function sinaStatusesUpdate(status){
	$.post("/pms/sina/sinaStatusesUpdate.do",
			// 参数
			{
				status:status
			},
			// 回调函数
			function(data){
			   if("undefined" != callback){
				   if(data == "success"){
					   callback("新浪微博发布成功");
				   }else{
					   callback("新浪微博发布失败");
				   }
			   }   
			},
			// 返回数据类型
			"json");
}