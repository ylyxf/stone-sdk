<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>stone-sdk</title>

<%@ include file="/include/include.jsp"%>
<script type="text/javascript" src="${appPath}/code/TableList.js"></script>
</head>
<body>
	<div class="container">
		<div class="row" style="margin: 10px;">
			<div class="col-md-12">
				<a  class="btn btn-success  pull-right" 
					href="${appPath}/project/ProjectList.do">返回</a>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
			<table class="table table-bordered">
				<tr>
					<th width="30%">表名</th>
					<th width="70%">注释</th>
				</tr>
				<c:forEach var="table" items="${tableList}" varStatus="status">
				<tr>
					<td><a class="" href="${appPath}/code/tableConfig.do?projectId=${table.projectId}&tableName=${table.name}"  >${table.name  }</a></td>
					<td>${table.comment }</td>
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
