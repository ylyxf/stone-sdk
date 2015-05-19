<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/includeSegment.jsp"%>
<script type="text/javascript" src="${appPath}/group/GroupEdit.js"></script>
<form id="groupEditForm" method="post" action="${appPath}/group/groupEdit.do">
	<input type="hidden" id="id" name="id" value="${group.id}" />
	<input type="hidden" id="parentId" name="parentId" value="${group.parentId}" />
	<input type="hidden" id="isLeaf" name="isLeaf" value="${group.isLeaf}" />
	<input type="hidden" id="logicDeleted" name="logicDeleted" value="${group.logicDeleted}" />
	<input type="hidden" name="easyuiform" value="true"/>
	<table class="form-layout">
		<tr>
			<td>编码:</td>
			<td>
				<input class="easyui-textbox" type="text" name="code" data-options="required:true" value="${group.code}"/>
			</td>
			<td>名称:</td>
			<td>
				<input class="easyui-textbox" type="text" name="name" data-options="required:true" value="${group.name}"/>
			</td>
		</tr>
		<tr>
			<td>类别:</td>
			<td>
				<input class="easyui-numberbox" type="text" name="type" data-options="required:true" value="${group.type}"/>
			</td>
			<td>序号:</td>
			<td>
				<input class="easyui-numberbox" type="text" name="sortNo" data-options="required:true" value="${group.sortNo}"/>
			</td>
		</tr>
	</table>
	<div style="text-align:left;padding:15px">
		<a  id="saveBtn" class="easyui-linkbutton"  data-options="iconCls:'icon-ok'"  type="button">保存</a>
		<a  id="cancelBtn" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" type="button" >取消</a>
	</div>
</form>
