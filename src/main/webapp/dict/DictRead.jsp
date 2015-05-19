<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<%@ include file="/include/include.jsp"%>
<script type="text/javascript" src="${appPath}/dict/DictRead.js"></script>
</head>
<body class='default'>
<div class="easyui-panel" title="字典详情"  >
<form id="dictReadForm" method="post" action="${appPath}/dict/DictEditInit.do">
	<input type="hidden" name="code" value="${dict.code}">
	<table class="form-layout">
		<tr>
			<td>编码:</td>
			<td>
				 ${dict.code}
			</td>
			
		</tr>
		<tr>
			<td>名称:</td>
			<td>
				 ${dict.name}
			</td>
		</tr>
		<tr>
			<td>序号:</td>
			<td>
				 ${dict.sortNo}
			</td>
		</tr>
		<tr>
			<td>备注：</td>
			<td>
				 ${dict.remark}
			</td>
		</tr>
	</table>
	<div style="text-align:left;padding:15px">
		<button  id="editBtn" class="easyui-linkbutton">编辑</button>
		<a href="${appPath}/dict/DictList.do" id="listBtn" class="easyui-linkbutton">返回</a>
	</div>
</form>
</div>
</body>
</html>
