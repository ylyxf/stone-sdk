<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<%@ include file="/include/include.jsp"%>
<script type="text/javascript" src="${appPath}/role/RoleAdd.js"></script>
</head>
<body class='default'>
	<div class="easyui-panel" title="新增角色" style="width: 100%; height: 100%;">
		<form id="roleAddForm" method="post" action="${appPath}/role/RoleAdd.do">
			<table class="form-layout">
				<tr>
					<td>名称：</td>
					<td>
						<input class="easyui-textbox" type="text" name="name" data-options="required:true"/>
					</td>
				</tr>
				<tr>
					<td>是否可用：</td>
					<td>
						<input type="checkbox" id="enabled" name="enabled"  style="vertical-align:middle;" checked="true"/>
						<label for="enabled"  style="vertical-align:middle;">是否可用</label>
					</td>
				</tr>
			</table>
			<div style="text-align:left;padding:15px">
				<button id="saveBtn"  class="easyui-linkbutton">保存</button>
				<a href="${ appPath}/role/RoleList.do" id="listBtn" class="easyui-linkbutton">返回</a>
			</div>
		</form>
	</div>
</body>
</html>
