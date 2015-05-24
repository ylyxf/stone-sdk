<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>stone</title>

<%@ include file="/include/includeBootstrap.jsp"%>
<script type="text/javascript"  src="${appPath}/install/ContainerDataSource.js"></script>
</head>
<body>
<div class="container">
	<div class="row" style="padding-top: 30px">
		<div class="col-md-12" >
	  		<form id="dbInfoForm" name="dbInfoForm" class="form-horizontal" method="post"
	  			action="${appPath}/install/checkDataSource.do">
	  		  <input type="hidden" name="dataSource" value="containerDataSource"/>
	  		  <div class="form-group">
			    <label for="dbType" class="col-sm-2 control-label">数据库</label>
			    <div class="col-sm-10">
			    <input type="text" class="form-control" id="dbType" name="dbType" value="PostgreSql" readonly  required>
			  	</div>
			  </div>
	  		  <div class="form-group">
			    <label for="dbType" class="col-sm-2 control-label">数据源名称</label>
			    <div class="col-sm-10">
			    <input type="text" class="form-control" id="jndiName" name="jndiName" value="${databaseConnection.jndiName }" required
			     placeholder="java:comp/env/jdbc/stone">
			  	</div>
			  </div>
			  <div class="col-sm-12">
			  	<p class="bg-danger">${errmsg }</p>
			  </div>
			  <div class="col-sm-2"></div>
			  <div class="col-sm-10">
			  	<button id="checkBtn" type="submit" class="btn btn-default">验证连接</button>
			  </div>
			</form>
	  	</div>
	</div>  
</div>
	    
</body>
</html>
