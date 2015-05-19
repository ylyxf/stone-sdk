<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/includeSegment.jsp"%>
<script type="text/javascript" src="${appPath}/user/UserAdd.js"></script>
<form id="userForm" method="post" action="${appPath}/user/userAdd.do" >
	<input type="hidden" name="logicDeleted" value="false">
	<input type="hidden" name="easyuiform" value="true">
	<input type="hidden" name="defaultGroupId" value="${defaultGroupId }">
	<input type="hidden" name="sortNo" value="0">
	<table  class="form-layout">
		<tr>
			<td>账户</td>
			<td>
				<input type="text" name="account" class="easyui-textbox"  data-options="required:true" >
				<input type="checkbox" id="enabled" name="enabled"  style="vertical-align:middle;" />
				<label for="enabled"  style="vertical-align:middle;">是否可用</label>
			</td>
		</tr>
		<tr>
			<td>密码</td>
			<td><input type="text" name="password" class="easyui-textbox" data-options="required:true"  ></td>
		</tr>
		<tr>
			<td>姓名</td>
			<td><input type="text" name="name" class="easyui-textbox" data-options="required:true" ></td>
		</tr>
		<tr>
			<td>电话</td>
			<td><input type="text" name="phone" class="easyui-textbox" data-options="required:true" ></td>
		</tr>
		<tr>
			<td>邮箱</td>
			<td><input type="text" name="email" class="easyui-textbox" data-options="required:true,validType:'email'" ></td>
		</tr>
	</table>
	<div style="text-align:left;padding:15px">
		<a href="#" id="saveBtn" class="easyui-linkbutton">保存</a>
		<a href="#" id="cancelBtn" class="easyui-linkbutton">取消</a>
	</div>
</form>
