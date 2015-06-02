<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${databaseInstallService.installed != 'true'}">
<jsp:forward page="/install/InstallMain.jsp"></jsp:forward>
</c:if>
<c:redirect url="${request.contextPath}/index/Index.do"/>