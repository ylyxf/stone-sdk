<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>stone</title>

<%@ include file="/include/include.jsp"%>
<script type="text/javascript"  src="${appPath}/install/InnerDataSource.js"></script>
</head>
<body>
<div class="container">
	<div class="row" style="padding-top: 30px">
		<div class="col-md-12" >
	  		<form id="dbInfoForm" name="dbInfoForm" class="form-horizontal" method="post"
	  			action="${appPath}/install/checkDataSource.do">
	  		  <input type="hidden" name="dataSource" value="innerDataSource"/>
	  		  <div class="form-group">
			    <label for="dbType" class="col-sm-2 control-label">数据库</label>
			    <div class="col-sm-10">
			    <div class="col-sm-10">
			    <select class="form-control" id="dbType" name="dbType">
					  <option value="PostgreSql">PostgreSql</option>
					  <option value="Oracle">Oracle</option>
				</select>
			  	</div>
			  	</div>
			  </div>
			  <div class="form-group">
			    <label for="dbDriver" class="col-sm-2 control-label">驱动</label>
			    <div class="col-sm-10">
			    <input type="text" class="form-control" id="dbDriver" name="dbDriver" value="org.postgresql.Driver"
			    	placeholder="org.postgresql.Driver" readonly  required>
			  	</div>
			  </div>
			  <div class="form-group">
			    <label for="dbUrl" class="col-sm-2 control-label">URL</label>
			    <div class="col-sm-10">
			    <input type="text" class="form-control" id="dbUrl" name="dbUrl" value="${databaseConnection.dbUrl }"  required
			    	placeholder="jdbc:postgresql://127.0.0.1:5432/stone">
			  	</div>
			  </div>
			  <div class="form-group">
			    <label for="dbUser" class="col-sm-2 control-label">用户名</label>
			    <div class="col-sm-10">
			    <input type="text" class="form-control" id="dbUser" name="dbUser" value="${databaseConnection.dbUser }" required>
			  	</div>
			  </div>
			  <div class="form-group">
			    <label for="dbPassword" class="col-sm-2 control-label">密码</label>
			    <div class="col-sm-10">
			    <input type="text" class="form-control" id="dbPassword" name="dbPassword"  value="${databaseConnection.dbPassword }" required>
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
