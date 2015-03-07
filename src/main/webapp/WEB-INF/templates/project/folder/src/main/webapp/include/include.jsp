<%@page contentType="text/html;charset=UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<%
	response.setHeader("Pragma", "No-cache");//HTTP     1.1
	response.setHeader("Cache-Control", "no-cache");//HTTP     1.0
	response.setHeader("Expires", "0");//防止被proxy!
%>
<%
	request.setAttribute("appPath", request.getContextPath());
%>
<script type="text/javascript">
	var appPath = "${appPath}";
</script>
<link rel="stylesheet" type="text/css" href="${appPath}/include/jquery-easyui-1.4.1/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${appPath}/include/jquery-easyui-1.4.1/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${appPath}/include/jquery-easyui-config/easyui-config.css">
<script type="text/javascript"  src="${appPath}/include/jquery-1.11.1.min.js"></script>
<script type="text/javascript"  src="${appPath}/include/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${appPath}/include/jquery-easyui-config/easyui-extension.js"></script>
<script type="text/javascript" src="${appPath}/include/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${appPath}/include/jquery.serializeToObject.js"></script>


<script type="text/javascript">
(function($){
    $.fn.oldReady = $.fn.ready;
    $.fn.ready = function(fn){
        return $.fn.oldReady( function(){ try{ if(fn) fn.apply($,arguments); } catch(e){}} );
    }
})(jQuery);
</script> 

