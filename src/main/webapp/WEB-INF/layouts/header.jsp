<%@ page language="java" pageEncoding="UTF-8"%>
<%
	request.setAttribute("ctx", request.getContextPath());
%>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="row">
			<div class="navbar-header">
				<a href="${ctx}/" class="navbar-brand"> PMS </a>
			</div>
			<shiro:user>
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav navbar-right">
						<li>
							<a href="javascript:void(-1);">
								<spring:message code="label.welcome" />,<shiro:principal />
							</a>
						</li>
							
						<li>
							<a href="${ctx}/uams/logout?<%=Math.random()%>"> 
								<spring:message code="label.logout" />
							</a>
						</li>
					</ul>
				</div>
			</shiro:user>
		</div>
	</div>

</nav>
