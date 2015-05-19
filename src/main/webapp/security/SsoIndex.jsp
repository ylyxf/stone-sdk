<%@page isELIgnored="false"%>
<%@ include file="/include/include.jsp"%>
<c:if test="${ not empty param.distUrl}">
<script>
window.location.href='${appPath}/${param.distUrl}';
</script>
</c:if>
<c:if test="${empty param.distUrl}">
<script>
window.location.href='${appPath}';
</script>
</c:if>