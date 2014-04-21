//发微博回调函数
function resultCallBack(data,msg){
	if(data == "success"){
		$("#info").append(msg+"发布成功!");
	}else{
		$("#info").append(msg+"发布失败!");
	}
}