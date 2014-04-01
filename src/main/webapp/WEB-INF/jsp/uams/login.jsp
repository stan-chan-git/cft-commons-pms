<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.LockedAccountException"%>
<%@ page import="org.springframework.context.MessageSource"%>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@ page import="java.util.Locale"%>
<%@ page import="org.apache.shiro.SecurityUtils"%>
<%@ page import="org.apache.shiro.subject.Subject"%>

<%@ include file="/static/commons/jstl-taglibs.jsp"%>
<%@ include file="/static/commons/spring-taglibs.jsp"%>
<%@ include file="/static/commons/meta.jsp"%>
<%@ include file="/static/commons/header-easyui.jsp"%>
<%
	request.setAttribute("ctx", request.getContextPath());
%>
<head>
<%@ include file="/static/commons/header-easyui.jsp"%>

<title>User Login</title>
<link rel="stylesheet" type="text/css" href="${ctx}/static/styles/uams/reset.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/styles/uams/structure.css">
</head>

<body>
	<%
		ApplicationContext context = RequestContextUtils.getWebApplicationContext(request);
		MessageSource messageSource = (MessageSource) context.getBean("messageSource");
		Locale locale = RequestContextUtils.getLocale(request);

		String errorMsg = "null";
		String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);

		if (error != null) {
			if (error.contains("DisabledAccountException")) {
				errorMsg = messageSource.getMessage("login.msg.inactive",
						null, locale);
			} else if (error.contains("IncorrectCredentialsException")
					|| error.contains("UnknownAccountException")) {
				errorMsg = messageSource.getMessage("login.msg.incorrent",
						null, locale);
			} else {
				errorMsg = messageSource.getMessage("login.msg.fail", null,
						locale);
			}
		}
		//out.print(error);	

		boolean isAuthenticated = false;
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser != null && currentUser.isAuthenticated()) {
			isAuthenticated = true;
		}
	%>


	<form class="box login" id="loginForm" action="${ctx}/uams/login" method="post">
		<header>
			<spring:message code="login.title" />
		</header>

		<c:choose>
			<c:when test="<%=isAuthenticated%>">
				<fieldset class="boxBody">
				<div style="margin:20px 0;"></div> 
				<label>
					<shiro:user>
						<spring:message code="label.welcome" />, <shiro:principal />
					</shiro:user>
					</label>
					<div style="margin:10px 0;"></div> 
					<label>You have successfully logged in CMS.</label> 
					<div style="margin:40px 0;"></div> 
					<div ><a href="${ctx}/uams/logout?<%=Math.random()%>" class="easyui-linkbutton" iconCls="icon-logout" plain="false" style="font-size:16px;"><spring:message code="label.logout"/></a></div>
					<div style="margin:70px 0;"></div> 
				</fieldset>
				<footer><div style="margin:16px 0;"></div> </footer>
			</c:when>

			<c:otherwise>
				<fieldset class="boxBody">
					<label><spring:message code="login.username" /></label> <input type="text" id="username" name="username" tabindex="1" required> <label>
						<!-- <a href="#" class="rLink" tabindex="5">Forget your password?</a> --> <spring:message code="login.password" />
					</label> <input type="password" tabindex="2" id="password" name="password" required>

					<%-- <c:set var="errorMsg" value="hello"></c:set> --%>
					<br>
				</fieldset>
				<footer>
					<label><input type="checkbox" tabindex="3" id="rememberMe" name="rememberMe"> <spring:message code="login.rememberMe" /></label> 
					<label style="margin-left: 10px;"><a href="${ctx}/uams/registered">注册</a></label>
					<input
						type="submit" class="btnLogin" value="Login" tabindex="4">
				</footer>
			</c:otherwise>

		</c:choose>




	</form>
	<footer id="main">
		(HTML5/CSS3 Coded) Certified. 
		Reserved.
	</footer>

	<div>
		<a href="?lang=en_US" style="font-size: 12px;">English</a> <a href="?lang=zh_TW" style="font-size: 12px;">繁體中文</a> <a href="?lang=zh_CN"
			style="font-size: 12px;">简体中文---${ctx }--</a>
		<ul>
			<label>4 testing accounts for demo:</label>
			<li>Chief Editor: username: ceditor ,password: abcd</li>
			<li>NewsContent Editor: username: neditor ,password: abcd</li>
			<li>NewsImage Editor: username: ieditor ,password: abcd</li>
			<li>MusicProduct Editor: username: meditor ,password: abcd</li>
		</ul>

	</div>
	<script type="text/javascript">
$(function(){
	var errMsg = "<%=errorMsg%>";

			if (errMsg != "null") {
				$.messager.alert('<spring:message code="login.fail"/>', errMsg,
						'info');
			}
		});
	</script>
</body>
</html>
