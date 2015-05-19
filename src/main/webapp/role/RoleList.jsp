<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<%@ include file="/include/include.jsp"%>
<script type="text/javascript" src="${appPath}/role/RoleList.js"></script>
</head>
<body class='default'>
	<div class="easyui-panel" title="角色清单" style="width: 100%; height: 100%;">
		<form id="roleListForm" method="post">
			<input type="hidden" name="easyuiform" value="true">
			<div id="tb">
				<a id="add" href="${appPath}/role/RoleAddInit.do" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a> 
				<a id="read" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">查看</a>
				<a id="assignUser" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">分配用户</a>
				<a id="assignOperation" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">分配权限</a>
				<a id="remove" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a> 
			</div>
			<table id="roleList" class="easyui-datagrid"  pagination="true" toolbar="#tb"
				url="${appPath}/role/roleListData.do">
				<thead>
					<tr>
						<th data-options="field:'id',checkbox:true"></th>
						<th field="name" width="120">角色名称</th>
						<th field="enabled" width="60">是否启用</th>
					</tr>
				</thead>
			</table>
		</form>
	</div>
</body>
</html>
