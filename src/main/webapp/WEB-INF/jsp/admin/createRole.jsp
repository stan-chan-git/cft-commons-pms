<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="padding: 10px 0 10px 60px">
		<form id="ff" method="post" action="${ctx}/api/saveUser">
			<table>
				<tr>
					<td>名称:</td>
					<td><input class="easyui-validatebox" type="text" name="name"
						 autocomplete="off"/></td>
				</tr>
				<tr>
					<td>权限:</td>
					<td>
						<div style="border: 1px dotted #D8D8D8">
							<span style="font-size: 24px;">pms：</span><br>
							<input class="easyui-validatebox" type="checkbox" name="role" value="pms:create">创建</input>
							<input class="easyui-validatebox" type="checkbox" name="role" value="pms:delete">删除</input>
							<input class="easyui-validatebox" type="checkbox" name="role" value="pms:update">修改</input>
							<input class="easyui-validatebox" type="checkbox" name="role" value="pms:approval">审批</input>
						</div>
					</td>
				</tr>
				<tr>
					<td>状态:</td>
					<td>
						<select class="easyui-combobox" name="status" data-options="required:true">
							<option value="active">活跃</option>
							<option value="inactive">待用</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>备注:</td>
					<td>
						<textarea class="easyui-validatebox" name="remark"></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div style="text-align: center; padding: 5px">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="submitForm()">Submit</a> <a href="javascript:void(0)"
			class="easyui-linkbutton" onclick="clearForm()">Clear</a>
	</div>
	<script type="text/javascript">
		function submitForm() {
			$('#ff').form('submit');
		}
		function clearForm() {
			$('#ff').form('clear');
		}
	</script>
</body>
</html>