package com.mms.bo;

import java.util.HashMap;
import java.util.List;

import com.mms.dao.MedicineDAO;
import com.mms.exceptions.MMSApplicationException;
import com.mms.exceptions.MMSBusinessException;
import com.mms.model.MedicineTO;
import com.mms.utils.OtherUtilities;
import com.mms.utils.Validator;

public class MedicineBO {
	public int registerMedicine(MedicineTO medicineTO)
			throws MMSApplicationException, MMSBusinessException {
		System.out.println("BO : MedicineBO : registerMedicine : start");
		validateMedicineDetails(medicineTO);
		MedicineDAO medicineDAO = new MedicineDAO();
		medicineDAO.registerMedicine(medicineTO);
		System.out.println("BO : MedicineBO : registerMedicine : end");
		return 1;
	}

	private void validateMedicineDetails(MedicineTO medicineTO)
			throws MMSBusinessException {

		String medicineName = medicineTO.getMedicineName();
		String dosage = medicineTO.getDosageValue();
		String dosageUnit = medicineTO.getDosageUnit();
		String medicinesInStrip = medicineTO.getMedicinesInStrip();
		/*String numberOfStrip = medicineTO.getNoOfRequestedStrips();*/
		String manufactureDate = medicineTO.getManufactureDate();
		String expiryDate = medicineTO.getExpiryDate();
		String priceOfStrip = medicineTO.getPriceOfStrip();
		String description = medicineTO.getDescription();

		HashMap<String, String> errorMap = new HashMap<String, String>();
		errorMap.put("medicineName", "");
		errorMap.put("dosageUnit", "");
		errorMap.put("dosageValue", "");
		/*errorMap.put("numberOfStrip", "");*/
		errorMap.put("medicinesInStrip", "");
		errorMap.put("manufactureDate", "");
		errorMap.put("expiryDate", "");
		errorMap.put("priceOfStrip", "");
		errorMap.put("Description", "");

		boolean flag = true;

		if (medicineName.isEmpty() || (medicineName == null)) {
			flag = false;
			errorMap.put("medicineName", "medicine name should not be empty");
		}

		else if (medicineName.length() > 200) {
			flag = false;
			errorMap.put("medicineName", "medicine name too long");
		}

		if (dosageUnit.isEmpty() || dosageUnit == null) {
			flag = false;
			errorMap.put("dosageUnit", "field should not be empty");
		}

		else if (!dosageUnit.matches("[A-Za-z]+")) {
			flag = false;
			errorMap.put("dosageUnit", "numbers are not allowed");
		}

		if (dosage.isEmpty() || dosage == null) {
			flag = false;
			errorMap.put("dosageValue", "field should not be empty");
		}

		else if (!dosage.matches("[0-9]+")) {
			flag = false;
			errorMap.put("dosageValue", "only numbers are allowed");
		}

		if (medicinesInStrip.isEmpty() || medicinesInStrip == null) {
			flag = false;
			errorMap.put("medicinesInStrip", "field should not be empty");
		} else if (!medicinesInStrip.matches("[0-9]+")) {
			flag = false;
			errorMap.put("medicinesInStrip", "only numbers are allowed");
		} else if (Integer.parseInt(medicinesInStrip) < 0) {
			flag = false;
			errorMap.put("medicinesInStrip", "invalid value");
		}
/*
		if (numberOfStrip.isEmpty() || numberOfStrip == null) {
			flag = false;
			errorMap.put("numberOfStrip", "field should not be empty");
		}

		else if (!medicinesInStrip.matches("[0-9]+")) {
			flag = false;
			errorMap.put("medicinesInStrip", "only numbers are allowed");
		}*/

	/*	else if (Integer.parseInt(numberOfStrip) > 10000
				|| Integer.parseInt(numberOfStrip) < 0) {
			flag = false;
			errorMap.put("medicinesInStrip", "invalid value");
		}*/

		if (priceOfStrip.isEmpty() || priceOfStrip == null) {
			flag = false;
			errorMap.put("priceOfStrip", "field should not be empty");
		}

		else if (!priceOfStrip.matches("[0-9]+(?:\\.[0-9]+)?")) {
			flag = false;
			errorMap.put("priceOfStrip", "Invalid value");
		}

		else if (Float.parseFloat(priceOfStrip) < 0) {
			flag = false;
			errorMap.put("priceOfStrip", "invalid value");
		}
		System.out.println("heree " + description);
		if (description.isEmpty() || description == null) {
			flag = false;
			errorMap.put("Description", "field shouldnot be empty");
		}

		else if (description.length() > 200) {
			flag = false;
			errorMap.put("Description", "description is too long");
		}

		String datePattern = "yyyy-MM-dd";
		boolean dateFlag = true;
		if (manufactureDate.isEmpty() || manufactureDate == null) {
			flag = false;
			dateFlag = false;
			errorMap.put("manufactureDate", "field should not be empty");
		}

		else if (!Validator.validateDate(manufactureDate, datePattern).equals(
				"Success")) {
			flag = false;
			dateFlag = false;
			errorMap.put("manufactureDate",
					Validator.validateDate(manufactureDate, datePattern));
		}

		if (expiryDate.isEmpty() || expiryDate == null) {
			flag = false;
			dateFlag = false;
			errorMap.put("expiryDate", "field should not be empty");
		}

		else if (!Validator.validateDate(expiryDate, datePattern).equals(
				"Success")) {
			flag = false;
			dateFlag = false;
			errorMap.put("expiryDate",
					Validator.validateDate(expiryDate, datePattern));
			System.out.println(errorMap);

		}
		System.out.println(errorMap);

		if (dateFlag) {
			System.out.println(" validate..............");
			if (!OtherUtilities.compareDate(expiryDate, manufactureDate)
					.equals("Success")) {
				flag = false;
				errorMap.put("expiryDate",
						"value should be greater than manufacturing date");
				errorMap.put("manufactureDate",
						"value should be less than expiry date");
			}
		}

		System.out.println("flag= ...." + flag);

		if (!flag) {
			MMSBusinessException be = new MMSBusinessException();
			System.out.println("Before throwing exception");
			System.out.println(errorMap);
			be.setErrorMap(errorMap);
			System.out.println(errorMap);
			throw be;
		}

	}
}
