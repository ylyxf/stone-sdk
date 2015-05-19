<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<%@ include file="/include/include.jsp"%>
<script type="text/javascript" src="${appPath}/role/RoleOperationList.js"></script>
</head>
<body class='default'>
	<div id="tb">
		<a id="back" href="${appPath}/role/RoleList.do" class="easyui-linkbutton" data-options="iconCls:'icon-back',plain:true">返回</a>
	</div>
	<div class="easyui-panel" title="角色“${role.name}”的操作清单" style="width: 100%; height: 100%;">
		<form id="roleOperationForm" method="post">
			<input type="hidden" name="easyuiform" value="true">
			<input type="hidden" id="roleId" name="roleId" value="${role.id}">
			<table id="roleOperationTable" class="table table-bordered">
			<thead>
			<tr>
				<th  data-options="field:'module'" width="30%">
					模块
				</th>
				<th  data-options="field:'operationCode'" width="70%">
					操作
				</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${permissionClassEntityList}" var="permissionClassEntity">
				<tr>
					<td style="vertical-align: middle;">
						${permissionClassEntity.name}
					</td>
					<td>
						<c:forEach items="${permissionClassEntity.permissionEntityList}" var="permissionEntity">
						<div>
							<input type="checkbox" id="${permissionEntity.value }" value="${permissionEntity.value }"
								<c:if test="${permissionEntity.enabled }">
					 				checked="true"
					 			</c:if>
							class="operationCheckbox">
							<label for="${permissionEntity.value }">${permissionEntity.name} 	
							</label>
						</div>
						</c:forEach>
					</td>
				</tr>
			</c:forEach>
			</tbody>
			</table> 
		</form>
	</div>
</body>
</html>
