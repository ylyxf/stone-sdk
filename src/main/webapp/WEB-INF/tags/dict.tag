<%@ tag language="java" pageEncoding="utf-8"%>
<%@ tag dynamic-attributes="userAttributes"%>
<%@ attribute name="value" type="java.lang.String" required="true"  %>
<%@ attribute name="type" type="java.lang.String" required="true" %>
<%@ attribute name="code" type="java.lang.String" required="true" %>
<%@ attribute name="filterStr" type="java.lang.String" %>
<%@ attribute name="emptyOption" type="java.lang.Boolean" %>
<%@ attribute name="emptyLabel" type="java.lang.String" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions' %>
<%@tag import="org.siqisource.stone.dict.service.DictItemService"%>

<%
	DictItemService dictItemService = (DictItemService)application.getAttribute("dictItemService");
   	request.setAttribute("dictItemList" , dictItemService.getDictItemList(code,filterStr)) ;  
%>
<c:choose>
	<%--下拉框 --%>
    <c:when test="${type=='select'}">
    	<select  <c:forEach items="${userAttributes}" var="userAttribute" > ${userAttribute.key}="${userAttribute.value}" </c:forEach>>
    		<%-- 是否选中默认值 --%>
    		<c:set var="emptySelected" value="selected" />
    		<%-- 循环出数据库的字典内容 --%>
    		<c:forEach var="dictItem" items="${dictItemList}">
    			<option value="${dictItem.value }"  <c:if test="${value==dictItem.value }"> selected<c:set var="emptySelected" value="" /></c:if> >${dictItem.label }</option>
    		</c:forEach>
    		<%-- 是否显示默认值 --%>
    		<c:if test="${emptyOption}">
    			<option value="" ${emptySelected}>${emptyLabel}</option>
    		</c:if>
    	</select>
	</c:when> 
	<%-- 单选框 --%>
    <c:when test="${type=='radio'}">
	</c:when>
	<%-- 复选框 --%>
    <c:when test="${type=='checkbox'}">
	</c:when>
	<%-- 文本 --%>
    <c:when test="${type=='text'}">
    	<c:forEach var="dictItem" items="${dictItemList}">
    		 <c:if test="${value==dictItem.value }">  ${dictItem.label }</c:if>
    	</c:forEach>
	</c:when>
	<%-- json --%>
    <c:when test="${type=='json'}">
	</c:when>  
    <c:otherwise>不支持type为${type}类型的操作</c:otherwise>  
</c:choose> 