/**
 * 
 */

$(document).ready(
		function() {
			vpw = $(window).width();
			vph = $(window).height();
			$('.leftCol,.rightCol').css({
				'height' : vph + 'px'
			});

			$(".stock .searchMedicineButton").click(
					function() {
						$.ajax({
							url : $('#medicineSearchUrl').val()
									+ "?val=stock&searchString="
									+ $('#searchBox').val(),
							success : function(result) {
								$("#searchMedicineResult").html(result);

							}
						});
					});

			$(".stock #searchBox").keyup(
					function() {
						$.ajax({
							url : $('#medicineSearchUrl').val()
									+ "?val=stock&searchString="
									+ $('#searchBox').val(),
							success : function(result) {
								$("#searchMedicineResult").html(result);

							}
						});
					});

			$(".request .searchMedicineButton").click(
					function() {
						$.ajax({
							url : $('#medicineSearchUrl').val()
									+ "?val=request&searchString="
									+ $('#searchBox').val(),
							success : function(result) {
								$("#searchMedicineResult").html(result);

							}
						});
					});

			$(".request #searchBox").keyup(
					function() {
						$.ajax({
							url : $('#medicineSearchUrl').val()
									+ "?val=request&searchString="
									+ $('#searchBox').val(),
							success : function(result) {
								$("#searchMedicineResult").html(result);

							}
						});
					});
			$("select.requestBy").change(
				
					function() {
				
						$.ajax({
							url : $('#fethRequestUrl').val() + "?type="
									+ $("select.requestBy").val(),
							success : function(result) {
								$("#fetchRequestsResult").html(result);

							}
						});
					});

		});

function validateRequest() {
	try {

		var noOfStrips = document.forms["requestMedicine"]["noOfStrips"].value;
		var number = /^[0-9]+$/;
		$(".error").text("");

		if (noOfStrips == null || noOfStrips == "") {
			$(".noOfStrips .error").text("Please enter number of strips.");
			var flag = 1;
		} else if (number.test(noOfStrips) == false) {
			$(".noOfStrips .error").text("Please enter only number");
			var flag = 1;
		}
		if (flag == 1)
			return false;

	} catch (err) {

	}
}

function validateStockDetails() {
	try {

		var noOfStrips = document.forms["registerStock"]["noOfStrips"].value;
		var stockDescription = document.forms["registerStock"]["stockDescription"].value;
		var number = /^[0-9]+$/;
		$(".error").text("");

		if (noOfStrips == null || noOfStrips == "") {
			$(".noOfStrips .error").text("Please enter number of strips.");
			var flag = 1;
		} else if (number.test(noOfStrips) == false) {
			$(".noOfStrips .error").text("Please enter only number");
			var flag = 1;
		}
		if (stockDescription == null || stockDescription == "") {
			$(".stockDescription .error")
					.text("Please enter number of strips.");
			var flag = 1;
		}
		if (flag == 1)
			return false;

	} catch (err) {

	}
}

function validateBranchAdmin() {

	try {

		var firstName = document.forms["branchAdminReg"]["firstName"].value;
		var lastName = document.forms["branchAdminReg"]["lastName"].value;
		var address = document.forms["branchAdminReg"]["address"].value;
		var country = document.forms["branchAdminReg"]["country"].value;
		var state = document.forms["branchAdminReg"]["state"].value;
		var email = document.forms["branchAdminReg"]["email"].value;
		var password = document.forms["branchAdminReg"]["password"].value;
		var gender = document.forms["branchAdminReg"]["gender"].value;
		var maritalStatus = document.forms["branchAdminReg"]["maritalStatus"].value;
		var contactNo = document.forms["branchAdminReg"]["contactNo"].value;
		var dob = document.forms["branchAdminReg"]["dob"].value;
		var idDocument = document.forms["branchAdminReg"]["idDocument"].value;
		var branches = document.getElementsByName('branches');

		var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		var number = /^[0-9]+$/;
		var chars = /^[a-zA-Z ]+$/;

		$(".error").text("");

		if (firstName == null || firstName == "") {
			$(".firstName .error").text("Please enter first name.");
			var flag = 1;
		} else if (chars.test(firstName) == false) {
			$(".firstName .error").text(
					"First Name can contain only alphabets and space");
			var flag = 1;
		} else if (firstName.length >= 50) {
			$(".firstName .error").text(
					"First Name length must be less than 50 character");
			var flag = 1;
		}

		if (lastName == null || lastName == "") {
			$(".lastName .error").text("Please enter Last name.");
			var flag = 1;
		} else if (chars.test(firstName) == false) {
			$(".lastName .error").text(
					"Last Name can contain only alphabets and space");
			var flag = 1;
		} else if (firstName.length >= 50) {
			$(".lastName .error").text(
					"Last Name length must be less than 50 character");
			var flag = 1;
		}

		if (address == null || address == "") {
			$(".address .error").text("Please enter address");
			var flag = 1;
		} else if (address.length >= 200) {
			$(".address .error").text(
					"Addrss length must be less than 200 character");
			var flag = 1;
		}

		if (country == null || country == "") {
			$(".country .error").text("Please select country");
			var flag = 1;
		}
		if (state == null || state == "") {
			$(".state .error").text("Please select state");
			var flag = 1;
		}
		if (email == null || email == "") {
			$(".email .error").text("Please Enter email address");
			var flag = 1;
		} else if (reg.test(email) == false) {
			$(".email .error").text("please enter a valid email");
			var flag = 1;
		}
		if (password == null || password == "") {
			$(".password .error").text("Please enter password");
			var flag = 1;
		}
		if (gender == null || gender == "") {
			$(".gender .error").text("Please select gender");
			var flag = 1;
		}

		if (maritalStatus == null || maritalStatus == "") {
			$(".maritalStatus .error").text("Please select marital status");
			var flag = 1;
		}
		if (contactNo == null || contactNo == "") {
			$(".contactNo .error").text("Please enter contact number");
			var flag = 1;
		} else if (number.test(contactNo) == false) {
			$(".contactNo .error").text("Please enter only number");
			var flag = 1;
		} else if (contactNo.length != 10) {
			$(".contactNo .error").text("Please fill 10 digit mobile no");
			var flag = 1;
		}
		if (dob == null || dob == "") {
			$(".dob .error").text("Please select date of birth");
			var flag = 1;
		}
		if (idDocument == null || idDocument == "") {
			$(".idDocument .error").text("Please Select id Document");
			var flag = 1;
		}
		var check = 0;
		for ( var i = 0; i < branches.length; i++) {
			if (branches[i].checked) {
				check = 1;
				break;
			}
		}
		if (check != 1) {
			$(".branch .error").text("Please Select branch");
			var flag = 1;
		}
		if (flag == 1)
			return false;

	} catch (err) {

	}

}

function validateMedicine() {

	var medicineName = document.forms["medicineRegistration"]["medicineName"].value;
	var dosageValue = document.forms["medicineRegistration"]["dosageValue"].value;
	var dosageUnit = document.forms["medicineRegistration"]["dosageUnit"].value;
	var stripPrice = document.forms["medicineRegistration"]["stripPrice"].value;
	var medicineInStrip = document.forms["medicineRegistration"]["medicineInStrip"].value;
	var manufactureDate = document.forms["medicineRegistration"]["manufactureDate"].value;
	var expiryDate = document.forms["medicineRegistration"]["expiryDate"].value;
	var description = document.forms["medicineRegistration"]["description"].value;
	$(".error").text("");
	if (medicineName == null || medicineName == "") {
		$(".medicineName .error").text("Please enter Medicine Name.");
		var flag = 1;
	}
	if (dosageValue == null || dosageValue == "") {
		$(".dosageValue .error").text("Please enter Dosage value.");
		var flag = 1;
	}
	if (dosageUnit == null || dosageUnit == "") {
		$(".dosageUnit .error").text("Please enter Dosage Unit.");
		var flag = 1;
	}
	if (stripPrice == null || stripPrice == "") {
		$(".stripPrice .error").text("Please enter Price.");
		var flag = 1;
	}

	if (medicineInStrip == null || medicineInStrip == "") {
		$(".medicineInStrip .error").text("Please enter Medicine in Strip.");
		var flag = 1;
	}
	if (manufactureDate == null || manufactureDate == "") {
		$(".manufactureDate .error").text("Please enter manufacture date.");
		var flag = 1;
	}
	if (expiryDate == null || expiryDate == "") {
		$(".expiryDate .error").text("Please enter expiry date.");
		var flag = 1;
	}
	if (description == null || description == "") {
		$(".description .error").text("Please enter description.");
		var flag = 1;
	}
	if (flag == 1)
		return false;
}