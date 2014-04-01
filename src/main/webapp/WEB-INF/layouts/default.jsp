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
<%@ include file="/static/commons/header-easyui.jsp"%>
<%@ include file="/static/commons/meta.jsp"%>
<%@ include file="/static/commons/spring-taglibs.jsp"%>
<!-- ////////////////////////////////// -->
<!-- //      Start Stylesheets       // -->
<!-- ////////////////////////////////// -->
<style type="text/css">
.leftMenuList {
	padding: 0px 5px 1px;
	margin-top: -1px;
	position: relative;
}

.leftMenuList li {
	height: 39px;
	line-height: 39px;
	border-top: 1px dotted #D8D8D8;
	overflow: hidden;
}

.leftMenuList a {
	text-decoration: none;
	color: #666;
	outline: medium none;
}
</style>
<!-- ////////////////////////////////// -->
<!-- //      Javascript Files        // -->
<!-- ////////////////////////////////// -->
<script type="text/javascript" src="${ctx}/static/js/mmcms-1.0.js"></script>
<script type="text/javascript">
	$(function(){
		$(".leftMenuList a").mouseover(function(){
			$(this).css("color","#FF4E00");
		}).mouseout(function(){
			$(this).css("color","#666");
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
<body class="easyui-layout">
	<%@ include file="/WEB-INF/layouts/header.jsp"%>
	<div data-options="region:'west',split:true" title="菜单"
		style="width: 150px; height: 100%;">
		<div class="easyui-accordion"
			data-options="fit:true,border:false">
			<div title="Demo" style="padding: 10px;">
				<ul	class="leftMenuList">
					<li>
						<a href="javascript:void(0);" onclick="showMsg();">提示消息</a> 
					</li>
					<li>
						<a href="${ctx}/easyui/application">分页demo</a>
					</li>
					<li>
						<a href="https://open.t.qq.com/cgi-bin/oauth2/authorize?client_id=801489930&response_type=code&redirect_uri=http://localhost/easyui/tweibo.do">腾讯微博授权</a>
					</li>
				</ul>
			</div>
			<shiro:hasPermission name="system:admin">
			<div title="admin" style="padding: 10px;" >
				<ul	class="leftMenuList">
					<li>
						<a href="${ctx}/view/userManage">用户管理</a>
					</li>
					<li>
						<a href="${ctx}/view/roleManage">权限管理</a>
					</li>
				</ul>
			</div>
			</shiro:hasPermission>
			<div title="Title31" style="padding: 10px" data-options="selected:true">
				<ul	class="leftMenuList">
					<li>
						<a href="${ctx}/view/fillLog">填写日志</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div data-options="region:'center',title:'Center'">
		<sitemesh:body />
	</div>
	<%@ include file="/WEB-INF/layouts/footer.jsp"%>
</body>
</html>