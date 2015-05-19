<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/includeSegment.jsp"%>
<script type="text/javascript" src="${appPath}/user/UserEdit.js"></script>
<form id="userForm" method="post" action="${appPath}/user/userEdit.do" >
	<input type="hidden" id="id" name="id" value="${user.id }">
	<input type="hidden" name="easyuiform" value="true">
	<input type="hidden" name="logicDeleted" value="${user.logicDeleted }">
	<table  class="form-layout">
		<tr>
			<td>账户</td>
			<td>
				<input type="text" name="account" class="easyui-textbox" value="${user.account}"  data-options="required:true" >
				<input type="checkbox" id="enabled" name="enabled"  style="vertical-align:middle;"
				 		<c:if test="${user.enabled }">
				 			 checked="true"
				 		</c:if>
				 />
				 <label for="enabled"  style="vertical-align:middle;">是否可用</label>
			</td>
		</tr>
		<tr>
			<td>密码</td>
			<td><input type="text" name="password" class="easyui-textbox" value="${user.password}" data-options="required:true" ></td>
		</tr>
		<tr>
			<td>姓名</td>
			<td><input type="text" name="name" class="easyui-textbox" value="${user.name}" data-options="required:true" ></td>
		</tr>
		<tr>
			<td>电话</td>
			<td><input type="text" name="phone" class="easyui-textbox" value="${user.phone}" data-options="required:true" ></td>
		</tr>
		<tr>
			<td>邮箱</td>
			<td><input type="text" name="email" class="easyui-textbox"   data-options="required:true,validType:'email'" value="${user.email}"></td>
		</tr>
		<tr>
			<td>默认部门</td>
			<td> 
				<c:forEach var="groupUser" items="${user.groupUserList}" >     
				 	<input type="radio" id="groupoId_${groupUser.groupId}"   style="vertical-align:middle;" 
				 		<c:if test="${groupUser.isDefault }">
				 			 checked="true"
				 		</c:if>
				 		name="defaultGroupId" value="${groupUser.groupId}" />
				 	<label for="groupoId_${groupUser.groupId}"  style="vertical-align:middle;">${groupUser.groupName}</label><br/>
				</c:forEach> 
			</td>
		</tr>
	</table>
	<div style="text-align:left;padding:15px">
		<a href="#" id="saveBtn" class="easyui-linkbutton">保存</a>
		<a href="#" id="cancelBtn" class="easyui-linkbutton">取消</a>
	</div>
</form>
