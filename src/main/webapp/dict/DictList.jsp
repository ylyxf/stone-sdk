<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<%@ include file="/include/include.jsp"%>
<title>stone-sdk</title>
<script type="text/javascript" src="${appPath}/dict/DictList.js"></script>
</head>
<body>
<div class="easyui-layout"  data-options="fit:true">
<div data-options="region:'north',collapsible:false" style="height:30px;">
<%@ include file="/home/Header.jsp"%>	
</div>
<div data-options="region:'center',title:'字典清单'">
<div id="tb" >
	<form id="dictListForm" method="post">
		<input type="hidden" name="easyuiform" value="true">
		<a id="add" href="${appPath}/dict/DictAddInit.do" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a> 
		<a id="edit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</a>
		<a id="remove" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a> 
		<a id="read" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">查看</a>
		<a id="items" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-config',plain:true">字典项</a>
	</form>
</div>
<table id="dictList" class="easyui-datagrid"  pagination="true" toolbar="#tb"  fit='true' 
	url="${appPath}/dict/dictListData.do">
	<thead>
		<tr>
			<th data-options="field:'code',checkbox:true"></th>
			<th field="name" width="120">名称</th>
			<th field="remark" width="120">备注</th>
		</tr>
	</thead>
</table>
</div>
</div>
</body>
</html>
