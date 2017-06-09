<%@page import="java.util.HashMap"%>
<%@page import="com.mms.model.BranchAdminTO"%>
<%@page import="com.mms.bo.BranchAdminBO"%>
<%@page import="com.mms.utils.ServletUtilities"%>
<%@page import="com.mms.model.BranchTO"%>
<%@page import="com.mms.model.IdDocumentTO"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="com.mms.model.StateTO"%>
<%@page import="com.mms.model.CountryTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	BranchAdminTO branchAdminTO = new BranchAdminTO();
	if (request.getAttribute("branchAdminTO") != null) {
		branchAdminTO = (BranchAdminTO) request.getAttribute("branchAdminTO");
	}
	HashMap<String, String> errorMap = new HashMap<String, String>();
	if (request.getAttribute("errorMap") != null) {
		errorMap=(HashMap<String,String>)request.getAttribute("errorMap");
	} else {
		errorMap.put("firstName", "");
		errorMap.put("lastName", "");
		errorMap.put("address", "");
		errorMap.put("country", "");
		errorMap.put("state", "");
		errorMap.put("email", "");
		errorMap.put("password", "");
		errorMap.put("gender", "");
		errorMap.put("maritalStatus", "");
		errorMap.put("contactNo", "");
		errorMap.put("dateOfBirth", "");
		errorMap.put("idDocument", "");
		errorMap.put("branch", "");
	}
	List<CountryTO> countryTOs = (List<CountryTO>) request
			.getAttribute("countries");
	List<StateTO> stateTOs = (List<StateTO>) request
			.getAttribute("states");
	List<IdDocumentTO> idDocumentTOs = (List<IdDocumentTO>) request
			.getAttribute("idDocuments");
	List<BranchTO> branchTOs = (List<BranchTO>) request
			.getAttribute("unassignedBranches");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Branch Admin Registration</title>
<%@include file="AdminHead.jsp"%>
</head>
<body>
	<div>
		<%@include file="Menu.jsp"%>
		<div class="rightCol">
			<div class="head">
				<span class="headText">Branch Admin Registration</span>
			</div>
			<div class="MainContent">
				<%
					String branchAdminId = (String) request
							.getAttribute("branchAdminId");
					if (branchAdminId != null) {
				%><div class="submitSuccess">

					<span>Branch Admin Registration successful</span> <br> <span>
						Branch Admin Id : <%=request.getAttribute("branchAdminId")%><br>
						<span> Password : <%=request.getParameter("password")%><br>

							<div class="textBoxContainer">

								<input class="button" type="submit" value="GO TO HOME"
									onclick="window.location='<%=baseUrl%>/login';">
							</div>
				</div>
				<%
					} else {
				%>


				<form method="post" name="branchAdminReg" onsubmit="">

					<ul class="formStyle">
						<li class="nonWide clearfix">
							<div class="field firstName">
								<label>First Name<span class="required">*</span>
								</label>
								<div>
									<input type="text" name="firstName" placeholder="First Name" value="<%=branchAdminTO.getFirstName() %>">
								</div>
								<div class="error"><%=errorMap.get("firstName") %></div>
							</div>
							<div class="field lastName">
								<label>Last Name<span class="required">*</span>
								</label>
								<div>
									<input type="text" name="lastName" placeholder="Last Name" value="<%=branchAdminTO.getLastName() %>">
								</div>
								<div class="error"><%=errorMap.get("lastName") %></div>
							</div>
						</li>

						<li class="wide clearfix">
							<div class="field address">
								<label>Address<span class="required">*</span>
								</label>
								<div>
									<input type="text" name="address" placeholder="Address" value="<%=branchAdminTO.getAddress() %>">
								</div>
								<div class="error"><%=errorMap.get("address") %></div>
							</div>
						</li>
						<li class="nonWide clearfix">
							<div class="field country">
								<label>Country<span class="required">*</span>
								</label>
								<div>
									<select name="country">
										<option value="" disabled selected>-- Country --</option>
										<%
											for (CountryTO countryTO : countryTOs) {
										%>
										<option value="<%=countryTO.getCountryId()%>"><%=countryTO.getCountryName()%></option>
										<%
											}
										%>
									</select>
								</div>
								<div class="error"><%=errorMap.get("country") %></div>
							</div>
							<div class="field state">
								<label>State<span class="required">*</span>
								</label>
								<div>
									<select name="state">
										<option value="" disabled selected>-- State --</option>
										<%
											for (StateTO stateTO : stateTOs) {
										%>
										<option value="<%=stateTO.getStateId()%>"><%=stateTO.getStateName()%></option>
										<%
											}
										%>
									</select>
								</div>
								<div class="error"><%=errorMap.get("state") %></div>
							</div>
						</li>
						<li class="wide clearfix">
							<div class="field email">
								<label>Email<span class="required">*</span>
								</label>
								<div>
									<input type="text" name="email" placeholder="Email" value="<%=branchAdminTO.getEmail() %>"/>
								</div>
								<div class="error"><%=errorMap.get("email") %></div>
							</div>
						</li>
						<li class="wide clearfix">
							<div class="field password">
								<label>Password<span class="required">*</span>
								</label>
								<div>
									<input type="password" name="password" placeholder="Password" value="<%=branchAdminTO.getPassword() %>">
								</div>
								<div class="error"><%=errorMap.get("password") %></div>
							</div>
						</li>
						<li class="nonWide clearfix">
							<div class="field gender">
								<label>Gender<span class="required">*</span>
								</label>
								<div>
									<select name="gender">
										<option value="" disabled selected>-- Gender --</option>
										<option value="male">Male</option>
										<option value="female">Female</option>
									</select>
								</div>
								<div class="error"><%=errorMap.get("gender") %></div>
							</div>
							<div class="field maritalStatus">
								<label>Marital Status<span class="required">*</span>
								</label>
								<div>
									<select name="maritalStatus">
										<option value="" disabled selected>-- Marital Status
											--</option>
										<option value="married">Single</option>
										<option value="single">Married</option>
										<option value="divorced">Married</option>
										<option value="widowed">Single</option>
									</select>
								</div>
								<div class="error"><%=errorMap.get("maritalStatus") %></div>
							</div>
						</li>
						<li class="wide clearfix">
							<div class="field contactNo">
								<label>Contact Number<span class="required">*</span>
								</label>
								<div>
									<input type="text" name="contactNo"
										placeholder="Contact Number" value="<%=branchAdminTO.getContactNo() %>">
								</div>
								<div class="error"><%=errorMap.get("contactNo") %></div>
							</div>
						</li>

						<li class="nonWide clearfix">
							<div class="field dob">
								<label>Date Of Birth<span class="required">*</span>
								</label>
								<div>
									<input type="date" name="dob" value="<%=branchAdminTO.getDateOfBirth() %>">
								</div>
								<div class="error"><%=errorMap.get("dateOfBirth") %></div>
							</div>
							<div class="field idDocument">
								<label>Id Documents<span class="required">*</span>
								</label>

								<div>
									<select name="idDocument">
										<option value="" disabled selected>-- Id Document --</option>
										<%
											for (IdDocumentTO idDocumentTO : idDocumentTOs) {
										%>
										<option value="<%=idDocumentTO.getIdDocumentId()%>"><%=idDocumentTO.getPrefix()%></option>
										<%
											}
										%>
									</select>
								</div>
								<div class="error"><%=errorMap.get("idDocument") %></div>
							</div>
						</li>
						<li class="wide clearfix">
							<div class="field branch">
								<label>Branch<span class="required">*</span>
								</label>
								<div>
									<%
										for (BranchTO branchTO : branchTOs) {
									%><span> <input type="checkbox" name="branches"
										value="<%=branchTO.getBranchId()%>"><%=branchTO.getBranchName()%>
									</span>
									<%
										}
									%>
								</div>
								<div class="error"><%=errorMap.get("branch") %></div>
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
</body>
</html>
