<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>stone</title>
<%@ include file="/include/includeBootstrap.jsp"%>
<script type="text/javascript" src="${appPath}/develop/Index.js"></script>
</head>
<body>
 <div class="container">
<div class="row" style="padding-top: 30px">
	<div class="col-md-12" >
		 <h1>${project.name}</h1>
		 <h3>位置:${project.path}</h3>
		 <h3>基本包名:${project.basicPackagePath}</h3>
		 <button id="initProject" class="btn btn-default" type="button">初始化项目</button>
	</div>
</div>
</div>
</body>
</html>
