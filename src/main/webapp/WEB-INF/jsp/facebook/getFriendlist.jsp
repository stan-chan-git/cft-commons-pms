<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/zh_CN/sdk.js#xfbml=1&version=v2.0";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));
</script>
</head>
<body>
<div style="width:800px;padding-top: 30px;">			
            
            <span id="auth_msg" class="alert alert-danger" style="display:none;float:left;margin-left:250px"></span>
            <br>
            <span id="data_msg" class="alert alert-info" style="display:none;float:left;margin-left:280px"></span>		
			
			<div class="form-group">
				<div id="dd" class="col-sm-11" >
					<table id="wbTable" class="table" style="argin-left:40px" class="friendList">
						<thead>
						<!-- 判断Facebook是否有授权 -->
							<c:choose>
								<c:when test="${sessionScope.facebook_token != null && sessionScope.facebook_token != ''}">
									<tr align='center' class='success'>
										<td style='display:none'>微博ID</td>
										<td>My Friends:(follow function by the user open)</td>
										<td></td>
										<td></td>
				   					</tr>
								</c:when>
								<c:otherwise>
									Facebook is not for authorization, cannot get friend list!<br><br>
								</c:otherwise>
							</c:choose>
						</thead>
						<tbody>
							<!-- 判断当前用户是否有消息 -->
		   					<c:choose>
		   						<c:when test="${friendList != null && friendList != ''}">
		   							<c:forEach items="${friendList}" var="myfriends">
		   								<tr align='center'>
		            		            	<td style='display:none'>${myfriends.userId}</td>
		            		            	<td>
		            		            		<img src="${myfriends.userHead}" />
		            		            	</td>
		            		           		<td>${myfriends.userName}</td>
		            		            	<td>
		            		            	 	<div class="fb-follow" data-href="https://www.facebook.com/${myfriends.userId}" data-width="10" data-colorscheme="light" data-layout="button_count" data-show-faces="false"></div>
	            		            		<td>
	            		            	</tr>
		   							</c:forEach>
		   						</c:when>
		   						<c:otherwise>
		   							<c:if test="${sessionScope.facebook_token != null && sessionScope.facebook_token != ''}">
		   								You don't have any friend, let's add your friend! 
		   							</c:if>
		   						</c:otherwise>
		   					</c:choose>
						</tbody>
					</table>
				</div>
			</div>
			
</div>
<div id="fb-root"></div>
</body>
</html>
