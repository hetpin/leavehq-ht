package com.leave.leaveapp.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	private final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static long convertDateToLong(String date) {
		Long time = 0L;
		try {
			SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
			Date d = null;
			try {
				d = df.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			time = d.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	public static String convertIntToDat(int day) {
		String result = "Monday";
		switch (day) {
		case 2:
			result = "Mon";
			break;
		case 3:
			result = "Tue";
			break;
		case 4:
			result = "Wed";
			break;
		case 5:
			result = "Thu";
			break;
		case 6:
			result = "Fri";
			break;
		case 7:
			result = "Sat";
			break;
		case 1:
			result = "Sun";
			break;

		default:
			break;
		}
		return result;
	}

}
