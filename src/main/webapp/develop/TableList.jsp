<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<%@ include file="/include/include.jsp"%>
<script type="text/javascript" src="${appPath}/develop/TableList.js"></script>
</head>
<body>
	<div class="easyui-panel" title="数据库表清单" style="width: 100%; height: 100%;">
	<div id="tb">
	<form id="tableListForm" method="post">
	<input type="hidden" name="easyuiform" value="true">
		<table style="width: 100%">
		<tr>
		<td>
			模块包名：
			<input class="easyui-textbox" id="modulePackageName" name="modulePackageName">
			忽略前缀：
			<input class="easyui-textbox" id="ignorePrefix" name="ignorePrefix" >
			
			<a id="generateCode" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">生成代码</a>
			表名：
			<input class="easyui-textbox" id="name" name="name" >
			<a id="query" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">查询</a>
		</td>
		<td style="text-align: right;">
			<a id="initProject" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" >初始化项目</a>
		</td>
		</tr>
		<tr>
		<td colspan="2">
			生成模板：
			<c:forEach items="${codeFileNameList}" var="codeFileName">
			<label class="checkbox-inline"  >
		  		<input type="checkbox" id="codeName${codeFileName}" name="codeFileNameList" value="${codeFileName}" >${codeFileName}
			</label>
			</c:forEach>
		</td>
		</tr>
		</table>
		</form>
	</div>
	<table id="tableList" class="easyui-datagrid"  pagination="true" toolbar="#tb"  fit='true' pageSize='20'
		url="${appPath}/develop/tableListData.do">
		<thead>
			<tr>
				<th field="name" sortable="true" width="320">名称</th>
				<th field="comment" width="320">备注</th>
			</tr>
		</thead>
	</table>
	</div>
</body>
</html>
