package com.mms.model;

public class StockTO {
	private String stockId;
	private MedicineTO medicineTO;
	private BranchAdminTO branchAdminTO;
	private BranchTO branchTO;
	private String numberOfStrips;
	private String registrationDate;
	private String description;

	public StockTO() {
		stockId="";
		medicineTO=new MedicineTO();
		branchAdminTO=new BranchAdminTO();
		branchTO=new BranchTO();
		numberOfStrips="";
		registrationDate="";
		description="";
	}

	public StockTO(String stockId) {
		this.stockId = stockId;
		medicineTO=new MedicineTO();
		branchAdminTO=new BranchAdminTO();
		branchTO=new BranchTO();
		numberOfStrips="";
		registrationDate="";
		description="";
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public String getNumberOfStrips() {
		return numberOfStrips;
	}

	public MedicineTO getMedicineTO() {
		return medicineTO;
	}

	public void setMedicineTO(MedicineTO medicineTO) {
		this.medicineTO = medicineTO;
	}

	public BranchAdminTO getBranchAdminTO() {
		return branchAdminTO;
	}

	public void setBranchAdminTO(BranchAdminTO branchAdminTO) {
		this.branchAdminTO = branchAdminTO;
	}

	public BranchTO getBranchTO() {
		return branchTO;
	}

	public void setBranchTO(BranchTO branchTO) {
		this.branchTO = branchTO;
	}

	public void setNumberOfStrips(String numberOfStrips) {
		this.numberOfStrips = numberOfStrips;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
