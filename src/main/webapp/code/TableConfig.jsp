<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>stone-sdk</title>

<%@ include file="/include/include.jsp"%>
<script type="text/javascript" src="${appPath}/code/TableConfig.js"></script>
</head>
<body>
	<div class="container">
		<form class="form-horizontal" method="post" 
	 		id="tableConfigForm" action="${appPath}/code/ViewCode.do" target="_blank">
	 		<input type="hidden" name="projectId" value="${table.projectId }">
	 	<fieldset>
		  <legend>基本信息</legend>
		  <div class="form-group">
		    <label for="name" class="col-sm-2 control-label">表名</label>
		    <div class="col-sm-10">
		    <input type="text" class="form-control" id="name" name="name"  value="${table.name}" readonly>
		  	</div>
		  </div>
		  <div class="form-group">
		    <label for="label" class="col-sm-2 control-label">表标题</label>
		    <div class="col-sm-10">
		    <input type="text" class="form-control" id="label" name="label" placeholder="表标题"   value="${table.label}"  required>
		  	</div>
		  </div>
		  <div class="form-group">
		    <label for="modulePackageName"  class="col-sm-2 control-label">模块包名</label>
		    <div class="col-sm-10">
		    <input type="text" class="form-control" id="modulePackageName" name="modulePackageName"  placeholder="模块包名"   value="${table.modulePackageName}"  required>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="entity" class="col-sm-2 control-label">实体名</label>
		    <div class="col-sm-10">
		    <input type="text" class="form-control" id="entity" name="entity" placeholder="实体名"   value="${table.entity}" required>
		  	</div>
		  </div>
		  <div class="form-group">
		  	<div class="col-sm-offset-2 col-sm-10">
		    <div class="checkbox">
			  <label>
			    <input type="checkbox" name="autoGenerateKey" value="true" 
			     <c:if test="${table.autoGenerateKey}">
			     checked="checked"
			     </c:if>
			    >
			          是否自增主键
			  </label>
			</div>
			</div>
		  </div>
		  <div class="form-group">
		  	<div class="col-sm-offset-2 col-sm-10">
		    <div class="checkbox">
			  <label>
			    <input type="checkbox" name="treeCode">
			          生成树代码
			  </label>
			</div>
			</div>
		  </div>
		  </fieldset>
		  <fieldset>
		  <legend>列信息</legend>
		  	<div class="row">
			<div class="col-md-12">
			<table class="table table-bordered">
				<tr>
					<th width="5%"> </th>
					<th width="25%">列名</th>
					<th width="70%">注释</th>
				</tr>
				<c:forEach var="column" items="${table.columns}" varStatus="status">
				<tr>
					<td align="center">  
						<c:if test="${column.primaryKey }">
						<span class="glyphicon glyphicon-flag" aria-hidden="true"></span>
						</c:if>
					</td>
					<td>${column.name } </td>
					<td>${column.comment }</td>
				</tr>
				</c:forEach>
			</table>
			</div>
			</div>
		  </fieldset>
		  <button type="button"  id="saveBtn" type="submit" class="btn btn-primary">保存代码</button>
		  <button type="submit"  id="viewBtn"  class="btn btn-default" >查看代码</button>
		  <a  class="btn btn-default"  href="${appPath}/code/tableList.do?projectId=${table.projectId}">返回</a>
		</form>
		<!-- end  row  -->
	</div>
	<!-- end  container  -->
</body>
</html>
