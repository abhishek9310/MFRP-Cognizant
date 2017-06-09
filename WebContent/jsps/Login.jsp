<%@page import="com.mms.utils.ServletUtilities"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String baseUrl = ServletUtilities.getBaseUrl(request);
	String errorMsg = "";
	if (request.getAttribute("errorMsg") != null) {
		errorMsg = request.getAttribute("errorMsg").toString();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MMS Login</title>
<link type="text/css" rel="stylesheet" href="<%=baseUrl%>/css/login.css">
<script src="<%=baseUrl%>/js/jquery-2.1.4.min.js"></script>
<script src="<%=baseUrl%>/js/script.js"></script>
<script src="<%=baseUrl%>/js/validator.js"></script>
</head>
<body>
	<div class="homeContainer clearfix">
		<div class="homeHead">
			<div class="logoContainer">
				<span class="logoLeft">MONITER </span><span class="logoRight">MEDICINE</span>

			</div>
		</div>
		<div class="HomeBody">
			<div class="subHomeBody">
				<div class="loginBlock">
					<div class="lockIcon">
						<img src="<%=baseUrl%>/images/lock.png">
					</div>
					<span class="headText">Sign in to Continue</span><br> <span
						class="errorMsg"><%=errorMsg%></span>
					<form method="post">
						<div class="textBoxContainer">
							<div class="inputBoxIcon">
								<img src="<%=baseUrl%>/images/cogs.png">
							</div>
							<select name="role" class="textBox">
								<option value="admin">Admin</option>
								<option value="branchAdmin">Branch Admin</option>
							</select>
						</div>
						<div class="textBoxContainer">
							<div class="inputBoxIcon">
								<img src="<%=baseUrl%>/images/user.png">
							</div>
							<input placeholder="Username" class="textBox" type="text"
								name="userName">
						</div>
						<div class="textBoxContainer">
							<div class="inputBoxIcon">
								<img src="<%=baseUrl%>/images/key.png">
							</div>
							<input placeholder="Password" class="textBox" type="password"
								name="passWord">
						</div>

						<div class="textBoxContainer">
							<input class="button" type="submit" value="Sign In">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>