package com.mms.model;

public class CountryTO {
	private String countryId;
	private String countryName;

	public CountryTO() {
		countryId="";
		countryName="";
	}

	public CountryTO(String countryId) {
		this.countryId = countryId;
		countryName="";
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
}
