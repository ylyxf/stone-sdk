<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>stone</title>

<%@ include file="/include/include.jsp"%>
<script type="text/javascript"  src="${appPath}/group/GroupMain.js"></script>
</head>
<body  class='default'>
	 <div class="easyui-layout" style="width:100%;height:100%;">
		<div data-options="region:'west',split:true" title="组织" style="width:200px;">
			<ul id="groupTree" class="easyui-tree" url="${appPath}/group/groupTreeData.do">
			</ul>
		</div>
        <div id="groupDetail" data-options="region:'center',title:'组织详情',iconCls:'icon-ok'">
        </div>	
	
	</div>
</body>
</html>
