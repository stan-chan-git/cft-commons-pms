<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setAttribute("ctx", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>application</title>
</head>
<body>
	<div>
		<table id="jsonmap"></table>
		<div id="pjmap"></div>
	</div>
	<script type="text/javascript">
		$(function() {
			jQuery("#jsonmap").jqGrid({
				url : '${ctx}/api/findAllApplication',
				datatype : "json",
				colNames : [ 'id', 'firstName', 'lastName', 'phone', 'email' ],
				colModel : [ 
				    {name : 'id',index : 'id',width : 55}, 
				    {name : 'firstName',index : 'firstName',width : 90,jsonmap : "firstName"}, 
				    {name : 'lastName',index : 'lastName asc, invdate',width : 100}, 
				    {name : 'phone',index : 'phone',width : 80,align : "right"}, 
				    {name : 'email',index : 'email',width : 80,align : "right"} 
				],
				rowNum : 10,
				rowList : [ 10, 20, 30 ],
				pager : '#pjmap',
				sortname : 'id',
				viewrecords : true,
				sortorder : "desc",
				jsonReader : {
					repeatitems : false,
					id : "0"
				},
				caption : "JSON Mapping",
				height : '100%',
				width : '700'
			});
			jQuery("#jsonmap").jqGrid('navGrid', '#pjmap', {
				edit : false,
				add : false,
				del : false
			});
		});
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