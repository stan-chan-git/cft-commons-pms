<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>User Account Management System </title>
<%@ include file="/static/commons/jstl-taglibs.jsp"%>
<%@ include file="/static/commons/spring-taglibs.jsp"%>
<%@ include file="/static/commons/meta.jsp"%>
<%@ include file="/static/commons/header-easyui.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%@page import="java.util.Date"%>


<script type="text/javascript" src="${ctx}/static/js/extendValidationRule.js"></script>
<script type="text/javascript" src="${ctx}/static/js/dateFormatter.js"></script>
<script type="text/javascript" src="${ctx}/static/js/actionLogger.js"></script>
<script type="text/javascript" src="${ctx}/static/js/mmcms-1.0.js"></script>
<script type="text/javascript" src="${ctx}/static/js/JSON.js"></script> <!-- for support of JSON in ie7 -->



<link rel="stylesheet" type="text/css" href="${ctx}/static/styles/mmcms_custom.css">

<link rel="stylesheet" type="text/css" href="${ctx}/static/styles/uams/reset.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/styles/uams/structure.css">


<script type="text/javascript">  
        jQuery.ajaxSetup({cache:false});//ajax不缓存  
        jQuery(function($){  
        });  
       
  </script>  
</head>

<!-- =============================== Layout: Header ===============================-->
<body class="easyui-layout">
	<div data-options="region:'north'" border="false" class="rtitle" style="height: 70px;">
		<table width="100%">
			<tr valign="top">
				<td width="74%" style="text-align: left;font-size: 17px;" >User Account Management System</td>
				<td width="8%" style="text-align: right">
				<a href="?lang=en_US" style="font-size:12px;color: #fff;">English</a><br/>
 					<a href="?lang=zh_TW" style="font-size:12px;color: #fff;">繁體中文</a><br/>
					<a href="?lang=zh_CN" style="font-size:12px;color: #fff;">简体中文</a>
				</td>

				<td width="8%" style="text-align: right">
				<div class="ccs3Logout" style="text-align: right">
					<shiro:user><spring:message code="label.welcome"/>, <shiro:principal/> <div ><a href="${ctx}/uams/logout?<%=Math.random()%>" class="easyui-linkbutton" iconCls="icon-logout" plain="true" style="font-size:12px;color: #fff;"><spring:message code="label.logout"/></a></div></shiro:user>
				</div>
				</td>
			</tr>
		</table>
	</div>
	


	<!-- =============================== Layout: left ===============================-->
	<div data-options="region:'west',title:'<spring:message code="menu"/> ',split:true" style="width: 180px;">
		<div class="easyui-accordion" data-options="fit:true,border:false">
			
			<div title="<spring:message code="menu.account"/>" style="padding: 10px">
			<a href="javascript:void(0)" onclick="setmain('<spring:message code="menu.account.user"/>','${ctx}/uams/view/toUserList')" class="easyui-linkbutton" plain="true" ><span class="menuItem"><spring:message code="menu.account.user"/></span></a><br/>
			<shiro:hasPermission name="role:read"><a href="javascript:void(0)" onclick="setmain('<spring:message code="menu.account.role"/>','${ctx}/uams/view/toRoleList')" class="easyui-linkbutton" plain="true" ><span class="menuItem"><spring:message code="menu.account.role"/></span></a><br/></shiro:hasPermission>
			</div>
			
			<shiro:hasPermission name="news:newsContent:update">
			<div title="News Management" style="padding: 10px">
			<a href="#" class="easyui-linkbutton" plain="true" ><span class="menuItem">Update News</span></a>
			</div>
			</shiro:hasPermission>
			
		</div>
	</div>
	
	<!-- =============================== Layout: Center ===============================-->
	<div data-options="region:'center'" style="padding: 5px;position:relative;"></div>
	
	

</body>

</html>