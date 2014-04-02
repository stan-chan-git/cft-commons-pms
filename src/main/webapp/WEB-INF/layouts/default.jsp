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
</script>
<sitemesh:head />
</head>
<body>

	<!--这个层很有用，必须要，否则可能不兼容。-->
<%-- 	<%@ include file="/WEB-INF/layouts/footer.jsp"%> --%>
	<%@ include file="/WEB-INF/layouts/header.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col-md-3 .col-xs-3 .col-sm-3" 
				style="padding-left: 0px;padding-right: 2px;border-bottom-color:#E7E7E7">
			
			<%@ include file="/WEB-INF/layouts/left.jsp"%></div>
			<div class="col-md-9 col-xs-9 col-sm-9" style="padding: 0px;padding-left: 10px;"><sitemesh:body /></div>
		</div>
	</div>
</body>
</html>