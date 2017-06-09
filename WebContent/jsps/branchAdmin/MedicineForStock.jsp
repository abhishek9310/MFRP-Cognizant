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
	<span class="notFound">No Medicine Found</span>
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
				<th>Stock Register</th>
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
					href="registerStock?medicineId=<%=medicineTO.getMedicineId()%>">Stock Register</a></td>

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