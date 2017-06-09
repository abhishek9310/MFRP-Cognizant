<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%

	String currentPage = (String)request.getAttribute("currentPage");
	String home = "";
	String stockRegister = "";
	String requestMedicine = "";
	String cancelRequest = "";
	if (currentPage != null) {
		if (currentPage.equals("home")) {
			home = "active";
		} else if (currentPage.equals("registerStock")) {
			stockRegister = "active";
		} else if (currentPage.equals("requestMedicine")) {
			requestMedicine = "active";
		} else if (currentPage.equals("cancelRequest")) {
			cancelRequest = "active";
		}
	}
%>
<div class="leftCol">

	<div class="logoContainer">
		<!-- <img class="logo" src="logo.png"> -->
		<span class="logoLeft">MONITER </span><span class="logoRight">MEDICINE</span>

	</div>
	<div class="userContainer">
		<div class="userWaterMark">
			<img src="../images/userPlaceHolder.png">
		</div>
		<div class="userName">Branch Admin Home</div>
	</div>
	<div class="menuContainer">

		<ul class="menu">

			<a href="home"><li class="<%=home%>">Home</li> </a>
			<a href="registerStock"><li class="<%=stockRegister%>">Register Medicine
					Stock</li> </a>
			<a href="stockDetails"><li>Stock Details</li> </a>
			<a href="requestMedicine"><li class="<%=requestMedicine%>">Request Medicine</li> </a>
			<a href="cancelRequest"><li class="<%=cancelRequest%>">Cancel Request</li> </a>
			<a href="allRequests"><li>All Request</li> </a>
			
			<a href="logout"><li>Logout</li> </a>
		</ul>
	</div>
</div>