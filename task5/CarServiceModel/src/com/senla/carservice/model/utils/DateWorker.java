package com.senla.carservice.model.utils;

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

	public static Date formatDate(Date date) throws ParseException {
		return parse(format(date));
	}

	public static Date parse(String str) throws ParseException {
		return DATE_FORMAT.parse(str);
	}

	public static Date shiftDate(Date date, int daysNum) {
		return new Date(date.getTime() + daysNum * MILLISEC_IN_DAY);
	}
}
