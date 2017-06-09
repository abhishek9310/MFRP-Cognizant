<%@page import="com.mms.utils.ServletUtilities"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String baseUrl = ServletUtilities.getBaseUrl(request);
	String errorMsg = "";
	if (request.getAttribute("errorMsg") != null) {
		errorMsg = request.getAttribute("errorMsg").toString();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>System Error</title>
<link type="text/css" rel="stylesheet" href="<%=baseUrl%>/css/login.css">
</head>
<body>
	<div class="homeContainer clearfix">
		<div class="homeHead">
			<div class="logoContainer">
				<span class="logoLeft">MONITER </span><span class="logoRight">MEDICINE</span>

			</div>
		</div>


		<div class="HomeBody">
			<div class="subHomeBody">
				<div class="loginBlock">
					<span class="headText"><%=errorMsg%></span>
					<div class="textBoxContainer">
		
						<input class="button errorPageBtn" type="submit" value="GO TO HOME" onclick="window.location='<%=baseUrl%>/login';">
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>