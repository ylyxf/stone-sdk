<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/includeSegment.jsp"%>
<script type="text/javascript" src="${appPath}/group/GroupRead.js"></script>
<form id="groupReadForm" method="post" action="${appPath}/group/groupDelete.do">
	<input type="hidden" id="id" name="id" value="${group.id}" />
	<input type="hidden" name="easyuiform" value="true"/>
	<table class="form-layout">
		<tr>
			<td>编码:</td>
			<td>
				 ${group.code}
			</td>
			<td>名称:</td>
			<td>
				 ${group.name}
			</td>
		</tr>
		<tr>
			<td>类别:</td>
			<td>
				 ${group.type}
			</td>
			<td>序号:</td>
			<td>
				 ${group.sortNo}
			</td>
		</tr>
	</table>
	<div style="text-align:left;padding:15px">
		<a  id="editBtn" class="easyui-linkbutton"  data-options="iconCls:'icon-edit'">编辑</a>
		<c:if test="${group.parentId != 0 }">
		<a  id="deleteBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
		</c:if>
		<a  id="addBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增下级</a>
	</div>
</form>
