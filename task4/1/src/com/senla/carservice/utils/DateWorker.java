package com.senla.carservice.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateWorker {
	public static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
	public static final long MILLISEC_IN_DAY = 86400000;

	public static String format(Date date) {
		return DATE_FORMAT.format(date);
	}

	public static Date parse(String str) {
		try {
			return DATE_FORMAT.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Date shiftDate(Date date, int daysNum) {
		return new Date(date.getTime() + daysNum * MILLISEC_IN_DAY);
	}
}
