package com.mms.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class Validator {
	private final static Pattern hasUppercase = Pattern.compile("[A-Z]");
	private final static Pattern hasLowercase = Pattern.compile("[a-z]");
	private final static Pattern hasAlphaChar = Pattern.compile("[a-zA-Z]");
	private final static Pattern hasDotAndAtTheRate = Pattern.compile("[@.]");
	private final static Pattern hasNumber = Pattern.compile("\\d");
	private final static Pattern hasSpecialChar = Pattern.compile("[&@!#]");
	private final static Pattern emailNamePtrn = Pattern
			.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

	public static String validateEmailAddress(String email) {
		System.out.println("Utils : Validator : validateEmail : start");
		if (email == null || email.isEmpty()) {
			return "Email must not be blank";
		}

		StringBuilder result = new StringBuilder();
		if (email.length() > 254) {
			result.append("Email must be less than 254 character <br>");
		}
		if (!hasAlphaChar.matcher(email).find()) {
			result.append("Email must contain alpha character <br>");
		}
		if (!hasDotAndAtTheRate.matcher(email).find()) {
			result.append("Email must contain . and @ character <br>");
		}
		if (!hasAlphaChar.matcher(email.substring(0, 1)).find()) {
			result.append("Email must start with alpha character <br>");
		}
		if (!emailNamePtrn.matcher(email).find()) {
			result.append("Email must hava a . character after @ <br>");
		}

		if (result.length() == 0) {
			result.append("Success");
		}
		System.out.println("Utils : Validator : validateEmail : end");
		return result.toString();
	}

	public static String validatePassword(String password) {
		System.out.println("Utils : Validator : validatePassword : start");
		if (password == null || password.isEmpty()) {
			return "Password must not be blank";
		}

		StringBuilder result = new StringBuilder();

		if (!(password.length() >= 8 && password.length() <= 12)) {
			result.append("Password needs to have 8 to 12 characters <br>");
		}

		if (!hasUppercase.matcher(password).find()) {
			result.append("Password needs an upper case <br>");
		}

		if (!hasLowercase.matcher(password).find()) {
			result.append("Password needs a lowercase <br>");
		}

		if (!hasNumber.matcher(password).find()) {
			result.append("Password needs a number <br>");
		}

		if (!hasSpecialChar.matcher(password).find()) {
			result.append("Password needs a special character i.e. !,@,#,&  <br>");
		}

		if (result.length() == 0) {
			result.append("Success");
		}
		System.out.println("Utils : Validator : validatePassword : end");
		return result.toString();
	}

	public static String validateContactNumber(String contactNo) {
		System.out.println("Utils : Validator : validateContact : start");
		if (contactNo == null || contactNo.isEmpty()) {
			return "Contact Number must not be blank";
		}

		StringBuilder result = new StringBuilder();

		if (contactNo.length() > 10) {
			result.append("Contact No. must contain only 10 digit <br>");
		}
		if (contactNo.length() < 10) {
			result.append("Contact No. must contain atleast 10 digit <br>");
		}

		if (!contactNo.matches("[0-9]+")) {
			result.append("Contact No. must contain only numbers <br>");
		}

		if (contactNo.charAt(0) == '0') {
			result.append("Contact No. must not start with zero <br>");
		}

		if (result.length() == 0) {
			result.append("Success");
		}
		System.out.println("Utils : Validator : validateContact : end");
		return result.toString();
	}

	public static String validateDate(String dateString, String datePattern) {
		System.out.println("Utils : Validator : validateDate : start");
		if (dateString == null || dateString.isEmpty()) {
			return "Date must not be blank";
		}

		SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
		sdf.setLenient(false);

		try {
			sdf.parse(dateString);
			System.out.println("Utils : Validator : validateDate : end");
			return "Success";
		} catch (ParseException e) {
			System.out.println("Utils : Validator : validateDate : end");
			return "Please Select a valid date";
		}

	}
	public static boolean isNumeric(String str)
	{
	    for (char c : str.toCharArray())
	    {
	        if (!Character.isDigit(c)) return false;
	    }
	    return true;
	}
}
