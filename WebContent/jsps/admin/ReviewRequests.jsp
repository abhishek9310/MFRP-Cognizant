<%@page import="com.mms.model.MedicineRequestTO"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	List<MedicineRequestTO> medicineRequestTOs = (List<MedicineRequestTO>) request
			.getAttribute("requests");
	String successMsg = (String) request.getAttribute("success");
	String error = (String) request.getAttribute("errorMsg");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Cancel Request</title>
<%@include file="AdminHead.jsp"%>
</head>
<body>
	<div>
		<%@include file="Menu.jsp"%>
		<div class="rightCol">
			<div class="head">
				<span class="headText">Cancel Request</span>
			</div>
			<div class="MainContent">


				<%
					if (error != null) {
				%>
				<div class="submitSuccess">

					<span><%=error%></span>

					<div class="textBoxContainer">

						<input class="button" type="submit" value="Go to Review Requests"
							onclick="window.location='<%=baseUrl%>/admin/reviewRequests';">
					</div>
				</div>

				<%
					} else if (successMsg != null && successMsg.equals("Rejected")) {
				%>
				<div class="submitSuccess">

					<span>Medicine Request Rejected Successfully</span>

					<div class="textBoxContainer">

						<input class="button" type="submit" value="Review More Requests"
							onclick="window.location='<%=baseUrl%>/admin/reviewRequests';">
					</div>
				</div>

				<%
					} else if (successMsg != null && successMsg.equals("Approved")) {
				%>
				<div class="submitSuccess">

					<span>Medicine Request Approved Successfully</span>

					<div class="textBoxContainer">

						<input class="button" type="submit" value="Review More Requests"
							onclick="window.location='<%=baseUrl%>/admin/reviewRequests';">
					</div>
				</div>

				<%
					} else {
				%>
				<div class="searchMedicine">
					Select Request Type<select class="requestBy">
						<option value="Pending">Pending</option>
						<option value="Rejected">Rejected</option>
						<option value="Approved">Approved</option>

					</select>
					<input id="fethRequestUrl" type="hidden"
						value="<%=baseUrl%>/admin/FetchRequests">
				</div>
				<jsp:include page="MedicineRequests.jsp"></jsp:include>

				<%
					}
				%>
			</div>

		</div>
	</div>
</body>
</html>