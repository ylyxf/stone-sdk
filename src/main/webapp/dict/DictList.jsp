<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<%@ include file="/include/include.jsp"%>
<script type="text/javascript" src="${appPath}/dict/DictList.js"></script>
</head>
<body class='default'>
	<div class="easyui-panel" title="字典清单" style="width: 100%; height: 100%;">
		<form id="dictListForm" method="post">
			<input type="hidden" name="easyuiform" value="true">
			<div id="tb">
				<a id="add" href="${appPath}/dict/DictAddInit.do" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a> 
				<a id="edit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</a>
				<a id="remove" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a> 
				<a id="read" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">查看</a>
				<a id="items" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-config',plain:true">字典项</a>
			</div>
			<table id="dictList" class="easyui-datagrid"  pagination="true" toolbar="#tb"
				url="${appPath}/dict/dictListData.do">
				<thead>
					<tr>
						<th data-options="field:'code',checkbox:true"></th>
						<th field="name" width="120">名称</th>
						<th field="remark" width="120">备注</th>
					</tr>
				</thead>
			</table>
		</form>
	</div>
</body>
</html>
