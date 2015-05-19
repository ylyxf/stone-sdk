<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/includeSegment.jsp"%>
<script type="text/javascript" src="${appPath}/group/GroupAdd.js"></script>
<form id="groupAddForm" method="post" action="${appPath}/group/groupAdd.do">
	<input type="hidden" id="parentId" name="parentId" value="${group.id}" />
	<input type="hidden" id="logicDeleted" name="logicDeleted" value="false" /> 
	<input type="hidden" id="isLeaf" name="isLeaf" value="true" />
	<input type="hidden" name="easyuiform" value="true"/>
	<table class="form-layout">
		<tr>
			<td>父部门:</td>
			<td colspan="3">
				${group.name}
			</td>
		</tr>
		<tr>
			<td>编码:</td>
			<td>
				<input class="easyui-textbox" type="text" name="code" data-options="required:true"  />
			</td>
			<td>名称:</td>
			<td>
				<input class="easyui-textbox" type="text" name="name" data-options="required:true"  />
			</td>
		</tr>
		<tr>
			<td>类别:</td>
			<td>
				<input class="easyui-textbox" type="text" name="type" data-options="required:true"  />
			</td>
			<td>序号:</td>
			<td>
				<input class="easyui-numberbox" type="text" name="sortNo" data-options="required:true" />
			</td>
		</tr>
	</table>
	<div style="text-align:left;padding:15px">
		<a  id="saveBtn" class="easyui-linkbutton"  data-options="iconCls:'icon-ok'"  type="button">保存</a>
		<a  id="cancelBtn" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" type="button" >取消</a>
	</div>
</form>
