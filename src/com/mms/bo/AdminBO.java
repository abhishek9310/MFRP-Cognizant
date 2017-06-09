package com.mms.bo;

import com.mms.dao.AdminDAO;
import com.mms.exceptions.MMSApplicationException;
import com.mms.exceptions.MMSBusinessException;
import com.mms.model.AdminTO;

public class AdminBO {
	public int loginAdmin(AdminTO userTO) throws MMSBusinessException,
			MMSApplicationException {
		System.out.println("BO : AdminBO : loginAdmin : start");
		int result;
		validateAdminDetail(userTO);
		
		AdminDAO loginDAO = new AdminDAO();
		result = loginDAO.validateAdmin(userTO);
		
		if (result == 0) {
			throw new MMSBusinessException("Invalid Username");
		} else if (result == -1) {
			throw new MMSBusinessException("Username/password does not match");
		}
		System.out.println("BO : AdminBO : loginAdmin : end");
		return result;
	}

	private void validateAdminDetail(AdminTO userTO)
			throws MMSBusinessException {
		System.out.println("BO : AdminBO : validateAdminDetail : start");
		String user = userTO.getUsername();
		String pass = userTO.getPassWord();

		if((user == null || user.isEmpty()) && (pass == null || pass.isEmpty()) ){
			throw new MMSBusinessException("Please enter username and password");
		}
		if (user == null || user.isEmpty()) {
			throw new MMSBusinessException("Username can not be blank");
		}
		if (pass == null || pass.isEmpty()) {
			throw new MMSBusinessException("Password can not be blank");
		}
		System.out.println("BO : AdminBO : validateAdminDetail : end");
	}
}
