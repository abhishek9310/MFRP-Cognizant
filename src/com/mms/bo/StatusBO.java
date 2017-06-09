package com.mms.bo;

import com.mms.dao.StatusDAO;
import com.mms.exceptions.MMSApplicationException;
import com.mms.exceptions.MMSBusinessException;
import com.mms.model.StatusTO;
import com.mms.utils.OtherUtilities;
import com.mms.utils.Validator;

public class StatusBO {

	public StatusTO getStatusByDOB(String dateOfBirth) throws MMSApplicationException, MMSBusinessException{
		System.out.println("BO : StatusBO : getStatusByDOB : start");
		int age;
		System.out.println(dateOfBirth);
		age = OtherUtilities.calculateAgeInYear(dateOfBirth, "yyyy-MM-dd");
		System.out.println(age);
		StatusDAO statusDAO=new StatusDAO();
		System.out.println("BO : StatusBO : getStatusByDOB : end");
		return statusDAO.getStatusIdByAge(age);
	}
}
