<%@page import="com.mms.model.BranchStockTO"%>
<%@page import="com.mms.model.MedicineRequestTO"%>
<%@page import="java.util.List"%>
<%@page import="com.mms.utils.ServletUtilities"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	List<BranchStockTO> branchStockTOs = (List<BranchStockTO>) request
			.getAttribute("StockDetails");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Stock Details</title>
<%@include file="BranchAdminHead.jsp"%>
</head>
<body>
	<div>
		<jsp:include page="Menu.jsp"></jsp:include>
		<div class="rightCol">
			<div class="head">
				<span class="headText">Stock Details</span>
			</div>
			<div class="MainContent">


				<%
					if (branchStockTOs == null || branchStockTOs.size() == 0) {
				%>
				<div class="submitSuccess">

					<span>There is nothing in Stock details</span>

					<div class="textBoxContainer">

						<input class="button" type="submit" value="Register Stock"
							onclick="window.location='registerStock';">
					</div>
				</div>

				<%
					} else {
				%>
				<table>
					<thead>
						<tr>
							<th>Stock Id</th>
							<th>Medicine Name</th>
							<th>Dosage</th>
							<th>Branch</th>
							<th>Strips</th>
							<th>Date</th>

						</tr>
					</thead>
					<tbody>
						<%
							for (BranchStockTO branchStockTO : branchStockTOs) {
						%>
						<tr>
							<td><%=branchStockTO.getStockId()%></td>
							<td><%=branchStockTO.getMedicineTO().getMedicineName()%></td>
							<td><%=branchStockTO.getMedicineTO().getDosageValue()
							+ branchStockTO.getMedicineTO().getDosageUnit()%></td>
							<td><%=branchStockTO.getNumberOfStrips()%></td>
							<td><%=branchStockTO.getBranchTO().getBranchName()%></td>
							<td><%=branchStockTO.getRegistrationDate().substring(0,
							11)%></td>
						</tr>

						<%
							}
						%>
					</tbody>
				</table>
				<%
					}
				%>
			</div>

		</div>
	</div>
</body>
</html>