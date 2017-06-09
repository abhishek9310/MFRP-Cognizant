<%@page import="java.util.HashMap"%>
<%@page import="com.mms.utils.ServletUtilities"%>
<%@page import="com.mms.model.BranchTO"%>
<%@page import="com.mms.model.MedicineTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	MedicineTO medicineTO = (MedicineTO) request
			.getAttribute("medicine");
	List<MedicineTO> medicineTOs = (List<MedicineTO>) request
			.getAttribute("medicines");
	List<BranchTO> branchTOs = (List<BranchTO>) request
			.getAttribute("branches");
	HashMap<String, String> errorMap = new HashMap<String, String>();
	if (request.getAttribute("errorMap") != null) {
		errorMap = (HashMap<String, String>) request
				.getAttribute("errorMap");
	} else {
		errorMap.put("medicineId", "");
		errorMap.put("branch", "");
		errorMap.put("numberOfStrips", "");
		errorMap.put("description", "");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Register Stock</title>
<%@include file="BranchAdminHead.jsp"%>
</head>
<body>
	<div>
		<%@include file="Menu.jsp"%>
		<div class="rightCol">
			<div class="head">
				<span class="headText">Register Medicine Stock</span>
			</div>
			<div class="MainContent stock">
				<%
					if (medicineTOs != null) {
				%>
				<div class="searchMedicine">
					<span>Search medicine for stock registration</span><br> <input
						id="searchBox" type="text" placeholder="Enter Medicine id or name">
					<input id="medicineSearchUrl" type="hidden"
						value="<%=baseUrl%>/branchAdmin/SearchMedicine">
					<div class="button searchMedicineButton">Search Medicine</div>
				</div>
				<jsp:include page="MedicineForStock.jsp"></jsp:include>
				<%
					}
				%>

				<%
					if (branchTOs != null && medicineTO != null) {
				%>
				<form method="post" name="registerStock" onsubmit="return validateStockDetails()">

					<ul class="formStyle">

						<li class="wide clearfix">
							<div class="field medicineId">
								<label>Medicine Id<span class="required">*</span>
								</label>
								<div>
									<input type="text" placeholder="Medicine Id" name="medicineId"
										value="<%=medicineTO.getMedicineId()%>" readonly>
								</div>
								<div class="error"><%=errorMap.get("medicineId") %></div>
							</div>
						</li>
						<li class="wide clearfix">
							<div class="field medicineName">
								<label>Medicine Name<span class="required">*</span>
								</label>
								<div>
									<input type="text" placeholder="Medicine Name"
										name="medicineName" value="<%=medicineTO.getMedicineName()%>" readonly>
								</div>
								<div class="error"></div>
							</div>
						</li>
						<li class="wide clearfix">
							<div class="field dosage">
								<label>Dosage<span class="required">*</span>
								</label>
								<div>
									<input type="text" placeholder="Dosage" name="dosage"
										value="<%=medicineTO.getDosageValue()
						+ medicineTO.getDosageUnit()%>" readonly>
								</div>
								<div class="error"></div>
							</div>
						</li>
						<li class="wide clearfix">
							<div class="field branch">
								<label>Branch<span class="required">*</span>
								</label>
								<div>
									<select name="branchId"> 
										<%
											for (BranchTO branchTO : branchTOs) {
										%>
										<option value="<%=branchTO.getBranchId()%>"><%=branchTO.getBranchName()%></option>
										<%
											}
										%>
									</select>
								</div>
								<div class="error"><%=errorMap.get("branch") %></div>
							</div>
						</li>
						<li class="wide clearfix">
							<div class="field noOfStrips">
								<label>Number of Strips<span class="required">*</span>
								</label>
								<div>
									<input type="text" placeholder="Number Of Strips"
										name="noOfStrips">
								</div>
								<div class="error"><%=errorMap.get("numberOfStrips") %></div>
							</div>
						</li>

						<li class="wide clearfix">
							<div class="field stockDescription">
								<label>Discription of stock<span class="required">*</span>
								</label>
								<div>
									<input type="text" placeholder="Description"
										name="stockDescription">
								</div>
								<div class="error"><%=errorMap.get("description") %></div>
							</div>
						</li>

						<li class="wide clearfix">
							<div class="field">
								<input class="button" type="submit" value="Register"> <input
									class="button" type="reset" value="Reset">
							</div>
						</li>
					</ul>
				</form>
			</div>
			<%
				}
			%>
		</div>

	</div>

	</div>
</body>
</html>