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
	<div class="alert alert-success">*未做输入限制请认真填写</div>
	<div class="alert alert-danger" style="padding: 10px 0 10px 60px">
		<form id="ff" method="post" action="${ctx}/api/saveRecord" role="form" class="form-horizontal">
			<%-- <table border="1">
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
						<td><span class="label label-warning">${date }</span><input
							class="easyui-validatebox" type="hidden" name="toDay"
							value="${date}" /></td>
						<td><select name="mainTask" id="mainTask">
								<option value="">请选择任务类型</option>
								<c:forEach items="${taskTypes }" var="taskType">
									<option value="${taskType.id }">${taskType.taskName }</option>
								</c:forEach>
						</select> <select name="taskType" id="taskType">
								<option>请选择任务类型</option>
						</select></td>
						<td><textarea class="easyui-validatebox" name="taskContent"></textarea>
						</td>
						<td><input class="easyui-validatebox" type="text"
							name="hours" size="2" /></td>
						<td>删行</td>
					</tr>
				</tbody>
			</table>
			<div style="text-align: center; padding: 5px">
				<input type="submit" value="提交" />

			</div> --%>

			<h4>========================<span class="label label-success">${date}</span>==========================</h4>
			<div class="form-group">
				<label for="toDay" class="sr-only">toDay</label> 
				<input type="hidden" id="toDay" name="toDay" value="${date}" />
			</div>
			<div class="form-group">
				<label for="mainTask" class="col-sm-2 control-label">任务类型：</label>
				<input type="hidden" id="mainTask" name="mainTask" value="" />
				<div class="col-sm-4">
					<div class="btn-group">
						<button type="button" class="btn btn-default" id="mainTask_but">请选择任务类型</button>
						<button type="button" class="btn btn-default dropdown-toggle"
							data-toggle="dropdown">
							<span class="caret"></span> <span class="sr-only">Toggle
								Dropdown</span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<c:forEach items="${taskTypes }" var="taskType">
								<li><a href="#" onclick="getTaskTypes('${taskType.id}','${taskType.taskName}');">${taskType.taskName }</a></li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="taskType"  class="col-sm-2 control-label"></label>
				<input type="hidden" id="taskType" name="taskType" value="" />
				<div class="col-sm-5">
					<div class="btn-group">
						<button type="button" class="btn btn-default" id="taskType_but">任务类型</button>
						<button type="button" class="btn btn-default dropdown-toggle"
							data-toggle="dropdown">
							<span class="caret"></span> <span class="sr-only">Toggle
								Dropdown</span>
						</button>
						<ul class="dropdown-menu"  id="taskType_opts"></ul>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="taskContent" class="col-sm-2 control-label">任务内容：</label>
				<div class="col-sm-5">
					<textarea class="form-control" rows="3" name="taskContent" id="taskContent"></textarea>
				</div>
				
			</div>
			<div class="form-group">
				<label for="hours" class="col-sm-2 control-label">工作时间：</label>
				<div class="col-sm-5">
					<div class="input-group">
					  <input type="number" class="form-control" name="hours" id="hours">
					  <span class="input-group-addon">.00</span>
					</div>
				</div>
			</div>
			<div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10">
			      <button type="submit" class="btn btn-default">提 交</button>
			    </div>
		  	</div>
		</form>
	</div>
	<script type="text/javascript">
		function getTaskTypes(id,name){
			$("#mainTask_but").text(name);
			$("#mainTask").val(name);
			$("#taskType_but").text("任务类型");
			$.ajax({
				type : "GET",
				url : '${ctx}/api/getTaskTypes?id='+ id,
				dataType : "json",
				success : function(types) {
					$("#taskType_opts > li").remove();
					$(types).each(function() {
						var opt_str = "<li><a href='#'>{0}</a></li>";
						var taskName = this.taskName;
						var opt = $(opt_str.format(taskName));
						opt.click(function(){
							$("#taskType_but").text(taskName);
							$("#taskType").val(taskName);
						});
						$("#taskType_opts").append(opt);
					});
				}
			});
			//taskType_opts
		}
	</script>
</body>
</html>