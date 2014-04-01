<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/static/commons/jstl-taglibs.jsp"%>
<%@ include file="/static/commons/spring-taglibs.jsp"%>
<%@ include file="/static/commons/meta.jsp"%>

</head>
<body>
	<div id="editRoleTabs" class="easyui-tabs" data-options="collapsible:true" >
		<div title="<spring:message code="label.editRole"/>" data-options="iconCls:'icon-edit'" style="padding: 10px;">
			<form:form method="post" modelAttribute="roleForm">

				<table width="92%" border="0" cellspacing="6" cellpadding="0">
					<c:if test="${roleForm.roleId != null}">
						<tr>
							<td width="20%" bgcolor="#FAFAFA"><label class="formLabel"><spring:message code="label.roleId"/>:</label></td>
							<td width="80%" bgcolor="#FAFAFA"><form:input path="roleId" readonly="true" /></td>
						</tr>
					</c:if>
					<tr>
						<td width="20%" bgcolor="#FAFAFA"><label class="formLabel"><spring:message code="label.roleName"/>:</label></td>
						<td width="80%" bgcolor="#FAFAFA"><form:input path="name" size="30" class="easyui-validatebox" required="true"  /></td>
					</tr>

					<tr>
						<td bgcolor="#FAFAFA"><label class="formLabel"><spring:message code="label.permissions"/>:</label></td>
						<td bgcolor="#FAFAFA">
							<%-- <form:checkboxes path="permissionList" items="${allPermissions}" /> --%>


							<table width="100%" border="0" cellspacing="5" cellpadding="0">
								<tr>
									<td colspan="3"><label class="formLabel"><spring:message code="label.newsPermissions"/>:</label></td>
								</tr>
								<tr>
									<td width="45%"><form:checkbox path="permissionList" value="news:create" />news:create</td>
									<td width="45%"><form:checkbox path="permissionList" value="news:ivaild" />news:ivaild</td>
									<td width="10%">&nbsp;</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td colspan="3"><label class="formLabel"><spring:message code="label.newsContentPermissions"/>:</label></td>
								</tr>
								<tr>
									<td><form:checkbox path="permissionList" value="news:newsContent:read" />news:newsContent:read</td>
									<td><form:checkbox path="permissionList" value="news:newsContent:update" />news:newsContent:update</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td><form:checkbox path="permissionList" value="news:newsContent:submit" />news:newsContent:submit</td>
									<td><form:checkbox path="permissionList" value="news:newsContent:reject" />news:newsContent:reject</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td><form:checkbox path="permissionList" value="news:newsContent:approve" />news:newsContent:approve</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td colspan="3"><label class="formLabel"><spring:message code="label.newsImagePermissions"/>:</label></td>
								</tr>
								<tr>
									<td><form:checkbox path="permissionList" value="news:newsImage:read" />news:newsImage:read</td>
									<td><form:checkbox path="permissionList" value="news:newsImage:update" />news:newsImage:update</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td><form:checkbox path="permissionList" value="news:newsImage:submit" />news:newsImage:submit</td>
									<td><form:checkbox path="permissionList" value="news:newsImage:reject" />news:newsImage:reject</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td><form:checkbox path="permissionList" value="news:newsImage:approve" />news:newsImage:approve</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td colspan="3"><label class="formLabel"><spring:message code="label.musicProductPermissions"/>:</label></td>
								</tr>
								<tr>
									<td><form:checkbox path="permissionList" value="news:musicProduct:read" />news:musicProduct:read</td>
									<td><form:checkbox path="permissionList" value="news:musicProduct:update" />news:musicProduct:update</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td><form:checkbox path="permissionList" value="news:musicProduct:submit" />news:musicProduct:submit</td>
									<td><form:checkbox path="permissionList" value="news:musicProduct:reject" />news:musicProduct:reject</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td><form:checkbox path="permissionList" value="news:musicProduct:approve" />news:musicProduct:approve</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td colspan="3"><label class="formLabel"><spring:message code="label.categoryPermissions"/>:</label></td>
								</tr>
								<tr>
									<td><form:checkbox path="permissionList" value="category:read" />category:read</td>
									<td><form:checkbox path="permissionList" value="category:edit" />category:edit</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td colspan="3"><label class="formLabel"><spring:message code="label.userPermissions"/>:</label></td>
								</tr>
								<tr>
									<td><form:checkbox path="permissionList" value="user:create" />user:create</td>
									<td><form:checkbox path="permissionList" value="user:read" />user:read</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td><form:checkbox path="permissionList" value="user:update" />user:update</td>
									<td><form:checkbox path="permissionList" value="user:delete" />user:delete</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td colspan="3"><label class="formLabel"><spring:message code="label.rolePermissions"/>:</label></td>
								</tr>
								<tr>
									<td><form:checkbox path="permissionList" value="role:create" />role:create</td>
									<td><form:checkbox path="permissionList" value="role:read" />role:read</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td><form:checkbox path="permissionList" value="role:update" />role:update</td>
									<td><form:checkbox path="permissionList" value="role:disable" />role:disable</td>
									<td>&nbsp;</td>
								</tr>
							</table>

						</td>
					</tr>

					<tr>
						<td bgcolor="#FAFAFA"><label class="formLabel"><spring:message code="label.status"/>:</label></td>
						<td bgcolor="#FAFAFA">
							<form:select path="status" style="width:150px;">
								<form:options items="${allStatus}" />
							</form:select>
						</td>
					</tr>
					<tr>
						<td><label class="formLabel"><spring:message code="label.remark"/>:</label></td>
						<td><form:textarea path="remark" rows="2" cols="30" /></td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>

	<div>
		<shiro:hasPermission name="role:update">
		<div style="text-align: right; padding: 10px;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="doSaveRole()"><spring:message code="btn.submit"/></a> &nbsp;<a
				href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#roleDailog').dialog('close')"><spring:message code="btn.cancel"/></a>
		</div>
		</shiro:hasPermission>
	</div>

	<script>
		function doSaveRole() {
			$('#roleForm')
					.form(
							'submit',
							{
								url : "${ctx}/uams/ajax/doSaveRole",
								//提交时表单验证
								onSubmit : function() {
									return $(this).form('validate');
								},

								success : function(data) {
									data = eval('(' + data + ')');
									if (data.success) { //如果成功返回Json.success = true

										$('#roleDailog').dialog('close'); //关闭Dailog
										$('#roleDataGrid').datagrid('reload'); //重载UserList

										//显示成功信息	
										showInfoMessage('<spring:message code="dialog.success"/>', data.message + ' (' + data.dataObject + ')');
									} else {
										//显示错误信息
										showErrorMessage('<spring:message code="dialog.error"/>', data.message);
									}
								} //end success:function(data)
							});
		}
	</script>
</body>
</html>