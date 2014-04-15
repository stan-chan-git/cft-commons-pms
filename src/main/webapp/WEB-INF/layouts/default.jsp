<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	request.setAttribute("ctx", request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
<title>PMS系统</title>
<%@ include file="/static/commons/header-bootstrap.jsp"%>
<%@ include file="/static/commons/meta.jsp"%>
<%@ include file="/static/commons/spring-taglibs.jsp"%>
<%@ include file="/static/commons/jstl-taglibs.jsp" %>
<!-- ////////////////////////////////// -->
<!-- //      Start Stylesheets       // -->
<!-- ////////////////////////////////// -->
<style type="text/css">
body { padding-top: 70px; }
</style>
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/styles/question.css">
<!-- ////////////////////////////////// -->
<!-- //      Javascript Files        // -->
<!-- ////////////////////////////////// -->
<script type="text/javascript" src="${ctx}/static/js/commens-pms-1.0.js"></script>
<script type="text/javascript">
	$(function() {
		$(".leftMenuList a").mouseover(function() {
			$(this).css("color", "#FF4E00");
		}).mouseout(function() {
			$(this).css("color", "#666");
		});
		var accordion_head = $('.accordion > li > a'), accordion_body = $('.accordion li > .sub-menu');
		// Open the first tab on load
		accordion_head.first().addClass('active').next().slideDown(200);
		// Click function
		accordion_head.on('click', function(event) {
			// Disable header links
			event.preventDefault();
			// Show and hide the tabs on click
			if ($(this).attr('class') != 'active') {
				accordion_body.slideUp('normal');
				$(this).next().stop(true, true).slideToggle('normal');
				accordion_head.removeClass('active');
				$(this).addClass('active');
			}
		});
	});
	function showMsg() {
		$.messager.show({
			title : '渐进显示信息3',
			msg : '3秒钟后消失',
			timeout : 3000,
			showType : 'show'
		});
	}
	
<!--//////////////////////// -->
<!--/// 下面部分为授权按钮点击事件/// -->
<!--//////////////////////// -->
$(function(){
	
	/**  腾讯微博授权按钮点击事件     **/
	$("#tencent_btn").click(function(){
		window.location.href="https://open.t.qq.com/cgi-bin/oauth2/authorize?client_id=801495189&response_type=code&redirect_uri=http://localhost:8088/pms/tencent/tweibo.do";
	});
	
})
</script>
<sitemesh:head />
</head>
<body>

	<!--这个层很有用，必须要，否则可能不兼容。-->
<%-- 	<%@ include file="/WEB-INF/layouts/footer.jsp"%> --%>
	<%@ include file="/WEB-INF/layouts/header.jsp"%>
	
	
	
	<div class="container">
	  <!-- 腾讯微博授权按钮 -->
	  <img alt="腾讯微博" src="${ctx}/static/images/tencent.jpg" style="width:37px;height:38px" />
	  <!-- 如果已经授权，则不显示按钮 -->
	  <c:choose>
		  <c:when test="${tencent_info == 'success'}">
		                   腾讯微博已授权,30分钟后失效
		  </c:when>
		  <c:otherwise>
			  <button class="btn btn-default" id="tencent_btn">腾讯微博授权</button>
		  </c:otherwise>
	  </c:choose>
	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  
	  <!-- 新浪微博授权按钮 -->
	  <img alt="新浪微博" src="${ctx}/static/images/sina.jpg" style="width:37px;height:38px" />
	  <button class="btn btn-default">新浪微博授权</button>
	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  
	  <!-- instagram授权按钮 -->
	  <img alt="instagram" src="${ctx}/static/images/instagram.jpg" style="width:37px;height:38px" />
	  <button class="btn btn-default">Instagram授权</button>
	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  
	  <!-- Facebook授权按钮 -->
	  <img alt="instagram" src="${ctx}/static/images/facebook.jpg" style="width:37px;height:38px" />
	  <button class="btn btn-default">Facebook授权</button>
	  
	  <br><br><br>
		<div class="row">
			<div class="col-lg-4 .col-xs-3 .col-sm-3" 
				style="padding-left: 0px;padding-right: 2px;border-bottom-color:#E7E7E7">
			
			<%@ include file="/WEB-INF/layouts/left.jsp"%></div>
			<div class="col-lg-4 col-xs-9 col-sm-9" style="padding: 0px;padding-left: 10px;">
			   <sitemesh:body />
			</div>
		</div>
	</div>
</body>
</html>