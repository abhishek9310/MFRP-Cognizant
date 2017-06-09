<%@page import="com.mms.model.MedicineRequestTO"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	List<MedicineRequestTO> medicineRequestTOs = (List<MedicineRequestTO>) request
			.getAttribute("requests");
	String success = (String) request.getAttribute("success");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Cancel Request</title>
<%@include file="BranchAdminHead.jsp"%>
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
					if (success != null) {
				%>
				<div class="submitSuccess">

					<span>Medicine Request Canceled Successfully</span>

					<div class="textBoxContainer">

						<input class="button" type="submit" value="Pending Requests"
							onclick="window.location='cancelRequest';">
					</div>
				</div>

				<%
					} else {

						if (medicineRequestTOs == null || medicineRequestTOs.size() == 0) {
				%>
				<div class="submitSuccess">

					<span>There is no any pending request.</span>

					<div class="textBoxContainer">

						<input class="button" type="submit" value="Request Medicine"
							onclick="window.location='requestMedicine';">
					</div>
				</div>

				<%
					} else {
				%>
				<table>
					<thead>
						<tr>
							<th>Request Id</th>
							<th>Medicine Id</th>
							<th>Dosage</th>
							<th>No Of medicine</th>
							<th>Branch</th>
							<th>Status</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<%
							for (MedicineRequestTO medicineRequestTO : medicineRequestTOs) {
						%>
						<tr>
							<td><%=medicineRequestTO.getMedicineRequestId()%></td>
							<td><%=medicineRequestTO.getMedicineTO()
								.getMedicineId()%></td>
							<td><%=medicineRequestTO.getMedicineTO()
								.getDosageValue()
								+ medicineRequestTO.getMedicineTO()
										.getDosageUnit()%></td>
							<td><%=medicineRequestTO.getNoOfStrips()%></td>
							<td><%=medicineRequestTO.getBranchTO()
								.getBranchName()%></td>
							<td><%=medicineRequestTO.getStatus()%></td>
							<td><a
								href="cancelRequest?reqId=<%=medicineRequestTO.getMedicineRequestId()%>">cancel
									request</a></td>
						</tr>

						<%
							}
						%>
					</tbody>
				</table>
				<%
					}
					}
				%>
			</div>

		</div>
	</div>
</body>
</html>