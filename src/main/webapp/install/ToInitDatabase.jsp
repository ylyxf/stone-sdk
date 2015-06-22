<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>stone</title>

<%@ include file="/include/include.jsp"%>
</head>
<body>
<div class="container">
	<div class="row" style="padding-top: 30px">
		<div class="col-sm-12">
			 <p class="bg-danger">${errmsg }</p>
		</div>
		<div class="col-sm-12">
			 <h3>验证成功，准备初始化数据库!</h3>
	  		 <p>这一步的操作会自动创建数据库资源（表、视图、函数等）。 </p> 
		     <p>创建过程会删除与stone同名的数据库资源，请确保在操作前，您已经<strong>备份了数据库</strong>。</p> 
		     <p>如果您通过
		     	<a href="/install/downloadInstallSql.do">下载sql文件</a>
		     	，已自行连接到数据库执行了sql脚本，那么请跳过这一步，直接<a href="${appPath}/install/saveProperities.do">保存数据库的配置信息。</a></p> 
	  		 <a href="${appPath}/install/InitDatabase.do" type="button" class="btn btn-info  btn-lg" >自动初始化数据库</a>
	  	</div>
	</div>
</div>
	    
</body>
</html>
