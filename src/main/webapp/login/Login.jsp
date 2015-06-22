<%@page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title></title>
<%@ include file="/include/include.jsp"%>
<style>
	body {
	  padding-top: 40px;
	  padding-bottom: 40px;
	  background-color: #eee;
	}
</style>
</head>
<body>
	<div class="container">
		<form role="form" action="" method="post">
			<h2 class="form-signin-heading">请登录</h2>
			<c:if test="${not empty shiroLoginFailure }">
			<div class="alert alert-danger" role="alert">用户名或密码错误</div>	
			</c:if>
			<input name="username" class="form-control" placeholder="用户名"
				required autofocus /> 
			<input type="password" name="password" class="form-control"
				placeholder="密码" required /><br />
			<button  class="btn btn-lg btn-primary btn-block"  type="submit">登录</button>
		</form>
	</div>
	<!-- /container -->
</body>
</html>
