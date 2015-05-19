<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<%@ include file="/include/include.jsp"%>
<script type="text/javascript" src="${appPath}/role/RoleUserList.js"></script>
</head>
<body class='default'>
	<div class="easyui-panel" title="角色“${role.name}”的用户清单" style="width: 100%; height: 100%;">
    	<form id="roleUserForm" method="post">
    		<input type="hidden" name="easyuiform" value="true">
    		<input type="hidden"  id="roleId" name="roleId" value="${role.id}">
			<div id="tb">
				<select id="groupUserCode" name="groupUserCode" class="easyui-combotree" style="width:200px;" multiple  data-options="url:'${appPath}/group/groupUser/groupUserTreeData.do'">
    			</select>
				<a id="add" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">关联用户</a> 
				<a id="remove" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">移除用户</a> 
				<a id="back" href="${appPath}/role/RoleList.do" class="easyui-linkbutton" data-options="iconCls:'icon-back',plain:true">返回</a>
			</div>
			<table id="roleUserList" class="easyui-datagrid"  pagination="true" toolbar="#tb" data-options="queryParams:{roleId:'${role.id}',easyuiform:true}"
			 url="${appPath}/role/roleUserListData.do">
				<thead>
					<tr>
						<th data-options="field:'id',checkbox:true"></th>
						<th field="userName" width="160">用户名</th>
						<th field="groupName" width="600">所在群组</th>
					</tr>
				</thead>
			</table>
		</form>
	</div>
</body>
</html>
