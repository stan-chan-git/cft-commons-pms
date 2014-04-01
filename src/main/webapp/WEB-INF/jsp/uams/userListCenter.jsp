<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<html>  
<head>

<%@ include file="/static/commons/spring-taglibs.jsp"%>

</head>
<body>

	<div id="userListCenter" >
		<!--========================= UserList DataGrid =========================-->
		<table id="userDataGrid" class="easyui-datagrid" title="<spring:message code="label.userList"/>" style="width: auto; height: auto"
			data-options="singleSelect:true,collapsible:true,url:'${ctx}/uams/ajax/getUserList'" rownumbers="true" pagination="false" pageSize="10" fit="false"
			fitColumns="false" striped="true" toolbar="#tb_userDataGrid">
			<thead>
				<tr>
					<th data-options="field:'ck'" checkbox="true"></th>
					<th data-options="field:'userId',width:'80'" sortable="true"><spring:message code="label.userId"/></th>
					<th data-options="field:'loginName',align:'center',width:'200'" sortable="true"><spring:message code="label.loginId"/></th>
					<th data-options="field:'name',width:'120'"><spring:message code="label.staffName"/></th>
					<th data-options="field:'email',align:'center',width:'200'"><spring:message code="label.email"/></th>
					<th data-options="field:'phone',align:'center',width:'190'"><spring:message code="label.phone"/></th>
					<th data-options="field:'office',align:'center',width:'100'"><spring:message code="label.office"/></th>
					<th data-options="field:'dept',align:'center'"><spring:message code="label.dept"/></th>
					<th data-options="field:'status',align:'center',width:'100'" sortable="true"><spring:message code="label.status"/></th>
					<th data-options="field:'remark',width:'150'"><spring:message code="label.remark"/></th>
				</tr>
			</thead>
		</table>
		<div id="tb_userDataGrid" style="padding: 3px; height: auto">
			<div style="margin-bottom: 5px">
				<shiro:hasPermission name="user:create">	
				<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"onclick="toCreateUser()"><spring:message code="btn.createUser"/></a>
				</shiro:hasPermission> 
				<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="toUpdateUser()"><spring:message code="btn.updateUser"/></a>
				<shiro:hasPermission name="user:update">
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"onclick="removeUser()"><spring:message code="btn.removeUser"/></a>
				</shiro:hasPermission>
			</div>
			<div style="margin-bottom: 5px">
				<span><spring:message code="label.userId"/>:</span> <input id="userId" style="line-height: 26px; border: 1px solid #ccc"> 
				<span><spring:message code="label.loginId"/>:</span> <input id="loginName" style="line-height: 26px; border: 1px solid #ccc"> 
				<span><spring:message code="label.staffName"/>:</span> <input id="name" style="line-height: 26px; border: 1px solid #ccc"> 
					
				<span><spring:message code="label.status"/>:</span>
				 <select id="status" name="status" style="width:128px;"> 
				 		 <option value=""></option>  
				         <option value="active"><spring:message code="status.active"/></option>  
        				 <option value="inactive"><spring:message code="status.inactive"/></option> 
				 </select>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" plain="false" onclick="doSearchUser()"><spring:message code="btn.search"/></a> &nbsp;
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" plain="false" onclick="doClearUser()"><spring:message code="btn.clear"/></a>
			</div>
		</div>

	</div>

	<!--========================= User Create Dailog =========================-->	
	<div id="userDailog"class="easyui-dialog" closed="true" title="User Detail" style="width: 800px; height: 590px"
	data-options="collapsible:true,maximizable:true,resizable:true,cache:false,modal:true"></div>
	

<script type="text/javascript">
	
var userTipRefresh = function(ishide){};
var userTipRemove = function(){};

function doSearchUser() {
	
	$('#userDataGrid').datagrid('load', {
		userId : $('#userId').val(),
		loginName : $('#loginName').val(),
		name : $('#name').val(),
		status : $('#status').val()
	});
}

function doClearUser() {
	$('#userId').val("");
	$('#loginName').val("");
	$('#name').val("");
	$('#status').val("");
	
	$('#userDataGrid').datagrid('load', {});
}

function toCreateUser(){  
	$('#userDailog').dialog({
		onClose : function() {
			userTipRemove();
		},
		onMove : function(){
			userTipRefresh(false);
		},
		onResize : function(){
			userTipRefresh(false);
		},
		onMove : function(){
			userTipRefresh(false);
		},
		onMaximize : function(){
			userTipRefresh(false);
		},
		onRestore : function(){
			userTipRefresh(false);
		},
		onMinimize : function(){
			userTipRefresh(true);
		},
		onCollapse : function(){
			userTipRefresh(true);
		},
		onExpand : function(){
			userTipRefresh(false);
		}
	});
    $('#userDailog').dialog('open').dialog('setTitle','<spring:message code="label.createUser"/>');
    $('#userDailog').dialog('refresh', '${ctx}/uams/view/toCreateUser');

} 


function toUpdateUser() {

	var row = $('#userDataGrid').datagrid('getSelected');
	
	if(row){
		var userId = row.userId;
		$('#userDailog').dialog('open').dialog('setTitle','<spring:message code="label.updateUser"/>');
		$('#userDailog').dialog('refresh', '${ctx}/uams/view/toUpdateUser?userId=' +userId);
	}else{
		showWarningMessage('<spring:message code="dialog.systemMsg"/>', '<spring:message code="dialog.noItemSelected"/>');
	}
}


function removeUser(){  
    var row = $('#userDataGrid').datagrid('getSelected');  
    if (row){  
        $.messager.confirm('<spring:message code="dialog.confirm"/>','<spring:message code="dialog.confirmDeleteUser"/>',function(r){  
            if (r){  
                $.post('${ctx}/uams/ajax/deleteUser',{userId:row.userId},function(data){        
                    if (data.success){  
                        $('#userDataGrid').datagrid('reload');    // reload the user data  
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