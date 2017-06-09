package com.mms.model;

public class BranchTO {
	private String branchId;
	private BranchAdminTO branchAdminTO;
	private String branchName;

	public BranchTO() {
		branchId="";
		branchAdminTO=new BranchAdminTO();
		branchName="";
	}

	public BranchTO(String branchId) {
		this.branchId=branchId;
		branchAdminTO=new BranchAdminTO();
		branchName="";
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public BranchAdminTO getBranchAdminTO() {
		return branchAdminTO;
	}

	public void setBranchAdminTO(BranchAdminTO branchAdminTO) {
		this.branchAdminTO = branchAdminTO;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
}
