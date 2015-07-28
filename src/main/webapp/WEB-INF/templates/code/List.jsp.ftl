<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<%@ include file="/include/include.jsp"%>
<title>urplus</title>
<script type="text/javascript"  src="${r"$"}{appPath}/${table.modulePath}/${table.entity}List.js"></script>
</head>
<body>
<div class="easyui-layout"  data-options="fit:true">
<div data-options="region:'north',collapsible:false" style="height:30px;">
<%@ include file="/home/Header.jsp"%>	
</div>
<div data-options="region:'center',title:'${table.entity}'">
<div id="tb" >
	<form id="${table.lowerEntity}ListForm" method="post">
	<input type="hidden" name="easyuiform" value="true">
	<table style="font-size: 12px">
	<tr>
		<td>Label</td>
		<td>
			<input class="easyui-textbox" id="__fieldId__" name="__fieldName__"  > 
		</td>
		<td>
			<a id="query" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">查询</a> 
		</td>
	</tr>
	</table>
	</form>
</div>
<table id="${table.lowerEntity}List" class="easyui-datagrid" toolbar="#tb"  nowrap="false" fit='true' singleSelect='true'
	url="${r"$"}{appPath}/${table.modulePath}/${table.lowerEntity}ListData.do">
	<thead>
	   <tr>
	   	<#list table.columns as column>
		<th field="${column.property}"  width="80px">${column.comment}</th>
		</#list>
	   </tr>
  	</thead>
</table>
</div>
</div>
</body>
</html>