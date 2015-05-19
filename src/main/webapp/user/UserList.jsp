<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<%@ include file="/include/includeSegment.jsp"%>
<script type="text/javascript" src="${appPath}/user/UserList.js"></script>
</head>
<body class='default'>
	<div class="easyui-panel" title="群组${group.name}的人员清单" style="width: 100%; height: 100%;">
		<form id="userListForm" method="post">
			<input type="hidden" name="easyuiform" value="true">
			<div id="tb">
				<a id="add" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a> 
				<a id="read" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">查看</a>
			</div>
			<table id="userList" class="easyui-datagrid"  pagination="true" toolbar="#tb" data-options="queryParams:{groupId:'${group.id}'}"
				url="${appPath}/user/userListData.do">
				<thead>
					<tr>
						<th data-options="field:'id',checkbox:true"></th>
						<th field="name" width="120">姓名</th>
						<th field="phone" width="120">手机</th>
						<th field="email" width="120">邮箱</th>
						<th field="account" width="120">账户</th>
						<th field="enabled" width="120">是否可用</th>
					</tr>
				</thead>
			</table>
		</form>
	</div>
</body>
</html>
