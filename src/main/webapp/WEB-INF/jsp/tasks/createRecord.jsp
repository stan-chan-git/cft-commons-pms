<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setAttribute("ctx", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="padding: 10px 0 10px 60px">
		<form id="ff" method="post" action="${ctx}/api/saveRecord">
			<table border="1">
				<thead>
					<tr>
						<th>时间</th>
						<th>任务类型</th>
						<th>任务内容</th>
						<th>工作时间</th>
						<th>增行</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${date }<input class="easyui-validatebox" type="hidden"
								name="toDay" value="${date}"/></td>
						<td>
							<select name="mainTask" id="mainTask" >
								<option value="">请选择任务类型</option>
								<c:forEach items="${taskTypes }" var="taskType">
									<option value="${taskType.id }">${taskType.taskName }</option>
								</c:forEach>
							</select>
							<select name="taskType" id="taskType">
								<option>请选择任务类型</option>
							</select>
						</td>
						<td>
							<textarea class="easyui-validatebox" name="taskContent"></textarea>
						</td>
						<td>
							<input class="easyui-validatebox" type="text"
								name="hours" size="2"/>
						</td>
						<td>
							删行
						</td>				
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<div style="text-align: center; padding: 5px">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="submitForm()">Submit</a>
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
		$(function(){
			$("#mainTask").change(function(){
				var id = $(this).val();
				if(id != ""){
				    $('#taskType').combobox({
				        url:'${ctx}/api/getTaskTypes?id='+id,
				        valueField:'id',
				        textField:'taskName'
				  	});
				}
			});
		});
	</script>
</body>
</html>