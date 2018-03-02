package com.senla.carservice.util.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.model.beans.abstractentity.Entity;

public class Printer {

	public static void print(Entity entity) {
		System.out.println(entity);
	}

	public static void print(Garage garage) {
		System.out.println(garage);
	}

	public static void print(List<? extends Entity> entities) {
		for (Entity entity : entities) {
			print(entity);
		}
	}

	public static void print(Master master) {
		System.out.println(master);
	}

	public static void print(Order order) {
		System.out.println(order);
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
