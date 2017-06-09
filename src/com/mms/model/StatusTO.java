package com.mms.model;

public class StatusTO {
	private String statusId;
	private String status;
	private String minAge;
	private String maxAge;
	
	public StatusTO() {
		statusId="";
		status="";
		minAge="";
		maxAge="";
	}
	public StatusTO(String statusId) {
		this.statusId=statusId;
		status="";
		minAge="";
		maxAge="";
	}
	
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMinAge() {
		return minAge;
	}
	public void setMinAge(String minAge) {
		this.minAge = minAge;
	}
	public String getMaxAge() {
		return maxAge;
	}
	public void setMaxAge(String maxAge) {
		this.maxAge = maxAge;
	}
}
