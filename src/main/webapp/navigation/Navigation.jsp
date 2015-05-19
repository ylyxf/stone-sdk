<%@page contentType="text/html;charset=UTF-8"%>
<div id="indexNavBar" style="margin: 10px;">
	<span style="float: right;margin-right:20px">
	<shiro:principal/>
	<a href="${appPath}/logout/logout">[退出]</a>
	</span>
</div>
