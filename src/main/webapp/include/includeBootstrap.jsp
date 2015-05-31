<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- 最新 Bootstrap 核心 CSS 文件 -->
<%
	request.setAttribute("appPath", request.getContextPath());
%>
<script type="text/javascript">
	var appPath = "${appPath}";
</script>
<link rel="stylesheet" href="${appPath}/include/bootstrap-3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="${appPath}/include/font-awesome-4.3.0/css/font-awesome.min.css">
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script type="text/javascript"  src="${appPath}/include/jquery-1.11.1.min.js"></script>
<script src="${appPath}/include/bootstrap-3.3.4/js/bootstrap.min.js"></script>
<!-- jquery.form -->
<script src="${appPath}/include/jquery.form.min-3.51.0.js"></script>
<!-- jquery.validate -->
<script src="${appPath}/include/jquery.validate-1.13.1/jquery.validate.min.js"></script>
<script src="${appPath}/include/jquery.validate-1.13.1/additional-methods.min.js"></script>
<script src="${appPath}/include/jquery.validate-1.13.1/localization/messages_zh.min.js"></script>
<!-- bootbox -->
<script src="${appPath}/include/bootbox.min-4.4.0.js"></script>
<!-- pnotify -->
<script src="${appPath}/include/pnotify-2.0.1/pnotify.custom.min.js"></script>
<link href="${appPath}/include/pnotify-2.0.1/pnotify.custom.min.css" media="all" rel="stylesheet" type="text/css" />
<!-- Bootstrap Table -->

<script src="${appPath}/include/bootstrap-table-1.8/bootstrap-table-all.min.js"></script>
<link rel="stylesheet" href="${appPath}/include/bootstrap-table-1.8/bootstrap-table.min.css">

<script src="${appPath}/include/jquery.stone.js"></script>
<script src="${appPath}/include/bootstrap-config/bootstrap-extension.js"></script>
