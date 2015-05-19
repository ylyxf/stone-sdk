<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<%@ include file="/include/include.jsp"%>
<script type="text/javascript" src="${appPath}/dict/DictItemAdd.js"></script>
</head>
<body class='default'>
	<div class="easyui-panel" title="新增字典项" style="width: 100%; height: 100%;">
		<form id="dictItemAddForm" method="post" action="${appPath}/dict/DictItemAdd.do">
		<input type="hidden" name="dictCode" value="${param.dictCode}">
			<table class="form-layout">
				<tr>
					<td>名称：</td>
					<td>
						<input class="easyui-textbox" type="text" name="label" data-options="required:true"/>
					</td>
				</tr>
				<tr>
					<td>值：</td>
					<td>
						<input class="easyui-textbox" type="text" name="value" data-options="required:true"/>
					</td>
				</tr>
				<tr>
					<td>序号：</td>
					<td>
						<input class="easyui-numberbox" type="text" name="sortNo" data-options="required:true"/>
					</td>
				</tr>
				<tr>
					<td>备注：</td>
					<td>
						<input class="easyui-textbox" type="text" name="remark"/>
					</td>
				</tr>
			</table>
			<div style="text-align:left;padding:15px">
				<button id="saveBtn"  class="easyui-linkbutton">保存</button>
				<a href="${appPath}/dict/DictItemList.do?dictCode=${param.dictCode}" id="listBtn" class="easyui-linkbutton">返回</a>
			</div>
		</form>
	</div>
</body>
</html>
