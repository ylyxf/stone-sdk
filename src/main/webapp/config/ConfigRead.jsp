<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/includeSegment.jsp"%>
<script type="text/javascript" src="${appPath}/config/ConfigRead.js"></script>
<form id="configForm" method="post" >
	<input type="hidden" id="classCode" value="${configClass.code}">
	<table  class="form-layout" style="width:90%">
		<c:forEach var="config" items="${configList}"  >
			<tr>
				<td style="width:10%">${config.label}：</td>
				<td>${config.value}</td>
				<td>${config.comment}</td>
			</tr> 
		</c:forEach>
	</table>
	<div style="text-align:left;padding:15px">
		<a href="#" id="editBtn" class="easyui-linkbutton">编辑</a>
	</div>
</form>
