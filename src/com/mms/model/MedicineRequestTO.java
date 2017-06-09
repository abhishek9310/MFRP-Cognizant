package com.mms.model;

public class MedicineRequestTO {

	private String medicineRequestId;
	private MedicineTO medicineTO;
	private String noOfStrips;
	private BranchTO branchTO;
	private String requestDate;
	private BranchAdminTO branchAdminTO;
	private String status;

	public MedicineRequestTO() {
		medicineRequestId="";
		medicineTO=new MedicineTO();
		noOfStrips="";
		branchTO=new BranchTO();
		requestDate="";
		branchAdminTO=new BranchAdminTO();
		status="";
	}

	public MedicineRequestTO(String medicineRequestId) {
		this.medicineRequestId = medicineRequestId;
		medicineTO=new MedicineTO();
		noOfStrips="";
		branchTO=new BranchTO();
		requestDate="";
		branchAdminTO=new BranchAdminTO();
		status="";
	}

	public String getMedicineRequestId() {
		return medicineRequestId;
	}

	public void setMedicineRequestId(String medicineRequestId) {
		this.medicineRequestId = medicineRequestId;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	public MedicineTO getMedicineTO() {
		return medicineTO;
	}

	public void setMedicineTO(MedicineTO medicineTO) {
		this.medicineTO = medicineTO;
	}

	public BranchTO getBranchTO() {
		return branchTO;
	}

	public void setBranchTO(BranchTO branchTO) {
		this.branchTO = branchTO;
	}

	public BranchAdminTO getBranchAdminTO() {
		return branchAdminTO;
	}

	public void setBranchAdminTO(BranchAdminTO branchAdminTO) {
		this.branchAdminTO = branchAdminTO;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNoOfStrips() {
		return noOfStrips;
	}

	public void setNoOfStrips(String noOfStrips) {
		this.noOfStrips = noOfStrips;
	}

}
