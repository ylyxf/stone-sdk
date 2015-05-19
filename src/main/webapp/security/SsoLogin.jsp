<%@page isELIgnored="false"%>
<iframe style="display: none"
	onload="window.location.href='${subServer}/security/SsoIndex.jsp?ticket=${ticket}&distUrl=${param.distUrl}'"
	src="${subServer}/logout/logout"> </iframe>
