<%@page import="com.mms.utils.ServletUtilities"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Branch Admin Home</title>
<%@include file="BranchAdminHead.jsp" %>
</head>
<body>
	<div>
	<jsp:include page="Menu.jsp"></jsp:include>
		<div class="rightCol">
			<div class="head">
				<span class="headText">Branch Admin Home</span>
			</div>
			<div class="MainContent">
				<div class="homeContainer">
					<a href="registerStock"> <span class="homeItem item5">
							Register Stock</span> </a><a href="stockDetails"><span class="homeItem item6">All Stock Details</span></a> <a href="requestMedicine"><span
						class="homeItem item7">Request Medicine</span> 
					</a> <a href="cancelRequest"><span class="homeItem item8">Cancel
							Request</span> </a> <a href="allRequests"><span class="homeItem item9">All Request</span>
							
					</a>
					
				</div>
			</div>

		</div>
	</div>
</body>
</html>