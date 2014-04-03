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
	src="${ctx}/static/js/jquery-1.11.0.min.js"></script>

<script type="text/javascript"
	src="${ctx}/static/dist/js/bootstrap.min.js"></script>

<script type="text/javascript"
	src="${ctx}/static/js/jquery-validation/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${ctx}/static/js/jquery-validation/messages_bs_cn.js"></script>
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/js/jquery-validation/validate.css">

<style type="text/css">
body {
	background-color: #eee;
}
</style>
<script type="text/javascript">
	
</script>
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
								<form id="ff" method="post" action="${ctx}/uams/registeredLogin"
									role="form" class="form-horizontal">
									<div class="form-group">
										<label for="loginName" class="col-sm-3 control-label">账号:</label>
										<div class="col-sm-9">
											<input type="text" name="loginName"
												class="{required} form-control" id="loginName"
												placeholder="Account">
										</div>
									</div>
									<div class="form-group">
										<label for="plainPassword" class="col-sm-3 control-label">密码:</label>
										<div class="col-sm-9">
											<input type="password"
												class="{required:true,minlength:5} form-control"
												id="plainPassword" name="plainPassword"
												placeholder="Password">
										</div>
									</div>
									<div class="form-group">
										<label for="password" class="col-sm-3 control-label">重复密码:</label>
										<div class="col-sm-9">
											<input type="password"
												class="{required:true,minlength:5,equalTo:'#plainPassword'} form-control"
												id="password" name="password" placeholder="Password">
										</div>
									</div>
									<div class="form-group">
										<label for="name" class="col-sm-3 control-label">姓名:</label>
										<div class="col-sm-9">
											<input type="text" name="name"
												class="{required} form-control" id="name" placeholder="Name">
										</div>
									</div>
									<div class="form-group">
										<label for="email" class="col-sm-3 control-label">邮箱:</label>
										<div class="col-sm-9">
											<input type="text" name="email"
												class="{required email} form-control" id="email"
												placeholder="Email">
										</div>
									</div>
									<div class="form-group">
										<label for="dept" class="col-sm-3 control-label">部门:</label>
										<div class="col-sm-9">
											<input type="text" name="dept" class="form-control" id="dept"
												placeholder="Dept">
										</div>
									</div>
									<div class="form-group">
										<label for="phone" class="col-sm-3 control-label">手机:</label>
										<div class="col-sm-9">
											<input type="text" name="phone" class="form-control"
												id="phone" placeholder="Phone">
										</div>
									</div>
									<div class="form-group">
										<label for="optionsRadios1" class="col-sm-3 control-label">状态:</label>
										<div class="col-sm-9">
											<label class="radio-inline"> <input type="radio"
												id="inlineCheckbox1" value="active" name="status"
												checked="checked"> 活跃
											</label> <label class="radio-inline"> <input type=radio
												id="inlineCheckbox2" value="inactive" name="status">
												待用
											</label>

										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-offset-2 col-sm-9">
											<button type="submit" class="btn btn-default">提交</button>
										</div>
									</div>
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