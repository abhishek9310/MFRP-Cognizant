package com.mms.bo;

import com.mms.dao.BranchDAO;
import com.mms.dao.MedicineDAO;
import com.mms.dao.MedicineRequestDAO;
import com.mms.exceptions.MMSApplicationException;
import com.mms.exceptions.MMSBusinessException;
import com.mms.model.MedicineRequestTO;

public class MedicineRequestBO {
	public int requestMedicine(MedicineRequestTO medicineRequestTO)
			throws MMSApplicationException {
		System.out.println("BO : MedicineRequestBO : requestMedicine : start");
		/* validateMedicineRequest(medicineRequestTO); */
		MedicineRequestDAO medicineRequestDAO = new MedicineRequestDAO();
		medicineRequestDAO.requestMedicine(medicineRequestTO);
		System.out.println("BO : MedicineRequestBO : requestMedicine : end");
		return 1;
	}

	private void validateMedicineRequest(MedicineRequestTO medicineRequestTO)
			throws MMSApplicationException {
		System.out
				.println("BO : MedicineRequestBO : validateMedicineRequest : start");
		BranchDAO branchDAO = new BranchDAO();
		String branchId = null;
		String branchAdminId = null;
		branchDAO.validateBranch(branchId, branchAdminId);

		MedicineDAO medicineDAO = new MedicineDAO();
		String medicineId = null;
		medicineDAO.validateMedicine(medicineId);
		System.out
				.println("BO : MedicineRequestBO : validateMedicineRequest : end");
	}

	public int approveRequest(String requestId) throws MMSApplicationException,
			MMSBusinessException {

		MedicineRequestDAO medicineRequestDAO = new MedicineRequestDAO();
		MedicineDAO medicineDAO=new MedicineDAO();
		int validationResult = medicineRequestDAO
				.validatePendingRequestById(requestId);
		if (validationResult == 1) {
			medicineRequestDAO.approveRequest(requestId);
		} else {
			throw new MMSBusinessException(
					"This Pending request is not available");
		}

		return validationResult;
	}

	public int rejectRequest(String requestId) throws MMSApplicationException,
			MMSBusinessException {

		MedicineRequestDAO medicineRequestDAO = new MedicineRequestDAO();
		int validationResult = medicineRequestDAO
				.validatePendingRequestById(requestId);
		if (validationResult == 1) {
			medicineRequestDAO.rejectRequest(requestId);
		} else {
			throw new MMSBusinessException(
					"This Pending request is not available");
		}

		return validationResult;
	}

	public int cancleRequest(String requestId, String branchAdminId)
			throws MMSApplicationException, MMSBusinessException {
		System.out.println("BO : MedicineRequestBO : cancelRequest : start");
		MedicineRequestDAO medicineRequestDAO = new MedicineRequestDAO();
		int validationResult = medicineRequestDAO.validatePendingRequest(
				requestId, branchAdminId);
		if (validationResult == 1) {
			medicineRequestDAO.cancleRequest(requestId, branchAdminId);
		} else {
			throw new MMSBusinessException(
					"This Pending request is not available");
		}

		System.out.println("BO : MedicineRequestBO : cancelRequest : end");
		return validationResult;
	}
}
