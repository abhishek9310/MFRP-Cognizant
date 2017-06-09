package com.mms.model;


public class BranchAdminTO {

	private String branchAdminId;
	private String firstName;
	private String lastName;
	private String address;
	private StateTO stateTO;
	private String email;
	private String password;
	private String gender;
	private String maritalStatus;
	private String contactNo;
	private StatusTO statusTO;
	private String dateOfBirth;
	private IdDocumentTO idDocumentTO;
	
	public BranchAdminTO(){
		branchAdminId="";
		firstName="";
		lastName="";
		address="";
		stateTO=new StateTO();
		email="";
		password="";
		gender="";
		maritalStatus="";
		contactNo="";
		statusTO=new StatusTO();;
		dateOfBirth="";
		idDocumentTO=new IdDocumentTO();
		
	}
	public BranchAdminTO(String branchAdminId) {
		this.branchAdminId=branchAdminId;
		firstName="";
		lastName="";
		address="";
		stateTO=new StateTO();
		email="";
		password="";
		gender="";
		maritalStatus="";
		contactNo="";
		statusTO=new StatusTO();;
		dateOfBirth="";
		idDocumentTO=new IdDocumentTO();
	}
	
	public String getBranchAdminId() {
		return branchAdminId;
	}
	public void setBranchAdminId(String branchAdminId) {
		this.branchAdminId = branchAdminId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public StateTO getStateTO() {
		return stateTO;
	}
	public void setStateTO(StateTO stateTO) {
		this.stateTO = stateTO;
	}
	public StatusTO getStatusTO() {
		return statusTO;
	}
	public void setStatusTO(StatusTO statusTO) {
		this.statusTO = statusTO;
	}
	public IdDocumentTO getIdDocumentTO() {
		return idDocumentTO;
	}
	public void setIdDocumentTO(IdDocumentTO idDocumentTO) {
		this.idDocumentTO = idDocumentTO;
	}
}
