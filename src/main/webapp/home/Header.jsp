<%@page contentType="text/html;charset=UTF-8"%>
<div class="panel-header">
	<button id="homeBegin" class="easyui-linkbutton">开始</button>	 
	<span style="float: right;"><shiro:principal/>[<a  href="${appPath}/logout/logout">退出</a>]</span>
</div>
<div id="beginPanel" class="easyui-dialog beginMenu"  title="所有功能" closed="true" width="500px" height="600px"
	 draggable="false" resizable="false" closable="false" border="false">
	<ul>
		<li>
		<a class="beginLink" href="${appPath}/">主页</a>
		</li>
		<li>
		<a class="beginLink" href="${appPath}/docs/Document.jsp">开发文档</a>
		</li>
		<li>
		<a class="beginLink" href="${appPath}/dict/DictList.do">字典管理</a>
		</li>
		<li>
		<a class="beginLink" href="${appPath}/config/configClass/List.do">配置管理</a>
		</li>
		<li>
		<a class="beginLink" href="${appPath}/group/GroupMain.do">群组管理</a>
		</li>
		<li>
		<a class="beginLink" href="${appPath}/user/UserMain.do">人员管理</a>
		</li>
		<li>
		<a class="beginLink" href="${appPath}/role/RoleList.do">角色管理</a>
		</li>
		<li>
		<a class="beginLink" href="${appPath}/develop/Index.do">代码生成</a>
		</li>
	</ul>
</div>
<link rel="stylesheet" type="text/css" href="${appPath}/home/Header.css">
<script type="text/javascript"  src="${appPath}/home/Header.js"></script>
