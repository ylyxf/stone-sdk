<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>stone</title>
<%@ include file="/include/include.jsp"%>
<c:if test="${ not empty param.distUrl }">
		<jsp:forward page="${param.distUrl }"></jsp:forward>
</c:if>
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
<body  class='default'>
	<div class="easyui-tabs" id="mainTabs" data-options="fit:true" >
		<div title="主页" >
			<iframe id="主页_FRAME" src="${appPath}/index/Main.do" scrolling="no" frameborder="0" class="tab-frame"></iframe>
		</div>
	</div>
</body>
</html>
