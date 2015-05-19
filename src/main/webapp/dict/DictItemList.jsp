<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<%@ include file="/include/include.jsp"%>
<script type="text/javascript" src="${appPath}/dict/DictItemList.js"></script>
</head>
<body class='default'>
	<div class="easyui-panel" title="${dict.name}的字典项清单" style="width: 100%; height: 100%;">
		<form id="dictItemListForm" method="post">
			<input type="hidden" name="easyuiform" value="true">
			<div id="tb">
				<a id="add" href="${appPath}/dict/DictItemAddInit.do?dictCode=${dict.code}" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a> 
				<a id="edit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</a>
				<a id="remove" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a> 
				<a id="read" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">查看</a>
				<a id="back" href="${appPath}/dict/DictList.do" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true">返回字典列表</a>
			</div>
			<table id="dictItemList" class="easyui-datagrid"  pagination="true" toolbar="#tb" data-options="queryParams:{dictCode:'${dict.code}'}"
				url="${appPath}/dict/dictItemListData.do">
				<thead>
					<tr>
						<th data-options="field:'id',checkbox:true"></th>
						<th field="label" width="120">名称</th>
						<th field="value" width="120">值</th>
						<th field="sortNo" width="120">序号</th>
						<th field="remark" width="120">备注</th>
					</tr>
				</thead>
			</table>
		</form>
	</div>
</body>
</html>
