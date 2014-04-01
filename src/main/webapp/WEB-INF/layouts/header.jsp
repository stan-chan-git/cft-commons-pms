<%@ page language="java" pageEncoding="UTF-8"%>
<div data-options="region:'north'"
	style="height: 100px; width: 100%; background-color: #F0F0F0;">
	<div
		style="float: left; text-align: center; padding: 15px;">
		<span style="font-size: 5em;">PMS</span>
	</div>
	<div style="float: right; height: 100%;">
		<div style="height: 80%;"></div>
		<div style="padding-right: 50px;">
			<shiro:user>
				<spring:message code="label.welcome" />, <shiro:principal />
				<a href="${ctx}/uams/logout?<%=Math.random()%>"><spring:message
						code="label.logout" /></a>
			</shiro:user>
		</div>
	</div>

</div>
