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
	  		 <h3>即将创建stone相关的数据库资源（表、视图、函数等）：</h3>
	  		 <p class="bg-danger"><br/>
	  			验证成功!<br/> 
	  		 	创建过程会删除与stone同名的数据库资源，请确保在操作前，您已经备份了指定数据库中的数据。<br/>&nbsp;</p> 
	  		 <a href="${appPath}/install/InitDatabase.do" type="button" class="btn btn-primary btn-lg" >开始创建</a>
	  	</div>
	  	<div class="col-sm-12">
			 <p class="bg-danger">${errmsg }</p>
		</div>
	</div>  
</div>
	    
</body>
</html>
