<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>stone</title>

<%@ include file="/include/include.jsp"%>
<script type="text/javascript"  src="${appPath}/index/Main.js"></script>
</head>
<body>
	<div class="container">
  		<div class="row" style="padding-top: 30px">
		  <div class="col-md-3" >
		  		<i class="fa fa-sitemap fa-5x"></i>
		  		<div>
		  		<a href="#" class="iconNav"  link="${appPath}/group/GroupMain.do">群组管理</a>
		  		</div>
		  </div>
		  <div class="col-md-3">
		  		<i class="fa fa-user fa-5x"></i>
		  		<div>
		  		<a href="#" class="iconNav" link="${appPath}/user/UserMain.do">人员管理</a>
		  		</div>
		  </div>
		  <div class="col-md-3">
		  		<i class="fa fa-list fa-5x"></i>
		  		<div>
		  		<a href="#" class="iconNav" link="${appPath}/dict/DictList.do">字典管理</a>
		  		</div>
		  </div>
		  <div class="col-md-3">
		  		<i class="fa fa-cogs fa-5x"></i>
		  		<div>
		  		<a href="#" class="iconNav" link="${appPath}/config/configClass/List.do">配置管理</a>
		  		</div>
		  </div>
		</div>
		<div class="row" style="padding-top: 30px">
			<div class="col-md-3">
		  		<i class="fa fa-group fa-5x"></i>
		  		<div>
		  		<a href="#" class="iconNav" link="${appPath}/role/RoleList.do">角色管理</a>
		  		</div>
			</div>
			<div class="col-md-3">
		  		<i class="fa fa-group fa-5x"></i>
		  		<div>
		  		<a href="#" class="iconNav" link="${appPath}/develop/Index.do">开发选项</a>
		  		</div>
			</div>
		</div>
	</div>
</body>
</html>
