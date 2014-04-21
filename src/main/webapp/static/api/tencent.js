//发一条文字微博
function sendTencentWeiBo(content,callback){
	$.post("/pms/tencent/sendWeiBo.do",
			//参数
			{
		      content:content
			},
			//回调函数
			function(data){
			  callback(data,"腾讯微博");  
			},
			//返回数据类型
			"json");
}