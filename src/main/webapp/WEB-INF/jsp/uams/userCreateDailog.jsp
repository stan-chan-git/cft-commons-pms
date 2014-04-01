<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/static/commons/jstl-taglibs.jsp"%>
<%@ include file="/static/commons/spring-taglibs.jsp"%>
<%@ include file="/static/commons/meta.jsp"%>

</head>
<body>

	<div id="createUserTabs" class="easyui-tabs" data-options="collapsible:true" style="height: 500px">
		<div title="<spring:message code="label.userProfile"/>" data-options="iconCls:'icon-edituser'" style="padding: 10px;">
			<form:form method="post" modelAttribute="userForm">
				<table width="80%" border="0" cellspacing="6" cellpadding="0">
					<tr>
						<td width="25%" bgcolor="#FAFAFA"><label class="formLabel"><spring:message code="label.loginId"/>:</label></td>						
						<td width="75%" bgcolor="#FAFAFA"><form:input path="loginName" size="30" /></td>
					</tr>
					<tr>
						<td bgcolor="#FAFAFA"><label class="formLabel"><spring:message code="label.password"/>:</label></td>
						<td bgcolor="#FAFAFA"><form:input path="plainPassword" size="30" class="easyui-validatebox" type="password" required="true" /></td>
					</tr>
					<tr>
						<td bgcolor="#FAFAFA"><label class="formLabel"><spring:message code="label.confirmPassword"/>:</label></td>
						<td bgcolor="#FAFAFA"><form:input path="password" size="30" class="easyui-validatebox"  required="true" type="password" validType="equalTo['#plainPassword']" /></td>
					</tr>
					<tr>
						<td bgcolor="#FAFAFA"><label class="formLabel"><spring:message code="label.userRole"/>:</label></td>
						<td bgcolor="#FAFAFA"><form:checkboxes path="roleList" items="${roleList}" itemLabel="name"
								itemValue="roleId" delimiter="<br/>" /></td>
					</tr>

					<tr>
						<td bgcolor="#FAFAFA"><label class="formLabel"><spring:message code="label.staffName"/>:</label></td>
						<td bgcolor="#FAFAFA"><form:input path="name" size="30" /></td>
					</tr>
					<tr>
						<td bgcolor="#FAFAFA"><label class="formLabel"><spring:message code="label.email"/>:</label></td>
						<td bgcolor="#FAFAFA"><form:input path="email" size="30" /></td>
					</tr>
					<tr>
						<td bgcolor="#FAFAFA"><label class="formLabel"><spring:message code="label.phone"/>:</label></td>
						<td bgcolor="#FAFAFA"><form:input path="phone" size="30" /></td>
					</tr>

					<tr>
						<td bgcolor="#FAFAFA"><label class="formLabel"><spring:message code="label.office"/>:</label></td>
						<td bgcolor="#FAFAFA"><form:input path="office" size="30" /></td>
					</tr>
					<tr>
						<td bgcolor="#FAFAFA"><label class="formLabel"><spring:message code="label.dept"/>:</label></td>
						<td bgcolor="#FAFAFA"><form:input path="dept" size="30" /></td>
					</tr>
					<tr>
						<td bgcolor="#FAFAFA"><label class="formLabel"><spring:message code="label.language"/>:</label></td>
						<td bgcolor="#FAFAFA"><form:select path="language" items="${languageMap}" style="width:150px;"/></td>
					</tr>
					<tr>
					<tr>
						<td bgcolor="#FAFAFA"><label class="formLabel"><spring:message code="label.status"/>:</label></td>
						<td bgcolor="#FAFAFA"><form:select path="status" style="width:150px;">
								<option value="active"><spring:message code="status.active"/></option>
								<option value="inactive"><spring:message code="status.inactive"/></option>
							</form:select></td>
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
		
		<div style="text-align: right; padding: 10px;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="doCreateUser()"><spring:message code="btn.submit"/></a> &nbsp;<a
				href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#userDailog').dialog('close')"><spring:message code="btn.cancel"/></a>
		</div>
		
	</div>


	<script>

		function doCreateUser() {
			$('#userForm')
					.form(
							'submit',
							{
								url : "${ctx}/uams/ajax/doCreateUser",
								//提交时表单验证
								onSubmit : function() {
									return true;
								},

								success : function(data) {
									data = eval('(' + data + ')');
									if (data.success) { //如果成功返回Json.success = true

										$('#userDailog').dialog('close'); //关闭Dailog
										$('#userDataGrid').datagrid('reload'); //重载UserList

										//显示成功信息	
										showInfoMessage(
												'<spring:message code="dialog.success"/>',
												data.message
														+ ' ('
														+ data.dataObject.userId
														+ ')');
									} else {
										//显示错误信息
										showErrorMessage(
												'<spring:message code="dialog.error"/>',
												data.message);
									}
								} //end success:function(data)
							});
		}
	<%-- <%@ include file="/static/js/extendValidationRule.js"%> --%>
		
	</script>

</body>
</html>