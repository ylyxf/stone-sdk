<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<%@ include file="/include/include.jsp"%>
<script type="text/javascript" src="${appPath}/role/RoleEdit.js"></script>
</head>
<body class='default'>
<div class="easyui-panel" title="编辑字典"  >
<form id="roleEditForm" method="post" action="${appPath}/role/RoleEdit.do">
	<input type="hidden" name="id" value="${role.id }">
	<table class="form-layout">
		<tr>
			<td>名称：</td>
			<td>
				<input class="easyui-textbox" type="text" name="name" value="${role.name }" data-options="required:true"/>  
			</td>
		</tr>
		<tr>
			<td>会否启用：</td>
			<td>
				<input type="checkbox" id="enabled" name="enabled"  style="vertical-align:middle;"
				 		<c:if test="${role.enabled }">
				 			 checked="true"
				 		</c:if>
				 />
				 <label for="enabled"  style="vertical-align:middle;">是否可用</label>
			</td>
		</tr>
	</table>
	<div style="text-align:left;padding:15px">
		<button id="saveBtn"   class="easyui-linkbutton">保存</button>
		<a href="${ appPath}/role/RoleRead.do?id=${role.id }" id="listBtn" class="easyui-linkbutton">取消</a>
	</div>
</form>
</div>
</body>
</html>