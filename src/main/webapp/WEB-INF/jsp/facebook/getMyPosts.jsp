<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Your Friends</title>
<style type="text/css">
.w1{
    width:300px;
   }
</style>

<script type="text/javascript">
<!-- facebook分享点赞必须script -->
(function(d, s, id) {
	  var js, fjs = d.getElementsByTagName(s)[0];
	  if (d.getElementById(id)) return;
	  js = d.createElement(s); js.id = id;
	  js.src = "//connect.facebook.net/zh_CN/sdk.js#xfbml=1&version=v2.0";
	  fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));
	
$(function(){
	 var facebook_token = "<%=session.getAttribute("facebook_token") %>";
	
	 var wbTable = $("#wbTable");//插入数据的表格
	 var auth_msg = $("#auth_msg");//是否授权提示消息
	 
	 if((facebook_token != "null" && facebook_token != "")){
		wbTable.append("<tr align='center' class='success'>" +
						"<td style='display:none'>微博ID</td>" +
						"<td class='w1'>微博内容</td>" +
						"<td>发表时间</td>" +
						"<td ></td>" +
			   			"</tr>");
	 }else{
		 auth_msg.empty();
		 auth_msg.append("Facebook还未进行授权,不能获取我的消息!<br><br>");
		 auth_msg.show();
		 
		 return false;
	 }

	//发出请求前先判断Facebook是否授权
	 if(facebook_token != "null" && facebook_token != ""){
		//facebook--获取好友列表函数               
		getMyAllPosts(function(data){
							if(data != "empty"){
					            var obj = JSON.parse(data);
					            $.each(obj,function(i){
				            		if(obj[i].images == "null"){
					            		wbTable.append("<tr align='center'>" +
					            		               "<td style='display:none'>"+ obj[i].id +"</td>" +
					            		               "<td>"+ obj[i].content +"</td>" +
					            		               "<td>"+ obj[i].time +"</td>" +
					            		               "<td><div class='fb-like' data-href='https://www.facebook.com/"+ obj[i].paraPostId0 +"/posts/"+ obj[i].paraPostId1 +"' data-layout='standard' data-action='like' data-show-faces='true' data-share='true'></div>" +
					            		               "<br><a>share("+ obj[i].shareNum+")</a>"+
					            		               "<br><a>comment()</a></td>" +
					            		               "</tr>");
				            		}else{
				            			wbTable.append("<tr align='center'>" +
		                    		               "<td style='display:none'>"+ obj[i].id +"</td>" +
		                    		               //将图片显示在文字下方
		                    		               "<td>"+ obj[i].content +
		                    		                    "<br><img src="+ obj[i].images +" />"+
		                    		               "</td>" +
		                    		               "<td>"+ obj[i].time +"</td>" +
		                    		               "<td><div class='fb-like' data-href='https://www.facebook.com/"+ obj[i].paraPostId0 +"/posts/"+ obj[i].paraPostId1 +"' data-layout='standard' data-action='like' data-show-faces='true' data-share='true'></div>" +
		                    		               "<br><a>share("+ obj[i].shareNum+")</a>"+
		                    		               "<br><a>comment()</a></td>" +
		                    		               "</tr>");
				            		}
				            	});
					         }else{
					            data_msg.append("您目前没有消息");	
					            data_msg.show();
					         }
		});
	}else{
		auth_msg.append("Facebook还未进行授权,不能获取我的消息!<br>");
		auth_msg.show();
	}
})

function getMyAllPosts(callback){
	$.post("/pms/facebook/getMyPosts2.do",
		   function(data){
		      callback(data); 
	       },
		   //返回的数据类型
	       "json");
}
</script>
</head>
<body>
<div class="fb-like" data-href="https://www.facebook.com/100008103913868/posts/1414768545469929" data-layout="standard" data-action="like" data-show-faces="true" data-share="true"></div>
<div style="width:800px;padding-top: 30px;">			
            
            <span id="auth_msg" class="alert alert-danger" style="display:none;float:left;margin-left:250px"></span>
            <br>
            <span id="data_msg" class="alert alert-info" style="display:none;float:left;margin-left:280px"></span>		
			
			<div class="form-group">
				<div id="dd" class="col-sm-11" >
					<table id="wbTable" class="table" style="argin-left:40px">
					</table>
				</div>
			</div>
			
</div>
<div id="fb-root"></div>
</body>
</html>
