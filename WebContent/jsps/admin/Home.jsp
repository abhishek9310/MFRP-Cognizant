<%@page import="com.mms.utils.ServletUtilities"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Admin Home</title>
<%@include file="AdminHead.jsp" %>
</head>
<body>
	<div>
		<%@include file="Menu.jsp"%>
		<div class="rightCol">
			<div class="head">
				<span class="headText">ADMIN HOME</span>
			</div>
			<div class="MainContent">
				<div class="homeContainer">
					<a href="registerBranchAdmin"> <span class="homeItem item1">
							Register Branch Admin</span> </a> <a href="registerMedicine"><span
						class="homeItem item2">Register Medicine</span> </a> <a
						href="reviewRequest"><span class="homeItem item3">Reivew
							Request</span> </a> <a href="logout"><span class="homeItem item4">Logout</span>
					</a>
				</div>
			</div>

		</div>
	</div>
</body>
</html>