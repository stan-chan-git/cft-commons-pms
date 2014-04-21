//发一条文字微博
function sendTencentWeiBo(content){
	$.post("/pms/tencent/sendWeiBo.do",
			
			{
		      content:content
			},
			
			function(data){
				console.log(data);
			},
			
			"json");
}