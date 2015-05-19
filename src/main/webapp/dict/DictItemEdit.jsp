<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<%@ include file="/include/include.jsp"%>
<script type="text/javascript" src="${appPath}/dict/DictItemEdit.js"></script>
</head>
<body class='default'>
<div class="easyui-panel" title="编辑字典项"  >
<form id="dictEditForm" method="post" action="${appPath}/dict/DictItemEdit.do">
	<input type="hidden" id="id" name="id" value="${dictItem.id}">
	<input type="hidden" id="dictCode" name="dictCode" value="${dictItem.dictCode}">
	<table class="form-layout">
		<tr>
			<td>名称：</td>
			<td>
				<input class="easyui-textbox" type="text" name="label"  value="${dictItem.label }"
					data-options="required:true"/>
			</td>
		</tr>
		<tr>
			<td>值：</td>
			<td>
				<input class="easyui-textbox" type="text" name="value" value="${dictItem.value }"
					 data-options="required:true"/>
			</td>
			
		</tr>
		<tr>
			<td>序号：</td>
			<td>
				<input class="easyui-numberbox" type="text" name="sortNo" value="${dictItem.sortNo }" data-options="required:true"/>
			</td>
		</tr>
		<tr>
			<td>备注：</td>
			<td>
				<input class="easyui-textbox" type="text" name="remark" value="${dictItem.remark }" />
			</td>
		</tr>
	</table>
	<div style="text-align:left;padding:15px">
		<button id="saveBtn"   class="easyui-linkbutton">保存</button>
		<a href="${appPath}/dict/DictItemList.do?dictCode=${dictItem.dictCode}" id="listBtn" class="easyui-linkbutton">返回</a>
	</div>
</form>
</div>
</body>
</html>