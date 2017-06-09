package com.mms.exceptions;

import java.util.HashMap;

public class MMSBusinessException extends Exception {

	private HashMap<String, String> errorMap = new HashMap<String, String>();

	private static final long serialVersionUID = 1L;

	public MMSBusinessException() {
	}
	
	public MMSBusinessException(final String message) {
		super(message);
	}

	public MMSBusinessException(final String message, final Throwable exception) {
		super(message, exception);

	}

	public MMSBusinessException(final Throwable exception) {
		super(exception);
	}

	public void setErrorMap(HashMap<String, String> errorMap) {
		this.errorMap = errorMap;
	}

	public HashMap<String, String> getErrorMap() {
		return errorMap;
	}

}
