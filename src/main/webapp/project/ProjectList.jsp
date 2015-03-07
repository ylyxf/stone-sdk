<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>stone-sdk</title>

<%@ include file="/include/include.jsp"%>
<script type="text/javascript" src="${appPath}/project/ProjectList.js"></script>
</head>
<body>
	<div class="container">
		<div class="row" style="margin: 10px;">
			<div class="col-md-12 ">
				<div class=" pull-right">
				<a  class="btn btn-success  " 
					href="${appPath}/project/ProjectAddInit.do">新增</a>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
			<table class="table table-bordered">
				<tr>
					<th width="200px">项目</th>
					<th >操作</th>
				</tr>
				<c:forEach var="project" items="${projectList}" varStatus="status">
				<tr>
					<td>${project.name }</td>
					<td>
					<div class="btn-group" role="group" >
					  <a class="btn btn-default prepareDatabase" projectId="${project.id}" >准备数据库</a>
					  <a class="btn btn-default initProject" projectId="${project.id}" >初始化项目</a>
					  <a class="btn btn-default generateCode" href="${appPath}/code/tableList.do?projectId=${project.id}" >生成业务代码</a>
					  <a class="btn btn-default readProject" href="${appPath}/project/ProjectRead.do?id=${project.id}"  >查看</a>
					  <a class="btn btn-default deleteProject" projectId="${project.id}" >删除</a>
					</div>
					</td>
				</tr>
				</c:forEach>
			</table>
			</div>
			<!-- end  col-md-12  -->
		</div>
		<!-- end  row  -->
	</div>
	<!-- end  container  -->
</body>
</html>
