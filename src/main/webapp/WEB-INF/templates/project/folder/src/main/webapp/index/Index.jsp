<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>mayflower</title>

<%@ include file="/include/include.jsp"%>
<style>
html, body {
	width: 100%;
	height: 100%;
	margin:0px;
	overflow: hidden;
	
}
</style>
<script type="text/javascript"  src="${appPath}/index/Index.js"></script>
</head>
<body class='default'>
	<div class="easyui-layout" style="width:100%;height:100%;">
		 <div data-options="region:'north'" style="height:40px"> 
			<%@ include file="/navigation/Navigation.jsp"%> 
		</div>
		<div id="workspace" data-options="region:'center'" style="overflow: hidden;">
		</div>
	</div>
</body>
</html>
