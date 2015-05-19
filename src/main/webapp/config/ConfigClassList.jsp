<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>配置</title>

<%@ include file="/include/include.jsp"%>
<script type="text/javascript" src="${appPath}/config/ConfigClassList.js"></script>
</head>
<body >
	<div class="easyui-layout" style="width:100%;height:100%;">
		<div data-options="region:'west',split:true" title="配置" style="width:100px;">
			<ul id="configClassList" class="easyui-datalist" >
			<c:forEach var="configClass" items="${configClassList}"  >
			 	<li value="${configClass.code}">
			 	${configClass.label}
				</li>
			</c:forEach>
			</ul>					
		</div>
        <div id="configEditPanel" data-options="region:'center',title:'配置详情',iconCls:'icon-ok'">
        </div>	
	
	</div>
</body>
</html>
