<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<%@ include file="/include/include.jsp"%>
<script type="text/javascript" src="${appPath}/dict/DictAdd.js"></script>
</head>
<body class='default'>
	<div class="easyui-panel" title="新增字典" style="width: 100%; height: 100%;">
		<form id="dictAddForm" method="post" action="${appPath}/dict/DictAdd.do">
			<table class="form-layout">
				<tr>
					<td>编码：</td>
					<td>
						<input class="easyui-textbox" type="text" name="code" data-options="required:true"/>
					</td>
				</tr>
				<tr>
					<td>名称：</td>
					<td>
						<input class="easyui-textbox" type="text" name="name" data-options="required:true"/>
					</td>
				</tr>
				<tr>
					<td>序号：</td>
					<td>
						<input class="easyui-numberbox" type="text" name="sortNo" />
					</td>
				</tr>
				<tr>
					<td>备注：</td>
					<td>
						<input class="easyui-textbox" type="text" name="remark" />
					</td>
				</tr>
			</table>
			<div style="text-align:left;padding:15px">
				<button id="saveBtn"  class="easyui-linkbutton">保存</button>
				<a href="${ appPath}/dict/DictList.do" id="listBtn" class="easyui-linkbutton">返回</a>
			</div>
		</form>
	</div>
</body>
</html>
