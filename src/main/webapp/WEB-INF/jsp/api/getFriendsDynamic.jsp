<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.w1{
    width:200px;
   }
   
.w2{
    width:120px;
   }
</style>

<script type="text/javascript">
$(function(){
	 var tencent_token = "<%=session.getAttribute("tencent_token") %>";
	 var sina_token = "<%=session.getAttribute("sina_token") %>";
	 var facebook_token = "<%=session.getAttribute("facebook_token") %>";
	 var instagram_token = "<%=session.getAttribute("instagram_token") %>";
	
	 var wbTable = $("#wbTable");//插入数据的表格
	 var auth_msg = $("#auth_msg");//是否授权提示消息
	 var data_msg = $("#data_msg");//是否有最新动态提示消息
	 
	 if((tencent_token != "null" && tencent_token != "") || (sina_token != "null" && sina_token != "") || (instagram_token != "null" && instagram_token != "") || (facebook_token != "null" && facebook_token != "")){
		wbTable.append("<tr align='center' class='success'>" +
								"<td style='display:none'>微博ID</td>" +
							    "<td class='w2'>来源</td>" +
								"<td class='w1'>发表时间</td>" +
								"<td class='w2'>发表人</td>" +
								"<td>微博内容</td>" +
					   "</tr>");
	 }else{
		 auth_msg.empty();
		 auth_msg.append("腾讯微博还未进行授权,不能获取好友动态!<br><br>");
		 auth_msg.append("新浪微博还未进行授权,不能获取好友动态!<br><br>");
		 auth_msg.append("FaceBook还未进行授权,不能获取好友动态!<br><br>");
		 auth_msg.append("Instagram还未进行授权,不能获取好友动态!");
		 auth_msg.show();
		 
		 return false;
	 }
	 
	 //发出请求前先判断腾讯微博是否授权
	 if(tencent_token != "null" && tencent_token != ""){
		 //腾讯微博--获取好友最新动态函数
		 getFocusPeopleWeiBo(function(data){
			                    if(data != "empty"){
			                    	var obj = JSON.parse(data);
			                      
			                    	$.each(obj,function(i){
			                    		//判断是否带图片
			                    	//console.log(obj[i].images);
			                    		if(obj[i].images == "null"){
				                    		wbTable.append("<tr align='center'>" +
				                    		               "<td style='display:none'>"+ obj[i].id +"</td>" +
				                    		               "<td>来自腾讯微博</td>" +
				                    		               "<td>"+ obj[i].time +"</td>" +
				                    		               "<td>"+ obj[i].name +"</td>" +
				                    		               "<td>"+ obj[i].content +"</td>" +
				                    		               "</tr>");
			                    		}else{
			                    			wbTable.append("<tr align='center'>" +
			                    		               "<td style='display:none'>"+ obj[i].id +"</td>" +
			                    		               //将图片显示在文字下方
			                    		               "<td>来自腾讯微博</td>" +
			                    		               "<td>"+ obj[i].time +"</td>" +
			                    		               "<td>"+ obj[i].name +"</td>" +
			                    		               "<td>"+ obj[i].content +
		                    		                    "<br><img src="+ obj[i].images + "/160" +" />"+
		                    		                   "</td>" +
			                    		               "</tr>");
			                    		}
			                    	});
			                    	
			                    }else{
			                    	data_msg.append("您的腾讯微博的好友目前没有动态!<br>");
			                    	data_msg.show();
			                    }
		                    });
	 }else{
		 auth_msg.append("腾讯微博还未进行授权,不能获取好友动态!<br>");
		 auth_msg.show();
	 }
	 
	//发出请求前先判断新浪微博是否授权
	if(sina_token != "null" && sina_token != ""){
		//新浪微博--获取好友最新动态函数                
		sinaCommentsfriends(function(data){
							   if(data != "empty"){
					            	var obj = JSON.parse(data);
					           
					            	$.each(obj,function(i){
					            		if(obj[i].images == "null"){
						            		wbTable.append("<tr align='center'>" +
						            		               "<td style='display:none'>"+ obj[i].id +"</td>" +
						            		               "<td>来自新浪微博</td>" +
						            		               "<td>"+ obj[i].time +"</td>" +
						            		               "<td>"+ obj[i].name +"</td>" +
						            		               "<td>"+ obj[i].content +"</td>" +
						            		               "</tr>");
					            		}else{
					            			wbTable.append("<tr align='center'>" +
			                    		               "<td style='display:none'>"+ obj[i].id +"</td>" +
			                    		               "<td>来自新浪微博</td>" +
			                    		               "<td>"+ obj[i].time +"</td>" +
			                    		               "<td>"+ obj[i].name +"</td>" +
			                    		               //将图片显示在文字下方
			                    		               "<td>"+ obj[i].content +
			                    		                    "<br><img src="+ obj[i].images +" />"+
			                    		               "</td>" +
			                    		               "</tr>");
					            		}
					            	});
							            	
						       }else{
					            	data_msg.append("您的新浪微博的好友目前没有动态!<br>");
					            	data_msg.show();
						       }
		                   });
	}else{
		auth_msg.append("新浪微博还未进行授权,不能获取好友动态!<br>");
		auth_msg.show();
	}

	//发出请求前先判断Instagram是否授权
	if(instagram_token != "null" && instagram_token != ""){
		//instagram--获取好友最新动态函数                
		instagramfriends2(function(data){
							if(data != "empty"){
				            	var obj = JSON.parse(data);
				            
				            	 $.each(obj,function(i){
				            		wbTable.append("<tr align='center'>" +
				            		               "<td style='display:none'>"+ obj[i].id +"</td>" +
				            		               "<td>来自instagram</td>" +
				            		               "<td>"+ obj[i].time +"</td>" +
				            		               "<td>"+ obj[i].name +"</td>" +
				            		               "<td>"+ obj[i].content +
	                    		                    "<br><img  width='40%' height='40%'  src="+ obj[i].images +" />"+
	                    		                   "</td>" +
				            		               "</tr>");
				            	})
					            	
					         }else{
					            data_msg.append("您的Instagram的好友目前没有动态<br>");	
					            data_msg.show();
					         }
		                   });
	}else{
		auth_msg.append("Insatgram还未进行授权,不能获取好友动态!<br>");
		auth_msg.show();
	}
	
	//发出请求前先判断Facebook是否授权
	if(facebook_token != "null" && facebook_token != ""){
		//alert("i come in getFriendsDyn.js");
		//facebook--获取好友最新动态函数                
		facebookFriendsDyn(function(data){
							if(data != "empty"){
					            	var obj = JSON.parse(data);
					            //console.log(obj);
					            	 $.each(obj,function(i){
					            		if(obj.imageUrl == "null"){
						            		wbTable.append("<tr align='center'>" +
						            		               "<td style='display:none'>"+ obj[i].id +"</td>" +
						            		               "<td>来自facebook</td>" +
						            		               "<td>"+ obj[i].time +"</td>" +
						            		               "<td>"+ obj[i].name +"</td>" +
						            		               "<td>"+ obj[i].content +"</td>" +
						            		               "</tr>");
					            		}else{
					            			wbTable.append("<tr align='center'>" +
					            		               "<td style='display:none'>"+ obj[i].id +"</td>" +
					            		               "<td>来自facebook</td>" +
					            		               "<td>"+ obj[i].time +"</td>" +
					            		               "<td>"+ obj[i].name +"</td>" +
					            		               "<td>"+ obj[i].content +
		                    		                   "<br><img src="+ obj[i].imageUrl +" />"+
		                    		                   "</td>" +
					            		               "</tr>");
					            		}
					            	})
					            	
					         }else{
					            data_msg.append("您的Facebook的好友目前没有动态<br>");	
					            data_msg.show();
					         }
		                   });
	}else{
		auth_msg.append("Facebook还未进行授权,不能获取好友动态!<br>");
		auth_msg.show();
	}
})
</script>
</head>
<body>
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
</body>
</html>
