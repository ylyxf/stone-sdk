<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>stone-sdk</title>

<%@ include file="/include/include.jsp"%>
<script type="text/javascript" src="${appPath}/project/ProjectEdit.js"></script>
<script type="text/javascript" src="${appPath}/project/ProjectForm.js"></script>
</head>
<body>
	<div class="container">
	 	<form class="form-horizontal" method="post" 
	 		id="projectEditForm" action="${appPath}/project/projectEdit.do">
	 		<input type="hidden" id="id" name="id" value="${project.id}">
	 	<fieldset>
		  <legend>基本信息</legend>
		  <div class="form-group">
		    <label for="name"  class="col-sm-2 control-label">项目名称</label>
		    <div class="col-sm-10">
		    <input type="text" class="form-control" id="name" name="name" placeholder="名称" value="${project.name}" required >
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="projectPath" class="col-sm-2 control-label">项目路径</label>
		    <div class="col-sm-10">
		    <input type="text" class="form-control" id="projectPath" name="projectPath" placeholder="项目路径" value="${project.projectPath}"  required>
		  	</div>
		  </div>
		  <div class="form-group">
		    <label for="packageName" class="col-sm-2 control-label">基本包名</label>
		    <div class="col-sm-10">
		    <input type="text" class="form-control" id="packageName" name="packageName" placeholder="基本包名" value="${project.packageName}"  required>
		 	</div>
		  </div>
		  </fieldset>
		  <fieldset>
		  	<legend>数据库信息</legend>
		  <div class="form-group">
		    <label for="dbType" class="col-sm-2 control-label">数据库类型</label>
		    <div class="col-sm-10">
			    <select id="dbType" name="dbType" class="form-control" value="${project.dbType}"  >
			      <option value="">请选择</option>
				  <option value="Postgresql">Postgresql</option>
				</select>
		  	</div>
		  </div>
		  <div class="form-group">
		    <label for="dbDriver" class="col-sm-2 control-label">数据库驱动</label>
		    <div class="col-sm-10">
		    <input type="text" class="form-control" id="dbDriver" name="dbDriver" placeholder="数据库驱动"  value="${project.dbDriver}"  required>
		  	</div>
		  </div>
		  <div class="form-group">
		    <label for="dbUrl" class="col-sm-2 control-label" >数据库URL</label>
		    <div class="col-sm-10">
		    <input type="text" class="form-control" id="dbUrl" name="dbUrl" placeholder="数据库URL"  value="${project.dbUrl}"  required>
		  	</div>
		  </div>
		  <div class="form-group">
		    <label for="dbUser" class="col-sm-2 control-label">数据库用户名</label>
		    <div class="col-sm-10">
		    <input type="text" class="form-control" id="dbUser" name="dbUser" placeholder="数据库用户名"  value="${project.dbUser}"  required>
		  	</div>
		  </div>
		  <div class="form-group">
		    <label for="dbPassword" class="col-sm-2 control-label">数据库密码</label>
		    <div class="col-sm-10">
		    <input type="text" class="form-control" id="dbPassword" name="dbPassword" placeholder="数据库密码"  value="${project.dbPassword}"  required>
		  	</div>
		  </div>
		  </fieldset>
		  <div class="form-group">
		    <label for="remark" class="col-sm-2 control-label">备注</label>
		    <div class="col-sm-10">
		    <textarea class="form-control" id="remark" name="remark" rows="3"  >${project.remark}</textarea>
		  	</div>
		  </div>
		  <button id="saveBtn"  class="btn btn-default">保存</button>
		  <a href="${appPath}/project/ProjectRead.do?id=${project.id}" class="btn btn-default">取消</a>
		</form>
	</div>
</body>
</html>
