package com.mms.bo;

import java.util.List;

import com.mms.dao.BranchDAO;
import com.mms.exceptions.MMSApplicationException;
import com.mms.exceptions.MMSBusinessException;
import com.mms.model.BranchTO;

public class BranchBO {
	public int setBranchAdminId(String branchAdminId, List<BranchTO> branches) throws MMSApplicationException {
		System.out.println("BO : BranchBO : setBranchAdminId : start");
		BranchDAO branchDAO =new BranchDAO();
		branchDAO.setBranchAdminId(branchAdminId, branches);
		int result = 0;
		System.out.println("BO : BranchBO : setBranchAdminId : end");
		return result;
	}
	public boolean validateBranches(List<BranchTO> branchTOs) throws MMSBusinessException, MMSApplicationException{
		boolean isBranchAvailable=true;
		if (branchTOs == null) {
			isBranchAvailable=false;
		}
		for (BranchTO branchTO : branchTOs) {
			if (branchTO.getBranchId() == null) {
				isBranchAvailable=false;
				break;
			}
		}
		if (!isBranchAvailable) {
			throw new MMSBusinessException("Please select branch");
		}
		BranchDAO branchDAO=new BranchDAO();
		branchDAO.validateBranches(branchTOs);
		return true;
	}
}
