<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<%@ include file="/include/include.jsp"%>
<script type="text/javascript" src="${appPath}/dict/DictItemRead.js"></script>
</head>
<body class='default'>
<div class="easyui-panel" title="字典项详情"  >
<form id="dictReadForm" method="post" action="${appPath}/dict/DictItemEditInit.do">
	<input type="hidden" id="id" name="id" value="${dictItem.id}" />
	<table class="form-layout">
		<tr>
			<td>名称:</td>
			<td>
				 ${dictItem.label}
			</td>
		</tr>
		<tr>
			<td>值:</td>
			<td>
				 ${dictItem.value}
			</td>
		</tr>
		<tr>
			<td>序号：</td>
			<td>
				 ${dictItem.sortNo } 
			</td>
		</tr>
		<tr>
			<td>备注：</td>
			<td>
				 ${dictItem.remark } 
			</td>
		</tr>
	</table>
	<div style="text-align:left;padding:15px">
		<button  id="editBtn" class="easyui-linkbutton">编辑</button>
		<a href="${appPath}/dict/DictItemList.do?dictCode=${dictItem.dictCode}" id="listBtn" class="easyui-linkbutton">返回</a>
	</div>
</form>
</div>
</body>
</html>
