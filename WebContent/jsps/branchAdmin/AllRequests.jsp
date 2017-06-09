<%@page import="com.mms.model.MedicineRequestTO"%>
<%@page import="java.util.List"%>
<%@page import="com.mms.utils.ServletUtilities"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	List<MedicineRequestTO> medicineRequestTOs = (List<MedicineRequestTO>) request
			.getAttribute("requests");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Branch Admin Home</title>
<%@include file="BranchAdminHead.jsp"%>
</head>
<body>
	<div>
		<jsp:include page="Menu.jsp"></jsp:include>
		<div class="rightCol">
			<div class="head">
				<span class="headText">All Requests</span>
			</div>
			<div class="MainContent">
				<table>
					<thead>
						<tr>
							<th>Request Id</th>
							<th>Medicine</th>
							<th>Dosage</th>
							<th>No Of medicine</th>
							<th>Branch</th>
							<th>Status</th>
							
						</tr>
					</thead>
					<tbody>
						<%
							for (MedicineRequestTO medicineRequestTO : medicineRequestTOs) {
						%>
						<tr>
							<td><%=medicineRequestTO.getMedicineRequestId()%></td>
							<td><%=medicineRequestTO.getMedicineTO().getMedicineName()%></td>
							<td><%=medicineRequestTO.getMedicineTO().getDosageValue()
						+ medicineRequestTO.getMedicineTO().getDosageUnit()%></td>
							<td><%=medicineRequestTO.getNoOfStrips()%></td>
							<td><%=medicineRequestTO.getBranchTO().getBranchName()%></td>
							<td><%=medicineRequestTO.getStatus()%></td>
						</tr>

						<%
							}
						%>
					</tbody>
				</table>
			</div>

		</div>
	</div>
</body>
</html>