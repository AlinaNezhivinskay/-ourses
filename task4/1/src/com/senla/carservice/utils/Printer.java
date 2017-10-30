package com.senla.carservice.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.senla.carservice.beans.Garage;
import com.senla.carservice.beans.Master;
import com.senla.carservice.beans.Order;

public class Printer {

	public static void print(Garage garage) {
		System.out.println(garage);
	}

	public static void print(Garage[] garages) {
		for (Garage garage : garages) {
			System.out.println(garage);
		}
	}

	public static void print(Master master) {
		System.out.println(master);
	}

	public static void print(Master[] masters) {
		for (Master master : masters) {
			System.out.println(master);
		}
	}

	public static void print(Order order) {
		System.out.println(order);
	}

	public static void print(Order[] orders) {
		for (Order order : orders) {
			System.out.println(order);
		}
	}

	public static void print(String message) {
		System.out.println(message);
	}

	public static void print(Date date) {
		SimpleDateFormat d = new SimpleDateFormat("dd.MM.yyyy");
		System.out.println(d.format(date));
	}

	public static void print(int number) {
		System.out.println(number);
	}

	public static void print(double number) {
		System.out.println(number);
	}
}
