<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.mms.model.MedicineTO"%>
<%
	MedicineTO medicineTo = new MedicineTO();

	if (request.getAttribute("medicineTO") != null) {
		medicineTo = (MedicineTO) request.getAttribute("medicineTO");
	}

	HashMap<String, String> errorMap = new HashMap<String, String>();

	errorMap.put("medicineName", "");
	errorMap.put("dosageValue", "");
	errorMap.put("dosageUnit", "");
/* 	errorMap.put("numberOfStrip", ""); */
	errorMap.put("medicinesInStrip", "");
	errorMap.put("manufactureDate", "");
	errorMap.put("expiryDate", "");
	errorMap.put("priceOfStrip", "");
	errorMap.put("Description", "");

	if (request.getAttribute("errorMap") != null) {
		errorMap = (HashMap<String, String>) request
				.getAttribute("errorMap");
	}
	String success = (String) request.getAttribute("success");
%>
<html>
<head>
<title>Register Medicine</title>
<%@include file="AdminHead.jsp"%>
</head>
<body>
	<div>
		<%@include file="Menu.jsp"%>
		<div class="rightCol">
			<div class="head">
				<span class="headText">ADMIN HOME</span>
			</div>
			<div class="MainContent">
				<%
					if (success != null) {
				%>
				<div class="submitSuccess">

					<span>Medicine Registration successful </span> <br>

					<div class="textBoxContainer">

						<input class="button" type="submit"
							value="Register Another Medicine"
							onclick="window.location='<%=baseUrl%>/admin/registerMedicine';">
					</div>
				</div>

				<%
					} else {
				%>
				<form method="post" name="medicineRegistration" onsubmit="return validateMedicine()">

					<ul class="formStyle">
						<li class="wide clearfix">
							<div class="field medicineName">
								<label>Medicine Name<span class="required">*</span> </label>
								<div>
									<input type="text" name="medicineName"
										placeholder="Medicine Name"
										value=<%=medicineTo.getMedicineName()%>>
								</div>
								<div class="error"><%=errorMap.get("medicineName")%></div>
							</div>
						</li>
						<li class="wide clearfix">
							<div class="field dosageValue">
								<label>Dosage Value<span class="required">*</span> </label>
								<div>
									<input type="text" name="dosageValue"
										placeholder="Dosage Value"
										value=<%=medicineTo.getDosageValue()%>>
								</div>
								<div class="error"><%=errorMap.get("dosageValue")%></div>
							</div>
						</li>
						<li class="wide clearfix">
							<div class="field dosageUnit">
								<label>Dosage Unit<span class="required">*</span> </label>
								<div>
									<input type="text" name="dosageUnit"
										placeholder="Dosage Unit etc. mg,ml"
										value=<%=medicineTo.getDosageUnit()%>>
								</div>
								<div class="error"><%=errorMap.get("dosageUnit")%></div>
							</div></li>
						<li class="wide clearfix">
							<div class="field stripPrice">
								<label>Price<span class="required">*</span> </label>
								<div>
									<input type="text" name="stripPrice" placeholder="Price"
										value=<%=medicineTo.getPriceOfStrip()%>>
								</div>
								<div class="error"><%=errorMap.get("priceOfStrip")%></div>
							</div></li>

						<li class="wide clearfix">
							<div class="field medicineInStrip">
								<label>Medicine in Strip<span class="required">*</span>
								</label>
								<div>
									<input type="text" name="medicineInStrip"
										placeholder="Medicine in Strip"
										value=<%=medicineTo.getMedicinesInStrip()%>>
								</div>
								<div class="error"><%=errorMap.get("medicinesInStrip")%></div>
							</div></li>
						<li class="nonWide clearfix">
							<div class="field manufactureDate">
								<label>Manufacturing Date<span class="required">*</span>
								</label>
								<div>
									<input type="date" name="manufactureDate"
										placeholder="YYYY-MM-DD"
										value=<%=medicineTo.getManufactureDate()%>>
								</div>
								<div class="error"><%=errorMap.get("manufactureDate")%></div>
							</div>
							<div class="field expiryDate">
								<label>Expiry Date<span class="required">*</span> </label>
								<div>
									<input type="date" name="expiryDate" placeholder="YYYY-MM-DD"
										value=<%=medicineTo.getExpiryDate()%>>
								</div>
								<div class="error"><%=errorMap.get("expiryDate")%></div>
							</div></li>
						<li class="wide clearfix">
							<div class="field description">
								<label>Description<span class="required">*</span> </label>
								<div>
									<input type="text" name="description" placeholder="Description"
										value=<%=medicineTo.getDescription()%>>
								</div>
								<div class="error"><%=errorMap.get("Description")%></div>
							</div></li>
						<li class="wide clearfix">
							<div class="field">
								<input class="button" type="submit" value="Register"> <input
									class="button" type="reset" value="Reset">
							</div></li>
					</ul>
				</form>
				<%
					}
				%>
			</div>

		</div>
	</div>
</body>
</html>