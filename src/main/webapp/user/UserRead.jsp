<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/includeSegment.jsp"%>
<script type="text/javascript" src="${appPath}/user/UserRead.js"></script>
<form id="userForm" method="post" action="${appPath}/user/userEdit.do" >
	<input type="hidden" id="id" name="id" value="${user.id }">
	<input type="hidden" name="logicDeleted" value="${user.logicDeleted }">
	<input type="hidden" name="easyuiform" value="true">
	<table  class="form-layout">
		<tr>
			<td>账户</td>
			<td>
				 ${user.account} <c:if test="${user.enabled }">可用 </c:if>
			</td>
		</tr>
		<tr>
			<td>密码</td>
			<td>*</td>
		</tr>
		<tr>
			<td>姓名</td>
			<td>${user.name}</td>
		</tr>
		<tr>
			<td>电话</td>
			<td>${user.phone}</td>
		</tr>
		<tr>
			<td>邮箱</td>
			<td>${user.email}</td>
		</tr>
		<tr>
			<td>部门</td>
			<td> 
				<c:forEach var="groupUser" items="${user.groupUserList}" >     
				 		${groupUser.groupName}
				 		<c:if test="${groupUser.isDefault }">（默认部门）</c:if>
				</c:forEach> 
			</td>
		</tr>
	</table>
	<div style="text-align:left;padding:15px">
		<a href="#" id="deleteBtn" class="easyui-linkbutton">删除</a>
		<a href="#" id="removeBtn" class="easyui-linkbutton">移除</a>
		<a href="#" id="editBtn" class="easyui-linkbutton">编辑</a>
		<a href="#" id="referenceBtn" class="easyui-linkbutton">加入其它群组</a>
		<a href="#" id="cancelBtn" class="easyui-linkbutton">返回</a>
	</div>
</form>
