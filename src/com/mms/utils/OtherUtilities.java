package com.mms.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.mms.exceptions.MMSBusinessException;

public class OtherUtilities {
	public static int calculateAgeInYear(String dateOfBirth, String dateFormat)
			throws MMSBusinessException {
		System.out.println("Utils : OtherUtilities : calculateAge : start");
		int age;
		Date date = null;
		Date currentDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);

		try {
			date = sdf.parse(dateOfBirth);
		} catch (ParseException e) {
			throw new MMSBusinessException("Please Enter valid date");
		}

		Calendar now = Calendar.getInstance();
		Calendar dob = Calendar.getInstance();
		dob.setTime(date);
		now.setTime(currentDate);
		if (dob.after(now)) {
			age = -1;
		} else {
			int year2 = now.get(Calendar.YEAR);
			int year1 = dob.get(Calendar.YEAR);
			age = year2 - year1;
			int month2 = now.get(Calendar.MONTH);
			int month1 = dob.get(Calendar.MONTH);
			if (month1 > month2) {
				age--;
			} else if (month1 == month2) {
				int day2 = now.get(Calendar.DAY_OF_MONTH);
				int day1 = dob.get(Calendar.DAY_OF_MONTH);
				if (day1 > day2) {
					age--;
				}
			}
		}
		System.out.println("Utils : OtherUtilities : calculateAge : end");
		return age;
	}

	// added by Akashdeep Gupta
	public static String compareDate(String date1, String date2)
			throws MMSBusinessException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String result = "";
		try {
			Date d1 = sdf.parse(date1);
			Date d2 = sdf.parse(date2);
			int i = d1.compareTo(d2);
			if (i > 0) { // d1 is greater than d2
				result = "Success";
			}
			if (i == 0) { // d1 is equal to d2
				result = "Invalid dates";
			}
			if (i < 0) { // d1 is less than d2
				result = "Invalid dates";
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			MMSBusinessException be = new MMSBusinessException();
			throw be;

		}
		return result;
	}
}
