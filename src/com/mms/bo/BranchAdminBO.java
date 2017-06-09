package com.mms.bo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.mms.dao.BranchAdminDAO;
import com.mms.dao.IdDocumentDAO;
import com.mms.dao.StateDAO;
import com.mms.exceptions.MMSApplicationException;
import com.mms.exceptions.MMSBusinessException;
import com.mms.model.BranchAdminTO;
import com.mms.model.BranchTO;
import com.mms.model.IdDocumentTO;
import com.mms.model.StateTO;
import com.mms.model.StatusTO;
import com.mms.utils.Validator;

public class BranchAdminBO {

	private static final Logger LOG = Logger.getLogger(BranchAdminBO.class);

	public int loginBranchAdmin(BranchAdminTO branchAdminTO)
			throws MMSApplicationException, MMSBusinessException {
		System.out.println("BO : BranchAdminBO : loginBranchAdmin : start");
		LOG.info("Inside BO.... UserName = " + branchAdminTO.getFirstName());
		validateLoginDetail(branchAdminTO);
		BranchAdminDAO branchAdminDAO = new BranchAdminDAO();
		int result = branchAdminDAO.validateBranchAdmin(branchAdminTO);
		if (result == 0) {
			throw new MMSBusinessException("Invalid Username");
		} else if (result == -1) {
			throw new MMSBusinessException("Username/password does not match");
		}
		System.out.println("BO : BranchAdminBO : loginBranchAdmin : end");
		return result;

	}

	public String registerBranchAdmin(BranchAdminTO branchAdminTO,List<BranchTO> branchTOs)
			throws MMSApplicationException, MMSBusinessException {
		System.out.println("BO : BranchAdminBO : registerBranchAdmin : start");
		String branchAdminId;
		BranchAdminDAO branchAdminDAO = new BranchAdminDAO();
		BranchBO branchBO=new BranchBO();
		StatusBO statusBO = new StatusBO();
		
		validateRegistrationDetails(branchAdminTO,branchTOs);
		StatusTO statusTO = statusBO.getStatusByDOB(branchAdminTO.getDateOfBirth());
		branchAdminTO.setStatusTO(statusTO);
		
		branchBO.validateBranches(branchTOs);
		branchAdminId= branchAdminDAO.registerBranchAdmin(branchAdminTO);
		branchBO.setBranchAdminId(branchAdminId, branchTOs);

		System.out.println("BO : BranchAdminBO : registerBranchAdmin : end");
		return branchAdminId;

	}

	private void validateRegistrationDetails(BranchAdminTO branchAdminTO,List<BranchTO> branchTOs)
			throws MMSBusinessException, MMSApplicationException {
		System.out
				.println("BO : BranchAdminBO : validateRegistrationDetails : start");
		boolean errorFlag = false;
		HashMap<String, String> errorMap = new HashMap<String, String>();
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

		StateDAO stateDAO = new StateDAO();
		IdDocumentDAO idDocumentDAO = new IdDocumentDAO();

		String firstName = branchAdminTO.getFirstName();
		String LastName = branchAdminTO.getLastName();
		String address = branchAdminTO.getAddress();
		StateTO stateTO = branchAdminTO.getStateTO();
		String email = branchAdminTO.getEmail();
		String password = branchAdminTO.getPassword();
		String gender = branchAdminTO.getGender();
		String maritalStatus = branchAdminTO.getMaritalStatus();
		String contactNo = branchAdminTO.getContactNo();
		String dateOfBirth = branchAdminTO.getDateOfBirth();
		IdDocumentTO idDocumentTO = branchAdminTO.getIdDocumentTO();

		if (firstName == null || firstName.isEmpty()) {
			errorFlag = true;
			errorMap.put("firstName", "Please enter first name.");
		} else if (!firstName.matches("[a-zA-Z ]+")) {
			errorFlag = true;
			errorMap.put("firstName",
					"First Name can contain only alphabets and space");
		} else if (firstName.length() > 50) {
			errorFlag = true;
			errorMap.put("firstName",
					"First Name length must be less than 50 character");
		}

		if (LastName == null || LastName.isEmpty()) {
			errorFlag = true;
			errorMap.put("lastName", "Please enter last name");
		} else if (!LastName.matches("[a-zA-Z ]+")) {
			errorFlag = true;
			errorMap.put("lastName",
					"Last Name can contain only alphabets and space");
		} else if (LastName.length() > 50) {
			errorFlag = true;
			errorMap.put("lastName",
					"Last Name length must be less than 50 character ");
		}

		if (address == null || address.isEmpty()) {
			errorFlag = true;
			errorMap.put("address", "Please enter address");

		} else if (address.length() > 200) {
			errorFlag = true;
			errorMap.put("address",
					"Address length must be less than 200 character");
		}
		
		if (stateTO.getCountryTO().getCountryId() == null || stateTO.getCountryTO().getCountryId().isEmpty()) {
			errorFlag = true;
			errorMap.put("country", "Please select country");
		} 
		
		if (stateTO.getStateId() == null || stateTO.getStateId().isEmpty()) {
			errorFlag = true;
			errorMap.put("state", "Please select state");
		} else if (!stateDAO.validateState(stateTO)) {
			errorFlag = true;
			errorMap.put("state", "Please select state from given states");
		}

		if(email == null || email.isEmpty()){
			errorFlag = true;
			errorMap.put("email", "Please Enter email address");
		}else if (!Validator.validateEmailAddress(email).equals("Success")) {
			errorFlag = true;
			errorMap.put("email", Validator.validateEmailAddress(email));
		}
		
		if(password == null || password.isEmpty()){
			errorFlag = true;
			errorMap.put("password", "Please enter password");			
		}else if (!Validator.validatePassword(password).equals("Success")) {
			errorFlag = true;
			errorMap.put("password", Validator.validatePassword(password));
		}
		if (gender == null || gender.isEmpty()) {
			errorFlag = true;
			errorMap.put("gender", "Please select gender");
		} else if (!(gender.equals("male") || gender.equals("female"))) {
			errorFlag = true;
			errorMap.put("gender",
					"Please select gender from the given genders");
		}
		
		if (maritalStatus == null || maritalStatus.isEmpty()) {
			errorFlag = true;
			errorMap.put("maritalStatus", "Please select marital status");
		}

		if (contactNo == null || contactNo.isEmpty()) {
			errorFlag = true;
			errorMap.put("contactNo", "Please enter contact number");
		} else if (!Validator.validateContactNumber(contactNo)
				.equals("Success")) {
			errorFlag = true;
			errorMap.put("contactNo",
					Validator.validateContactNumber(contactNo));
		}

		if (dateOfBirth == null || dateOfBirth.isEmpty()) {
			errorFlag = true;
			errorMap.put("dateOfBirth", "Please select date of birth");
		} else if (dateOfBirth.matches("[0-9]{4}[-][0-9]{2}[-][0-9]{2}")) {
			System.out.println(dateOfBirth);
			if (!Validator.validateDate(dateOfBirth, "yyyy-MM-dd").equals(
					"Success")) {
				errorFlag = true;
				errorMap.put("dateOfBirth",
						Validator.validateDate(dateOfBirth, "yyyy-MM-dd"));
			} else {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyy-MM-dd");
				Date dob = null;
				try {
					dob = simpleDateFormat.parse(dateOfBirth);
				} catch (ParseException e) {
					errorFlag = true;
					errorMap.put("dateOfBirth",
							"Please enter date of birth in valid format");
				}
				Date currentDay = new Date();
				if (dob.after(currentDay)) {
					errorFlag = true;
					errorMap.put("dateOfBirth",
							"You can not select future date as date of birth");
				}
			}
		} else {
			errorFlag = true;
			errorMap.put("dateOfBirth", "Please select valid date");
		}

		if (idDocumentTO.getIdDocumentId() == null
				|| idDocumentTO.getIdDocumentId().isEmpty()) {
			errorFlag = true;
			errorMap.put("idDocument", "Please Select id Document");
		} else if (!idDocumentDAO.validateIdentityProof(idDocumentTO)) {
			errorFlag = true;
			errorMap.put("idDocument", "Please select Id from given Ids");
		}

		if(branchTOs == null || branchTOs.size() == 0){
			errorFlag = true;
			errorMap.put("branch", "Please select branches");
		}
		
		if (errorFlag) {
			MMSBusinessException mmsBusinessException = new MMSBusinessException();
			mmsBusinessException.setErrorMap(errorMap);
			throw mmsBusinessException;
		}

		System.out
				.println("BO : BranchAdminBO : validateRegistrationDetails : end");
	}

	private void validateLoginDetail(BranchAdminTO branchAdminTO)
			throws MMSBusinessException {
		System.out.println("BO : BranchAdminBO : validateLoginDetails : start");
		String branchAdminId = branchAdminTO.getBranchAdminId();
		String password = branchAdminTO.getPassword();

		if ((branchAdminId == null || branchAdminId.isEmpty())
				&& (password == null || password.isEmpty())) {
			throw new MMSBusinessException("Please enter username and password");
		}
		if (branchAdminId == null || branchAdminId.isEmpty()) {
			throw new MMSBusinessException("Username can not be blank");
		}
		if (password == null || password.isEmpty()) {
			throw new MMSBusinessException("Password can not be blank");
		}
		System.out.println("BO : BranchAdminBO : validateLoginDetails : end");
	}

}
