package com.mms.bo;

import java.util.HashMap;

import com.mms.dao.BranchDAO;
import com.mms.dao.BranchStockDAO;
import com.mms.dao.MedicineDAO;
import com.mms.exceptions.MMSApplicationException;
import com.mms.exceptions.MMSBusinessException;
import com.mms.model.BranchStockTO;
import com.mms.utils.Validator;

public class BranchStockBO {
	public int registereStock(BranchStockTO branchStockTO) throws MMSApplicationException, MMSBusinessException{
		System.out.println("BO : BranchStockBO : registerStock : start");
		validateStockDetails(branchStockTO);
		BranchStockDAO branchStockDAO=new BranchStockDAO();
		branchStockDAO.registerStock(branchStockTO);
		System.out.println("BO : BranchStockBO : registerStock : end");
		return 1;
	}
	
	private void validateStockDetails(BranchStockTO branchStockTO) throws MMSApplicationException, MMSBusinessException{
		System.out.println("BO : BranchStockBO : validateStockDetails : start");
		
		MedicineDAO medicineDAO=new MedicineDAO();
		BranchDAO branchDAO=new BranchDAO();
		boolean errorFlag=false;
		
		HashMap<String, String> errorMap=new HashMap<String, String>();
		errorMap.put("medicineId", "");
		errorMap.put("branch", "");
		errorMap.put("numberOfStrips", "");
		errorMap.put("description", "");
		
		String medicineId=branchStockTO.getMedicineTO().getMedicineId();
		String branchId=branchStockTO.getBranchTO().getBranchId();
		String numberOfStrips=branchStockTO.getNumberOfStrips();
		String description=branchStockTO.getDescription();
		String branchAdminId=branchStockTO.getBranchAdminTO().getBranchAdminId();
		
		if(medicineId == null || medicineId.isEmpty()){
			errorFlag=true;
			errorMap.put("medicineId", "Please fill the Medicine Id");
		}else if(medicineId.trim().length() != 5){
			errorFlag=true;
			errorMap.put("medicineId", "Medicine Id should be in 5 characters");
		}else if(!medicineDAO.validateMedicine(medicineId)){
			errorFlag=true;
			errorMap.put("medicineId","Medicine does not exits");
		}
		
		if(branchId == null || branchId.isEmpty()){
			errorFlag=true;
			errorMap.put("branch", "Please select Branch");
		}else if(!branchDAO.validateBranch(branchId, branchAdminId)){
			errorFlag=true;
			errorMap.put("branch","Please select valid Branch");
		}
		
		if(numberOfStrips == null || numberOfStrips.isEmpty()){
			errorFlag=true;
			errorMap.put("numberOfStrips", "Please enter number of strip");
		}else if(!Validator.isNumeric(numberOfStrips)){
			errorFlag=true;
			errorMap.put("numberOfStrips", "Please enter only digits");
		}else if( Integer.parseInt(numberOfStrips) == 0 ){
			errorFlag=true;
			errorMap.put("numberOfStrips", "Please enter at least one strip in stock");
		}else if(Integer.parseInt(numberOfStrips) > 10000 ){
			errorFlag=true;
			errorMap.put("numberOfStrips", "Stock can not contain more than 10000 medicines");
		}

		
		if(description == null || description.isEmpty()){
			errorFlag=true;
			errorMap.put("description", "Please fill the description");
		}else if(description.length() > 500){
			errorFlag=true;
			errorMap.put("description", "Description should be less than 500 characters");
		}
		
		if (errorFlag) {
			MMSBusinessException mmsBusinessException=new MMSBusinessException();
			mmsBusinessException.setErrorMap(errorMap);
			throw mmsBusinessException;
		}
		System.out.println("BO : BranchStockBO : validateStockDetails : end");
	}

}
