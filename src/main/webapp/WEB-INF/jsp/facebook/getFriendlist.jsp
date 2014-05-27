<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Your Friends</title>
<style type="text/css">
.w1{
    width:500px;
   }
   
</style>

<script type="text/javascript">
$(function(){
	 var facebook_token = "<%=session.getAttribute("facebook_token") %>";
	
	 var wbTable = $("#wbTable");//插入数据的表格
	 var auth_msg = $("#auth_msg");//是否授权提示消息
	 
	 if((facebook_token != "null" && facebook_token != "")){
		wbTable.append("<tr align='center' class='success'>" +
								"<td style='display:none'>微博ID</td>" +
							    "<td class='w1'>My Friends:</td>" +
							    "<td class='w1'></td>" +
					   "</tr>");
	 }else{
		 auth_msg.empty();
		 auth_msg.append("Facebook还未进行授权,不能获取好友列表!<br><br>");
		 auth_msg.show();
		 
		 return false;
	 }

	//发出请求前先判断Facebook是否授权
	 if(facebook_token != "null" && facebook_token != ""){
		//facebook--获取好友列表函数               
		getFriendlistFB(function(data){
							if(data != "empty"){
					            var obj = JSON.parse(data);
					            $.each(obj,function(i){
				            			//alert(obj[i].id + "," + obj[i].name + "," + obj[i].portrait);
					            		wbTable.append("<tr align='center'>" +
					            		               "<td style='display:none'>"+ obj[i].id +"</td>" +
					            		               "<td>"+"<img src="+ obj[i].portrait + " />"+"</td>" +
					            		               "<td>"+ obj[i].name +"</td>" +
					            		               "</tr>");
				            	});
					         }else{
					            data_msg.append("您目前没有好友");	
					            data_msg.show();
					         }
		});
	}else{
		auth_msg.append("Facebook还未进行授权,不能获取好友列表!<br>");
		auth_msg.show();
	}
})

function getFriendlistFB(callback){
	$.post("/pms/facebook/getFriendlist.do",
		   function(data){
		      callback(data); 
	       },
	
		   //返回的数据类型
	       "json");
}
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
