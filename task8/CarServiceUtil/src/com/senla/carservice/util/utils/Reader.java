package com.senla.carservice.util.utils;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

public class Reader {
	private static Scanner in = new Scanner(System.in);

	public static int readInt() {
		String str;
		Integer number = null;
		do {
			str = in.nextLine();
			try {
				number = Integer.parseInt(str);
			} catch (NumberFormatException ex) {

			}
		} while (number == null);
		return number;
	}

	public static double readDouble() {
		String str;
		Double number = null;
		do {
			str = in.nextLine();
			try {
				number = Double.parseDouble(str);
			} catch (NumberFormatException ex) {

			}
		} while (number == null);
		return number;
	}

	public static String readString() {
		return in.nextLine();
	}

	public static Date readDate() {
		String str;
		Date date = null;
		do {
			str = in.nextLine();
			try {
				date = DateWorker.parse(str);
			} catch (ParseException e) {

			}
		} while (date == null);
		return date;
	}

	public static String readFilePath() {
		String str;
		do {
			str = in.nextLine();
		} while (str == null);
		return str;
	}
}
