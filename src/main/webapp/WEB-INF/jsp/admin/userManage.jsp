<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/static/commons/spring-taglibs.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
</head>
<body>
	<table id="userDataGrid" style="width: auto;height: auto;">
		<thead>
			<tr>
				<th field="userId" sortable="true" width="10">id</th>
				<th field ="loginName" sortable="true" width="10">账号</th>
				<th field="name" width="10">姓名</th>
				<th field="email" width="15">邮箱</th>
				<th field="phone" width="10">手机</th>
				<th field="office" width="10">电话</th>
				<th field="dept" width="10">部门</th>
				<th field="status" sortable="true" width="5">状态</th>
				<th field="remark" width="10">备注</th>
			</tr>
		</thead>
	</table>
	<div id="tb_userDataGrid" style="padding: 3px; height: auto">
		<div style="margin-bottom: 5px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add"
					plain="true" onclick="toCreateUser()">添加用户</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit"
				plain="true" onclick="toUpdateUser()">修改用户</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove"
					plain="true" onclick="removeUser()">删除用户</a>
		</div>
		<div style="margin-bottom: 5px">
			<span>id:</span> 
			<input id="userId" style="line-height: 26px; border: 1px solid #ccc"/>
			<span>账号:</span> <input
				id="loginName" style="line-height: 26px; border: 1px solid #ccc"/>
			<span>姓名:</span> <input
				id="name" style="line-height: 26px; border: 1px solid #ccc"/>
			<span>状态:</span> <select
				id="status" name="status" style="width: 128px;">
				<option value=""></option>
				<option value="active">
					活跃
				</option>
				<option value="inactive">
					待用
				</option>
			</select> &nbsp;&nbsp;&nbsp;&nbsp; <a href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-search'" plain="false" id="doSearchUser">查询</a>
			&nbsp; <a href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'" plain="false">清除</a>
		</div>
	</div>
	<div id="userManage_dialog"></div>
	<script type="text/javascript">
		$(function() {
			$("#userDataGrid").initPage({
				title : '用户列表',
				toolbar : $("#tb_userDataGrid"),
				postUrl : '${ctx}/api/userPageList'
			});
			
			/***************************************/
			/**************init even****************/
			/***************************************/
			
			$("#doSearchUser").click(function(){
				$('#userDataGrid').datagrid('load', {
					userId : $('#userId').val(),
					loginName : $('#loginName').val(),
					name : $('#name').val(),
					status : $('#status').val()
				});
			});
			
		});
		
		/*********************function************************/
		function toCreateUser(){
			$("#userManage_dialog").dialog({
				title: '创建用户',
				width: 400,
				height: 500,
				closed: false,
				cache: false,
				href: '${ctx}/view/createUser',
				modal: true
			});
		}
	</script>
</body>
</html>