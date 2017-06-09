
<%@page import="com.mms.model.MedicineRequestTO"%>
<%@page import="java.util.List"%>
<%
	List<MedicineRequestTO> medicineRequestTOs = (List<MedicineRequestTO>) request
			.getAttribute("requests");
	String requestsType = (String) request.getAttribute("type");
%>
<div id="fetchRequestsResult">
	<%
		if (medicineRequestTOs == null || medicineRequestTOs.size() == 0) {
	%>
	<div class="submitSuccess">

		<span>No Request Available.</span>

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
				<%
					if (requestsType != null && requestsType.equals("Pending")) {
				%>
				<th>Action</th>
				<%
					}
				%>
			</tr>
		</thead>
		<tbody>
			<%
				for (MedicineRequestTO medicineRequestTO : medicineRequestTOs) {
			%>
			<tr>
				<td><%=medicineRequestTO.getMedicineRequestId()%></td>
				<td><%=medicineRequestTO.getMedicineTO().getMedicineId()%></td>
				<td><%=medicineRequestTO.getMedicineTO()
							.getDosageValue()
							+ medicineRequestTO.getMedicineTO().getDosageUnit()%></td>
				<td><%=medicineRequestTO.getNoOfStrips()%></td>
				<td><%=medicineRequestTO.getBranchTO().getBranchName()%></td>
				<td><%=medicineRequestTO.getStatus()%></td>
				<%
					if (requestsType != null && requestsType.equals("Pending")) {
				%>
				<td><a
					href="reviewRequests?reqId=<%=medicineRequestTO.getMedicineRequestId()%>&action=approve">Approve</a>
					/<a
					href="reviewRequests?reqId=<%=medicineRequestTO.getMedicineRequestId()%>&action=reject">Reject</a>
				</td>
				<%
					}
				%>
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