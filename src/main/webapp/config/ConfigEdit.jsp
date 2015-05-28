<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/includeSegment.jsp"%>
<script type="text/javascript" src="${appPath}/config/ConfigEdit.js"></script>
<form id="configForm" method="post" action="${appPath}/config/ConfigEdit.do" >
	<input type="hidden" id="classCode" name="classCode" value="${configClass.code}">
	<table  class="form-layout" style="width:90%">
		<c:forEach var="config" items="${configList}" varStatus="status" >
			<tr>
				<td style="width:10%">${config.label}：</td>
				<td>
					<input type="hidden" name="configList[${status.index }].code" value="${config.code}">
					<input type="hidden" name="configList[${status.index }].classCode" value="${config.classCode}">
					<input type="hidden" name="configList[${status.index }].encode" value="${config.encode}">
					<input type="text" name="configList[${status.index }].value" class="easyui-textbox"  style="width:600px" value="${config.value}">
				</td>
				<td>${config.comment}</td>
			</tr> 
		</c:forEach>
	</table>
	<div style="text-align:left;padding:15px">
		<a href="#" id="saveBtn" class="easyui-linkbutton">保存</a>
	</div>
</form>
