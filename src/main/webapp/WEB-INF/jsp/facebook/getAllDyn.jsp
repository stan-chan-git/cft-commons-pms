<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home Page (Today Posts)</title>
<style type="text/css">
.w1{
    width:300px;
   }
</style>

<script>

	(function(d, s, id) {
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
					<table id="wbTable" class="table" style="argin-left:40px" class="allDyn">
						<thead>
						<!-- 判断Facebook是否有授权 -->
							<c:choose>
								<c:when test="${sessionScope.facebook_token != null && sessionScope.facebook_token != ''}">
									<tr align='center' class='success'>
										<td style='display:none'>微博ID</td>
										<td class='w1'>Home Page (Today Posts)</td>
				   					</tr>
								</c:when>
								<c:otherwise>
									Facebook is not for authorization, can't get posts!<br><br>
								</c:otherwise>
							</c:choose>
						</thead>
						<tbody>
							<!-- 判断当前用户是否有消息 -->
		   					<c:choose>
		   						<c:when test="${allDyn != null && allDyn != ''}">
		   							<c:forEach items="${allDyn}" var="allDyn">
		   								<tr align='center'>
		            		            	<td style='display:none'>${allDyn.postID}</td>
		            		            	<td class='w1'>
		            		            		<div align="left"><a href="https://www.facebook.com/${allDyn.userId}"><font size="5" color="#8f4b2e">${allDyn.userName}</font></a>&nbsp;<font size="2px" color="#8e7437">${allDyn.update_time}</font></div>
		            		            		<div align="left">
			            		            		<br>${allDyn.message}
			            		            		<!-- 如果有图片则显示 -->
			            		            		<c:if test="${'null' != allDyn.imageUrl && '' != allDyn.imageUrl}">
			            		            			<br><img src="${allDyn.imageUrl}" />
			            		            		</c:if>
		            		            		</div>
		            		            		<div align="right">
		            		            			<font color="#f58220">Comment(&nbsp;${allDyn.commentNum}&nbsp;)</font>
		            		            			&nbsp;&nbsp;&nbsp;
			            		            		<div class="fb-like" data-href="https://www.facebook.com/${fn:replace(allDyn.postID,'_','/posts/')}" data-width="10" data-layout="box_count" data-action="like" data-show-faces="false" data-share="false"></div>
				            		            	<div class="fb-share-button" data-href="https://www.facebook.com/${fn:replace(allDyn.postID,'_','/posts/')}" data-width="10" data-type="box_count"></div>
		            		            		</div>
		            		            		<div align="left">
		            		            			<br><div class="fb-comments" data-href="https://www.facebook.com/${fn:replace(allDyn.postID,'_','/posts/')}" data-numposts="3" data-colorscheme="light"></div>
		            		            			<br>
		            		            			<c:forEach items="${allDyn.comDTO}" var="friendcommments">
			            		            			<font size="3" color="#8f4b2e">${friendcommments.fromName}</font>&nbsp;&nbsp;&nbsp;<font size="2px" color="#8e7437">${friendcommments.createTime}</font><br>
			            		            			${friendcommments.message}<br>
			            		            		</c:forEach>
		            		            		</div>
		            		            	</td>
	            		            	</tr>
		   							</c:forEach>
		   						</c:when>
		   						<c:otherwise>
		   							<c:if test="${sessionScope.facebook_token != null && sessionScope.facebook_token != ''}">
		   								You don't have post anything, let's post!
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
