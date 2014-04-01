<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	request.setAttribute("ctx", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style >
.nowCalendar td{width: 50px;height: 50px;}
</style>
</head>
<body>
<table border="2" style="width: 100%;" class="nowCalendar">
	<thead>
		<tr>
			<th width="15%">日</th>
			<th width="14%">一</th>
			<th width="14%">二</th>
			<th width="14%">三</th>
			<th width="14%">四</th>
			<th width="14%">五</th>
			<th width="15%">六</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${nowCalendar}" var="weeks">
			<tr>
				<c:forEach items="${weeks}" var="week">
					<td>
						<div>${week.date}</div>
						<c:if test="${fn:length(week.records) !=0 }">
							<c:forEach items="${week.records }" var="record">
								${record.taskContent }<br />
							</c:forEach>
						</c:if>
						<c:if test='${week.noWweekMonth == "T"}'>
							<div><a href="javascript:void(0);" onclick="toCreateRecord('${week.date}');">填写日志</a></div>
						</c:if>
					</td>
				</c:forEach>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div id="fillLog_dialog"></div>
<script type="text/javascript">
function toCreateRecord(date){
	$("#fillLog_dialog").dialog({
		title: '填写日志【'+date+'】',
		width: 700,
		height: 200,
		closed: false,
		cache: false,
		href: '${ctx}/view/createRecord?date='+date,
		modal: true
	});
}
</script>
</body>
</html>