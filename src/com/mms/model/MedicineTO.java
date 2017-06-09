package com.mms.model;

public class MedicineTO {
	private String medicineId;
	private String medicineName;
	private String dosageValue;
	private String dosageUnit;
	private String priceOfStrip;
	private String description;
	private String noOfRequestedStrips;
	private String medicinesInStrip;
	private String registrationDate;
	private String manufactureDate;
	private String expiryDate;

	public MedicineTO() {
		medicineId="";
		medicineName="";
		dosageValue="";
		dosageUnit="";
		priceOfStrip="";
		description="";
		noOfRequestedStrips="";
		medicinesInStrip="";
		registrationDate="";
		manufactureDate="";
		expiryDate="";
	}

	public MedicineTO(String medicineId) {
		this.medicineId = medicineId;
		medicineName="";
		dosageValue="";
		dosageUnit="";
		priceOfStrip="";
		description="";
		noOfRequestedStrips="";
		medicinesInStrip="";
		registrationDate="";
		manufactureDate="";
		expiryDate="";
	}

	public String getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(String medicineId) {
		this.medicineId = medicineId;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public String getDosageValue() {
		return dosageValue;
	}

	public void setDosageValue(String dosageValue) {
		this.dosageValue = dosageValue;
	}

	public String getDosageUnit() {
		return dosageUnit;
	}

	public void setDosageUnit(String dosageUnit) {
		this.dosageUnit = dosageUnit;
	}

	public String getPriceOfStrip() {
		return priceOfStrip;
	}

	public void setPriceOfStrip(String priceOfStrip) {
		this.priceOfStrip = priceOfStrip;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMedicinesInStrip() {
		return medicinesInStrip;
	}

	public void setMedicinesInStrip(String medicinesInStrip) {
		this.medicinesInStrip = medicinesInStrip;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getManufactureDate() {
		return manufactureDate;
	}

	public void setManufactureDate(String manufactureDate) {
		this.manufactureDate = manufactureDate;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getNoOfRequestedStrips() {
		return noOfRequestedStrips;
	}

	public void setNoOfRequestedStrips(String noOfRequestedStrips) {
		this.noOfRequestedStrips = noOfRequestedStrips;
	}
}
