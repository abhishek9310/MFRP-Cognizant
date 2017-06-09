<%@page import="com.mms.model.MedicineTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	List<MedicineTO> medicineTOs = (List<MedicineTO>) request
			.getAttribute("medicines");
%>
<div id="searchMedicineResult">
	<%
		if (medicineTOs == null || medicineTOs.size() == 0) {
	%>
	<span class="notFound">No Medicine Found, Please try with other
		search</span>
	<%
		} else {
	%>
	<table>
		<thead>
			<tr>
				<th>id</th>
				<th>Medicine Name</th>
				<th>Dosage</th>
				<th>Description</th>
				<th>Medicine in Strip</th>
				<th>Request Medicine</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (MedicineTO medicineTO : medicineTOs) {
			%>
			<tr>
				<td><%=medicineTO.getMedicineId()%></td>
				<td><%=medicineTO.getMedicineName()%></td>
				<td><%=medicineTO.getDosageValue()
							+ medicineTO.getDosageUnit()%></td>
				<td><%=medicineTO.getDescription()%></td>
				<td><%=medicineTO.getMedicinesInStrip()%></td>
				<td><a
					href="requestMedicine?medicineId=<%=medicineTO.getMedicineId()%>">Request</a></td>

			</tr>
			<%
				}
			%>
		</tbody>
	</table>
	<%
		}
	%>
	<div>