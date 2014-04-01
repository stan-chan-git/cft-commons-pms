<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table id="roleDataGrid">
		<thead>
			<tr>
				<th field="roleId" sortable="true" width="10">id</th>
				<th field="name" sortable="true" width="10">name</th>
				<th field="permissions" width="10">permissions</th>
				<th field="status" width="10">status</th>
				<th field="remark" width="10">remark</th>
			</tr>
		</thead>
	</table>
	<div id="tb_roleDataGrid">
		<div style="margin-bottom: 5px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add"
					plain="true" onclick="toCreateRole()">添加权限</a>
		</div>
	</div>
	<div id="roleManage_dialog"></div>
	<script type="text/javascript">
		$(function(){
			$("#roleDataGrid").initPage({
				title : '权限列表',
				toolbar : $("#tb_roleDataGrid"),
				postUrl : '${ctx}/rolePageList'
			});
		});
		
		function toCreateRole(){
			$("#roleManage_dialog").dialog({
				title: '添加权限',
				width: 400,
				height: 500,
				closed: false,
				cache: false,
				href: '${ctx}/view/createRole',
				modal: true
			});
		}
	</script>
</body>
</html>