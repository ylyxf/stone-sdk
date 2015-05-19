<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<%@ include file="/include/include.jsp"%>
<script type="text/javascript" src="${appPath}/role/RoleRead.js"></script>
</head>
<body class='default'>
<div class="easyui-panel" title="字典详情"  >
<form id="roleReadForm" method="post" action="${appPath}/role/RoleEditInit.do">
	<input type="hidden" name="id" value="${role.id}">
	<table class="form-layout">
		<tr>
			<td>名称:</td>
			<td>
				 ${role.name}
			</td>
			
		</tr>
		<tr>
			<td>是否启用:</td>
			<td>
				${role.enabled} 
			</td>
		</tr>
	</table>
	<div style="text-align:left;padding:15px">
		<button  id="editBtn" class="easyui-linkbutton">编辑</button>
		<a href="${appPath}/role/RoleList.do" id="listBtn" class="easyui-linkbutton">返回</a>
	</div>
</form>
</div>
</body>
</html>
