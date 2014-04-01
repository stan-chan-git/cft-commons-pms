<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% request.setAttribute("ctx", request.getContextPath()); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>application</title>
<script type="text/javascript">
console.log("尼玛，这是原始页面");
</script>
</head>
<body>
	<div>
		<table id="dg" style="width: auto;height: auto;">
			<thead>
				<tr>
					<th field="firstName" width="50">First Name</th>
					<th field="lastName" width="50">Last Name</th>
					<th field="phone" width="50">Phone</th>
					<th field="email" width="50">Email</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="newUser()">New User</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editUser()">Edit User</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="destroyUser()">Remove
			User</a>
	</div>
	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">User Information</div>
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<label>First Name:</label> <input name="firstname"
					class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>Last Name:</label> <input name="lastname"
					class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>Phone:</label> <input name="phone">
			</div>
			<div class="fitem">
				<label>Email:</label> <input name="email" class="easyui-validatebox"
					validType="email">
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="saveUser()">Save</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
	</div>
	<script type="text/javascript">
		$(function() {
			//console.log("准备执行分页方法");
			initPage();
		});
		function initPage() {
			$('#dg').datagrid({
				title : 'datagrid实例',
				iconCls : 'icon-ok',
				pageSize : 5,//默认选择的分页是每页5行数据
				pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合
				nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
				striped : true,//设置为true将交替显示行背景。	
				collapsible : true,//显示可折叠按钮
				toolbar:"#toolbar",//在添加 增添、删除、修改操作的按钮要用到这个
				url:'${ctx}/api/userPageList',//url调用Action方法
				loadMsg : '数据装载中......',
				/* beforePageText: '第',//页数文本框前显示的汉字 
			    afterPageText: '页    共 {pages} 页', 
			    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  */
				singleSelect:true,//为true时只能选择单行
				fitColumns:true,//允许表格自动缩放，以适应父容器
				sortName : 'firstName',//当数据表格初始化时以哪一列来排序
				sortOrder : 'asc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。
				remoteSort : false,
	 			frozenColumns : [ [ {
					field : 'ck',
					checkbox : true
				} ] ], 
				pagination : true,//分页
				rownumbers : true//行数
			});
			var p = $('#dg').datagrid('getPager'); 
		    $(p).pagination({
		        beforePageText: '第',//页数文本框前显示的汉字 
		        afterPageText: '页    共 {pages} 页', 
		        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
		    });  
		}

		var url;
		function newUser() {
			$('#dlg').dialog('open').dialog('setTitle', 'New User');
			$('#fm').form('clear');
			url = 'http://www.jeasyui.com/demo/main/save_user.php';
		}
		function editUser() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$('#dlg').dialog('open').dialog('setTitle', 'Edit User');
				$('#fm').form('load', row);
				url = 'http://www.jeasyui.com/demo/main/update_user.php?id='
						+ row.id;
			}
		}
		function saveUser() {
			$('#fm').form('submit', {
				url : url,
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(result) {
					var result = eval('(' + result + ')');
					if (result.errorMsg) {
						$.messager.show({
							title : 'Error',
							msg : result.errorMsg
						});
					} else {
						$('#dlg').dialog('close'); // close the dialog
						$('#dg').datagrid('reload'); // reload the user data
					}
				}
			});
		}
		function destroyUser() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$.messager
						.confirm(
								'Confirm',
								'Are you sure you want to destroy this user?',
								function(r) {
									if (r) {
										$
												.post(
														'http://www.jeasyui.com/demo/main/destroy_user.php',
														{
															id : row.id
														},
														function(result) {
															if (result.success) {
																$('#dg')
																		.datagrid(
																				'reload'); // reload the user data
															} else {
																$.messager
																		.show({ // show error message
																			title : 'Error',
																			msg : result.errorMsg
																		});
															}
														}, 'json');
									}
								});
			}
		}
	</script>
	<style type="text/css">
#fm {
	margin: 0;
	padding: 10px 30px;
}

.ftitle {
	font-size: 14px;
	font-weight: bold;
	padding: 5px 0;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
}

.fitem {
	margin-bottom: 5px;
}

.fitem label {
	display: inline-block;
	width: 80px;
}
</style>
</body>
</html>