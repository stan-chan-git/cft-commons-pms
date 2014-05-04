//获取好友最新动态
function instagramfriends(callback){
	$.post("/pms/instagram/followFirend.do",
			// 回调函数
			function(data){
		 		callback(data); 
			},
			// 返回数据类型
			"json");
}
//获取全部最新动态
function instagramfriends(callback){
	$.post("/pms/instagram/followFirend2.do",
			// 回调函数
			function(data){
		 		callback(data); 
			},
			// 返回数据类型
			"json");
}
