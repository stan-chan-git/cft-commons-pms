<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setAttribute("ctx", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/dist/css/bootstrap-theme.min.css">
<script type="text/javascript"
	src="${ctx}/static/dist/js/bootstrap.min.js"></script>
<style type="text/css">
body {
	background-color: #eee;
}
</style>
</head>
<body>
	<center>
		<div class="container"
			style="padding-top: 100px; padding-left: 200px;">
			<div class="row">
				<div class="col-md-8">
					<div class="alert alert-info">
						<div class="panel panel-default">
							<div class="panel-heading">用户注册</div>
							<div class="panel-body">
								<form id="ff" method="post" action="${ctx}/uams/registeredLogin">
									<table style="border: 1px dotted #D8D8D8;">
										<tr>
											<td>账号:</td>
											<td><input class="easyui-validatebox" type="text"
												name="loginName" autocomplete="off" /></td>
										</tr>
										<tr>
											<td>密码:</td>
											<td><input class="easyui-validatebox" type="password"
												name="plainPassword" autocomplete="off" /></td>
										</tr>
										<tr>
											<td>重复密码:</td>
											<td><input class="easyui-validatebox" type="password"
												name="password" /></td>
										</tr>
										<tr>
											<td>姓名:</td>
											<td><input class="easyui-validatebox" type="text"
												name="name" /></td>
										</tr>
										<tr>
											<td>邮箱:</td>
											<td><input class="easyui-validatebox" type="text"
												name="email" data-options="validType:'email'" /></td>
										</tr>
										<tr>
											<td>部门:</td>
											<td><input class="easyui-validatebox" type="text"
												name="dept" /></td>
										</tr>
										<tr>
											<td>手机:</td>
											<td><input class="easyui-validatebox" type="text"
												name="phone" /></td>
										</tr>
										<tr>
											<td>状态:</td>
											<td><select class="easyui-combobox" name="status"
												data-options="required:true">
													<option value="active">活跃</option>
													<option value="inactive">待用</option>
											</select></td>
										</tr>
										<tr>
											<td colspan="2"><input type="submit" value="提交" /></td>
										</tr>
									</table>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</center>
</body>
</html>