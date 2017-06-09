package com.mms.model;

public class StateTO {
	private String stateId;
	private CountryTO countryTO;
	private String stateName;

	public StateTO() {
		stateId="";
		countryTO=new CountryTO();
		stateName="";
	}

	public StateTO(String stateId) {
		this.stateId = stateId;
		countryTO=new CountryTO();
		stateName="";
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public CountryTO getCountryTO() {
		return countryTO;
	}

	public void setCountryTO(CountryTO countryTO) {
		this.countryTO = countryTO;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
}
