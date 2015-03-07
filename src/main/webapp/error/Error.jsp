<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${!empty header['X-Requested-With'] || param.easyuiform == true}">
<jsp:forward page="/error/ErrorJson.jsp"/>
</c:if>
<c:if test="${empty header['X-Requested-With'] && param.easyuiform != true}">
<jsp:forward page="/error/ErrorHtml.jsp"/> 
</c:if>