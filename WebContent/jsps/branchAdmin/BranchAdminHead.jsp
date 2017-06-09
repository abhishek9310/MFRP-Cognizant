<%@page import="com.mms.utils.ServletUtilities"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String baseUrl = ServletUtilities.getBaseUrl(request);
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="<%=baseUrl%>/css/style.css">
<script src="<%=baseUrl%>/js/jquery-2.1.4.min.js"></script>
<script src="<%=baseUrl%>/js/script.js"></script>
<script src="<%=baseUrl%>/js/validator.js"></script>