<%@page contentType="text/html;charset=UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	request.setAttribute("appPath", request.getContextPath());
%>
<script type="text/javascript">
	var appPath = "${appPath}";
</script>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="${appPath}/include/bootstrap-3.3.2/css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet" href="${appPath}/include/bootstrap-3.3.2/css/bootstrap-theme.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${appPath}/include/jquery-1.11.1.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${appPath}/include/bootstrap-3.3.2/js/bootstrap.min.js"></script>

<!-- 表单 http://malsup.com/jquery/form/ -->
<script src="${appPath}/include/jquery.form.min.js"></script>


<!-- 提醒 http://sciactive.com/pnotify -->
<link rel="stylesheet" href="${appPath}/include/pnotify/pnotify.custom.min.css">
<script src="${appPath}/include/pnotify/pnotify.custom.min.js"></script>


<!-- 自定义的一些工具类  -->
<script src="${appPath}/include/jquery.stone.js"></script>

<c:if test="${!empty notify }">
<script>
$(document).ready(function() {
	new PNotify(${notify});
});
</script>
</c:if>