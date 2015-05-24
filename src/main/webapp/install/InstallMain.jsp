<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>stone</title>

<%@ include file="/include/includeBootstrap.jsp"%>
</head>
<body>
<div class="container">
	<div class="row" style="padding-top: 30px">
		<div class="col-md-12" >
	  		 <h3>欢迎使用stone，系统检测到您还没有设置数据库，请选择连接数据库的方式：</h3>
	  		 <br/> 
	  		 <a href="${appPath}/install/dataSourceInfo.do?dataSource=innerDataSource" type="button" class="btn btn-default btn-lg" >内置数据源</a>
  			 <a href="${appPath}/install/dataSourceInfo.do?dataSource=containerDataSource" type="button" class="btn btn-primary btn-lg">容器数据源</a>
	  		 <br/><br/><br/>
	  		 <h4>容器数据源与内置数据源的区别：</h4>
	  		 <p>容器数据源：数据库连接由容器来管理，提供jndi name供stone使用。在我们能够控制容器的时候，可以使用这种方式来进行安装。
	  		 </p>
	  		 <p>内置数据源：数据库连接由stone内置的spring来管理，当我们没有权限操作容器创建数据源的时候，我们采取这种办法来进行安装。
	  		 </p>
	  	</div>
	</div>  
</div>
	    
</body>
</html>
