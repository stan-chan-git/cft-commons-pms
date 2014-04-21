<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
$(function(){
	var tencent = $("#tencent");//腾讯微博复选框
	var sina = $("#sina");//新浪微博复选框
	var instagram = $("#instagram");//Instagram复选框
	var facebook = $("#facebook");//FaceBook复选框
	var send = $("#send");//发送按钮
	
	var tencent_label = $("#tencent_label");//Tencent label
	var sina_label = $("#sina_label");//Sina label
	var instagram_label = $("#instagram_label");//Instagram label
	var facebook_label = $("#facebook_label");//FaceBook label
	
	//发送按钮点击事件
	$("#send").click(function(){
		//获取发表内容
		var content = $("#content").text();
		
		
		//判断是否选择了平台
		if(tencent.is(":checked") == false && sina.is(":checked") == false && instagram.is(":checked") == false && facebook.is(":checked") == false){
			//先清空提示div
			$("#tip").remove();
			send.after("<div id='tip' class='alert alert-warning' style='margin-left:115px;margin-top:20px'>Please Select A Platform At Least!</div>");
			return false;
		}
		
		//判断是否进行了授权
		if(tencent.is(":checked") == true || sina.is(":checked") == true || instagram.is(":checked") == true || facebook.is(":checked") == true){
			if($("tencent_tip").text() != "" || $("sina_tip").text() != "" || $("instagram_tip").text() != "" || $("facebook_tip").text() != ""){
				$("#tip").remove();
				send.after("<div id='tip' class='alert alert-warning' style='margin-left:115px;margin-top:20px'>Please According To The Instructions!</div>");
				return false;
			}
		}
		
		window.location.href= "/pms/api/sendMessage.do?content="+content;
		
	});
	
////////////////////////////////////////////////////////////////////////////	
	/*  以下是复选框按钮点击事件，主要是用来移除提示div和判断平台是否授权  */
	tencent.click(function(){
		$("#tip").remove();
		$("#tencent_tip").remove();
		
		var tencent_token = "<%=session.getAttribute("tencent_token") %>";
		//复选框被选中,判断是否已经授权
		if(tencent.is(":checked") == true){
			if(tencent_token == null || tencent_token == "" ){
				tencent_label.after("<label id='tencent_tip' style='color:red'>请先授权!</label>")
			}
		}
	});
	
	sina.click(function(){
		$("#tip").remove();
		$("#sina_tip").remove();
		
		var sina_token = "<%=session.getAttribute("sina_token") %>";
		//复选框被选中,判断是否已经授权
		if(sina.is(":checked") == true){
			if(sina_token == null || sina_token == "" ){
				sina_label.after("<label id='sina_tip' style='color:red'>请先授权!</label>")
			}
		}
	});

	instagram.click(function(){
		$("#tip").remove();
		$("#instagram_tip").remove();
		
		var instagram_token = "<%=session.getAttribute("instagram_token") %>";
		//复选框被选中,判断是否已经授权
		if(instagram.is(":checked") == true){
			if(instagram_token == null || instagram_token == "" ){
				instagram_label.after("<label id='instagram_tip' style='color:red'>请先授权!</label>")
			}
		}
	});

	facebook.click(function(){
		$("#tip").remove();
		$("#facebook_tip").remove();
		
		var facebook_token = "<%=session.getAttribute("facebook_token") %>";
		//复选框被选中,判断是否已经授权
		if(facebook.is(":checked") == true){
			if(facebook_token == null || facebook_token == "" ){
				facebook_label.after("<label id='facebook_tip' style='color:red'>请先授权!</label>")
			}
		}
	});
	
});
</script>
</head>
<body>
<form action="#" role="form" method="post">
<div class="form-group">
<span class="col-sm-3 label label-success">微博内容:</span>
<div class="col-sm-8">
<textarea id="content"  class="form-control" name="content" rows="5" cols="70">这是测试微博.......</textarea>
</div>
</div>
<br>
<!-- 选择要发送的平台 -->
<div class="form-group" style="margin-left:115px;padding-top:100px">
<input type="checkbox" id="tencent" />&nbsp;<label class="label label-info" id="tencent_label">腾讯微博</label><br>
<input type="checkbox" id="sina" />&nbsp;<label class="label label-danger" id="sina_label">新浪微博</label><br>
<input type="checkbox" id="instagram" />&nbsp;<label class="label label-default" id="instagram_label">Instagram</label><br>
<input type="checkbox" id="facebook" />&nbsp;<label class="label label-primary" id="facebook_label">FaceBook</label><br>
</div>
<div class="form-group">
<input id="send" class="btn btn-default" style="margin-left:115px;margin-top:20px" type="button" value="发送" />
</div>
</form>
</body>
</html>