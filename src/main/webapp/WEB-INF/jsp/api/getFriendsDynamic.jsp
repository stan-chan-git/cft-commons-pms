<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
$(function(){
	 //腾讯微博--获取好友最新动态函数
	 getFocusPeopleWeiBo(function(data){
		                    if(data != "empty"){
		                    //console.log(data);
		                    	var wbTable = $("#wbTable")
		                    	//var first = $("#first");
		                    	var obj = JSON.parse(data);
		                    //console.log(obj[0]);	
		                    	 $.each(obj,function(i){
		                    		wbTable.append("<tr align='center'>" +
		                    		               "<td>"+ obj[i].id +"</td>" +
		                    		               "<td>"+ obj[i].content +"</td>" +
		                    		               "<td>"+ obj[i].time +"</td>" +
		                    		               "<td>"+ obj[i].name +"</td>" +
		                    		               "<td>来自腾讯微博</td>" +
		                    		               "</tr>");
		                    	})
		                    	
		                    }else{
		                    	
		                    }
	                    }); 
	//新浪微博--获取好友最新动态函数                
	sinaCommentsfriends(function(data){
							if(data != "empty"){
					            //console.log(data);
					            	var wbTable = $("#wbTable")
					            	//var first = $("#first");
					            	var obj = JSON.parse(data);
					            //console.log(obj[0]);	
					            	 $.each(obj,function(i){
					            		wbTable.append("<tr align='center'>" +
					            		               "<td>"+ obj[i].id +"</td>" +
					            		               "<td>"+ obj[i].content +"</td>" +
					            		               "<td>"+ obj[i].time +"</td>" +
					            		               "<td>"+ obj[i].name +"</td>" +
					            		               "<td>来自新浪微博</td>" +
					            		               "</tr>");
					            	})
					            	
					            }else{
					            	
					            }
	                   });
	//instagram--获取好友最新动态函数                
	instagramfriends(function(data){
							if(data != "empty"){
					            //console.log(data);
					            	var wbTable = $("#wbTable")
					            	//var first = $("#first");
					            	var obj = JSON.parse(data);
					            //console.log(obj[0]);	
					            	 $.each(obj,function(i){
					            		wbTable.append("<tr align='center'>" +
					            		               "<td>"+ obj[i].id +"</td>" +
					            		               "<td>"+ obj[i].content +"</td>" +
					            		               "<td>"+ obj[i].time +"</td>" +
					            		               "<td>"+ obj[i].name +"</td>" +
					            		               "<td>来自instagram</td>" +
					            		               "</tr>");
					            	})
					            	
					            }else{
					            	
					            }
	                   });
})
</script>
</head>
<body>
<div style="border: 2px solid #3399FF; border-radius: 25px; moz-border-radius: 25px; width: 800px; height: 1120px; padding-top: 30px;">			
			
			<div class="form-group">
				<div class="col-sm-11" >
					<table id="wbTable" class="table table-bordered table-hover" style="margin-left:40px">
						<tr align="center" class="success">
							<td>微博ID</td>
							<td>微博内容</td>
							<td>发表时间</td>
							<td>发表人</td>
							<td>来源</td>
						</tr>
					</table>
				</div>
			</div>

</div>
</body>
</html>