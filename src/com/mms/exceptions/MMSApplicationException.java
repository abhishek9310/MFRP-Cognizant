package com.mms.exceptions;

// TODO: Auto-generated Javadoc
/**
 * This class used to handle the ApplicationException.
 */
public class MMSApplicationException extends Exception {

	public MMSApplicationException() {
		super("Application is not responding <br><br> Please Contact System Adminstrator");
	}

	public MMSApplicationException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MMSApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public MMSApplicationException(String message) {
		super(message);
	}

	public MMSApplicationException(Throwable cause) {
		super(cause);
	}

}
