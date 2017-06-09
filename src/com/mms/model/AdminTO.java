package com.mms.model;

public class AdminTO {
	private String userName;
	private String passWord;
	
	public AdminTO() {
		userName="";
		passWord="";
	}
	
	public String getUsername() {
		return userName;
	}
	public void setUserName(String username) {
		this.userName = username;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
}
