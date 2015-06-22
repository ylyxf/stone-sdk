<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>stone</title>

<%@ include file="/include/include.jsp"%>
<script type="text/javascript"  src="${appPath}/install/ToSetAdminPassword.js"></script>
</head>
<body>
<div class="container">
	<div class="row" style="padding-top: 30px">
		<div class="col-md-12" >
	  		<form id="dbInfoForm" name="dbInfoForm" class="form-horizontal" method="post"
	  			action="${appPath}/install/SetAdminPassword.do">
	  		  <input type="hidden" name="dataSource" value="containerDataSource"/>
	  		  <div class="form-group">
			    <label for="dbType" class="col-sm-2 control-label">管理员密码</label>
			    <div class="col-sm-10">
			    <input type="text" class="form-control" id="password" name="password" value="" required
			     placeholder="请设置管理员密码">
			  	</div>
			  </div>
			  <div class="col-sm-12">
			  	<p class="bg-danger">${errmsg }</p>
			  </div>
			  <div class="col-sm-2"></div>
			  <div class="col-sm-10">
			  	<button id="checkBtn" type="submit" class="btn btn-default">确定</button>
			  </div>
			</form>
	  	</div>
	</div>  
</div>
	    
</body>
</html>
