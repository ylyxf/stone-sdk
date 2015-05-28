<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html;charset=UTF-8"%>
<%
	request.setAttribute("appPath", request.getContextPath());
%>
<title>教育管理系统-eduadmin</title>
<script type="text/javascript">
	var appPath = "${appPath}";
</script>
<!-- esayui的css -->
<link rel="stylesheet" type="text/css" href="${appPath}/include/jquery-easyui-1.4.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${appPath}/include/jquery-easyui-1.4.2/themes/icon.css">
<!-- 基于esayui定制的css -->
<link rel="stylesheet" href="${appPath}/include/font-awesome-4.3.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${appPath}/include/jquery-easyui-config/easyui-config.css">
<link rel="stylesheet" type="text/css" href="${appPath}/include/jquery-easyui-config/themes/icon.css">
<!--  esayui的js -->
<script type="text/javascript"  src="${appPath}/include/jquery-easyui-1.4.2/jquery.min.js"></script>
<script type="text/javascript"  src="${appPath}/include/jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${appPath}/include/jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
<!-- 基于esayui定制的js,一些基本约定 -->
<script type="text/javascript" src="${appPath}/include/jquery-easyui-config/easyui-config.js"></script>
<!-- 基于esayui的扩展 -->
<script type="text/javascript" src="${appPath}/include/jquery-easyui-config/easyui-extension.js"></script>

<script type="text/javascript" src="${appPath}/include/jquery.stone.js"></script>

<script type="text/javascript">
(function($){
    $.fn.oldReady = $.fn.ready;
    $.fn.ready = function(fn){
        return $.fn.oldReady( function(){ try{ if(fn) fn.apply($,arguments); } catch(e){}} );
    }
})(jQuery);
</script> 