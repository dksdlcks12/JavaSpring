<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setDateHeader("Expires", 0L); // Do not cache in proxy server
%>
<html>
<head>
<title>스프링</title>
<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
</head>
<body>
    <tiles:insertAttribute name="header"/>        
	<tiles:insertAttribute name="body" />                                                 
	<tiles:insertAttribute name="footer" />
</body>
</html>