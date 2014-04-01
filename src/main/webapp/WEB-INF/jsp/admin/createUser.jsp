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
					<td>账号:</td>
					<td><input class="easyui-validatebox" type="text" name="loginName"
						 autocomplete="off"/></td>
				</tr>
				<tr>
					<td>密码:</td>
					<td><input class="easyui-validatebox" type="password" name="plainPassword"
						 autocomplete="off"/></td>
				</tr>
				<tr>
					<td>重复密码:</td>
					<td><input class="easyui-validatebox" type="password" name="password"/></td>
				</tr>
				<tr>
					<td>姓名:</td>
					<td><input class="easyui-validatebox" type="text" name="name"/></td>
				</tr>
				<tr>
					<td>邮箱:</td>
					<td><input class="easyui-validatebox" type="text" name="email"
						data-options="validType:'email'"/></td>
				</tr>
				<tr>
					<td>部门:</td>
					<td><input class="easyui-validatebox" type="text"
						name="dept"/></td>
				</tr>
				<tr>
					<td>手机:</td>
					<td><input class="easyui-validatebox" type="text"
						name="phone"/></td>
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
					<td>权限:</td>
					<td>
						<c:forEach items="${roleList }" var="role">
							<input class="easyui-validatebox" type="checkbox" name="roleId" value="${role.roleId }">${role.name }</input>
							<br/>
						</c:forEach>
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
			$('#ff').form('submit',{
				onSubmit:function(){return true;},
				success:function(data){
					alert(data);
				}
			});
		}
		function clearForm() {
			$('#ff').form('clear');
		}
	</script>
</body>
</html>