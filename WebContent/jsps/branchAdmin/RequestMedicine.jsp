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
	String success = (String) request.getAttribute("success");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Request Medicine</title>
<%@include file="BranchAdminHead.jsp"%>
</head>
<body>
	<div>
		<%@include file="Menu.jsp"%>
		<div class="rightCol">
			<div class="head">
				<span class="headText">Request Medicine</span>
			</div>
			<div class="MainContent request">
				<%
					if (success != null) {
				%>
				<div class="submitSuccess">

					<span>Medicine Request Registered Successful</span>

					<div class="textBoxContainer">

						<input class="button" type="submit" value="See All Request"
							onclick="window.location='allRequests';">
					</div>
				</div>

				<%
					} else {
				%>
				<%
					if (medicineTOs != null) {
				%>
				<div class="searchMedicine">
					<span>Search medicine for Medicine Request</span><br> <input
						id="searchBox" type="text" placeholder="Enter Medicine id or name">
					<input id="medicineSearchUrl" type="hidden"
						value="<%=baseUrl%>/branchAdmin/SearchMedicine">
					<div class="button searchMedicineButton">Search Medicine</div>
				</div>
				<jsp:include page="MedicineForRequest.jsp"></jsp:include>
				<%
					}
				%>
				<%
					if (branchTOs != null && medicineTO != null) {
				%>
				<form method="post" name="requestMedicine" onsubmit="return validateRequest()">

					<ul class="formStyle">

						<li class="wide clearfix">
							<div class="field medicineId">
								<label>Medicine Id<span class="required">*</span>
								</label>
								<div>
									<input type="text" placeholder="Medicine Id" name="medicineId"
										value="<%=medicineTO.getMedicineId()%>" readonly>
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
							<div class="field dosage">
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
								<div class="error"></div>
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
								<div class="error"></div>
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
				}
			%>
		</div>

	</div>
	</div>
</body>
</html>