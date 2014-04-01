<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<html>  
<head>
<%@ include file="/static/commons/jstl-taglibs.jsp"%>
<%@ include file="/static/commons/spring-taglibs.jsp"%>
<%@ include file="/static/commons/meta.jsp"%>
</head>
<body>

	<div id="roleListCenter" style="width:100%">
		<!--========================= Role List DataGrid =========================-->
		<table id="roleDataGrid" class="easyui-datagrid" title="<spring:message code="label.roleList"/>" style="width: auto; height: auto"
			data-options="singleSelect:true,collapsible:true,url:'${ctx}/uams/ajax/getRoleList'" rownumbers="true" pagination="false" pageSize="10" fit="false"
			fitColumns="false" striped="true" toolbar="#tb_roleDataGrid">
			<thead>
				<tr>
					<th data-options="field:'ck'" checkbox="true"></th>
					<th data-options="field:'roleId',width:'60'" sortable="true"><spring:message code="label.roleId"/></th>
					<th data-options="field:'name',width:'150'"><spring:message code="label.roleName"/></th>
					<th data-options="field:'permissions',width:'770'"><spring:message code="label.permissions"/></th>
					<th data-options="field:'status',align:'center',width:'100'" sortable="true"><spring:message code="label.status"/></th>
					<th data-options="field:'remark',width:'100'"><spring:message code="label.remark"/></th>
				</tr>
			</thead>
		</table>
		<div id="tb_roleDataGrid" style="padding: 3px; height: auto">
			<div style="margin-bottom: 5px">
				<shiro:hasPermission name="role:create">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"onclick="toCreateRole()"><spring:message code="btn.createRole"/></a>
				</shiro:hasPermission> 
				<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="toUpdateRole()"><spring:message code="btn.updateRole"/></a>
				<shiro:hasPermission name="role:disable"> 
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"onclick="doDisableRole()"><spring:message code="btn.disableRole"/></a>
				</shiro:hasPermission>
			</div>
			<div style="margin-bottom: 5px">
				<span><spring:message code="label.roleId"/>:</span> <input id="roleId" style="line-height: 26px; border: 1px solid #ccc"> 
				<span><spring:message code="label.roleName"/>:</span> <input id="name" style="line-height: 26px; border: 1px solid #ccc"> 
				<span><spring:message code="label.permissions"/>:</span> <input id="permissions" style="line-height: 26px; border: 1px solid #ccc"> 
					
				<span><spring:message code="label.status"/>:</span>
				 <select id="status" name="status" style="width:128px;"> 
				 		 <option value=""></option>  
				         <option value="active"><spring:message code="status.active"/></option>  
        				 <option value="inactive"><spring:message code="status.inactive"/></option> 
				 </select>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" plain="false" onclick="doSearchRole()"><spring:message code="btn.search"/></a> &nbsp;
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" plain="false" onclick="doClearRole()"><spring:message code="btn.clear"/></a>
			</div>
		</div>

	</div>

	<div id="roleDailog"class="easyui-dialog" closed="true" title="User Detail" style="width: 800px; height: 590px"
	data-options="collapsible:true,maximizable:true,resizable:true,cache:false,modal:true"></div>
	

<script type="text/javascript">
	
function doSearchRole() {
	
	$('#roleDataGrid').datagrid('load', {
		roleId : $('#roleId').val(),
		name : $('#name').val(),
		permissions : $('#permissions').val(),
		status : $('#status').val()
	});
}

function doClearRole() {
	$('#roleId').val("");
	$('#name').val("");
	$('#permissions').val("");
	$('#status').val("");
	
	$('#roleDataGrid').datagrid('load', {});
}

function toCreateRole(){  
    $('#roleDailog').dialog('open').dialog('setTitle','<spring:message code="label.createRole"/>');  
    $('#roleDailog').dialog('refresh', '${ctx}/uams/view/toCreateRole');
} 


function toUpdateRole() {

	var row = $('#roleDataGrid').datagrid('getSelected');
	
	if(row){
		var roleId = row.roleId;
		$('#roleDailog').dialog('open').dialog('setTitle','<spring:message code="label.updateRole"/>');
		$('#roleDailog').dialog('refresh', '${ctx}/uams/view/toUpdateRole?roleId=' +roleId);
	}else{
		showWarningMessage('<spring:message code="dialog.systemMsg"/>', '<spring:message code="dialog.noItemSelected"/>');
	}
}


function doDisableRole(){  
    var row = $('#roleDataGrid').datagrid('getSelected');  
    if (row){  
        $.messager.confirm('<spring:message code="dialog.confirm"/>','<spring:message code="dialog.confirmDisableRole"/>',function(r){  
            if (r){  
                $.post('${ctx}/uams/ajax/doDisableRole',{roleId:row.roleId},function(data){        
                    if (data.success){  
                        $('#roleDataGrid').datagrid('reload'); 
                        showInfoMessage('<spring:message code="dialog.success"/>', data.message + ' ('+ data.dataObject + ')');
                    } else {  
                        showErrorMessage('<spring:message code="dialog.error"/>', data.message);
                    }  
                },'json');  
            }  
        });  
    } else{
    	showWarningMessage('<spring:message code="dialog.systemMsg"/>', '<spring:message code="dialog.noItemSelected"/>');
    } 
}  
</script>
</body>

</html>