<%@page contentType="text/html;charset=UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<%
	response.setHeader("Pragma", "No-cache");//HTTP     1.1
	response.setHeader("Cache-Control", "no-cache");//HTTP     1.0
	response.setHeader("Expires", "0");//防止被proxy!
%>
<%
	request.setAttribute("appPath", request.getContextPath());
%>

