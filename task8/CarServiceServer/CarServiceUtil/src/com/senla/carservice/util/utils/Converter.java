package com.senla.carservice.util.utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.model.orderstate.OrderState;

public class Converter {
	private static final String SEPARATOR = ",";

	public static List<String> convertGaragesToStrings(List<Garage> garages) {
		List<String> strGarages = new ArrayList<String>();
		for (Garage garage : garages) {
			strGarages.add(convertGarageToString(garage));
		}
		return strGarages;
	}

	public static List<Garage> convertStringsToGarages(List<String> strings) {
		List<Garage> garages = new ArrayList<Garage>();
		for (String str : strings) {
			garages.add(convertStringToGarage(str));
		}
		return garages;
	}

	private static String convertGarageToString(Garage garage) {
		if (garage == null)
			return null;
		StringBuilder str = new StringBuilder();
		str.append(garage.getId()).append(SEPARATOR).append(garage.getIsFree());
		return str.toString();
	}

	private static Garage convertStringToGarage(String str) {
		String[] garageComponents = str.split(SEPARATOR);
		return new Garage(Long.parseLong(garageComponents[0]), Boolean.parseBoolean(garageComponents[1]));
	}

	public static List<String> convertMastersToStrings(List<Master> masters) {
		List<String> strMasters = new ArrayList<String>();
		for (Master master : masters) {
			strMasters.add(convertMasterToString(master));
		}
		return strMasters;
	}

	public static List<Master> convertStringsToMasters(List<String> strings) {
		List<Master> masters = new ArrayList<Master>();
		for (String str : strings) {
			masters.add(convertStringToMaster(str));
		}
		return masters;
	}

	private static String convertMasterToString(Master master) {
		StringBuilder str = new StringBuilder();
		str.append(master.getId()).append(SEPARATOR).append(master.getName()).append(SEPARATOR)
				.append(master.getIsFree());
		return str.toString();
	}

	private static Master convertStringToMaster(String str) {
		if (str.equals("null"))
			return null;
		String[] masterComponents = str.split(SEPARATOR);
		return new Master(Long.parseLong(masterComponents[0]), masterComponents[1],
				Boolean.parseBoolean(masterComponents[2]));
	}

	public static List<String> convertOrdersToStrings(List<Order> orders) {
		List<String> strOrders = new ArrayList<String>();
		for (Order order : orders) {
			strOrders.add(convertOrderToString(order));
		}
		return strOrders;
	}

	public static List<Order> convertStringsToOrders(List<String> strings, List<Garage> garages, List<Master> masters)
			throws ParseException {
		List<Order> orders = new ArrayList<Order>();
		for (String str : strings) {
			orders.add(convertStringToOrder(str, garages, masters));
		}
		return orders;
	}

	private static String convertOrderToString(Order order) {
		StringBuilder str = new StringBuilder();
		str.append(order.getId()).append(SEPARATOR).append(DateWorker.format(order.getSubmissionDate()))
				.append(SEPARATOR).append(DateWorker.format(order.getExecutionDate())).append(SEPARATOR)
				.append(DateWorker.format(order.getPlannedStartDate())).append(SEPARATOR).append(order.getPrice())
				.append(SEPARATOR).append(order.getState()).append(SEPARATOR).append(order.getGarage().getId())
				.append(SEPARATOR).append(order.getMaster() == null ? null : order.getMaster().getId());
		return str.toString();
	}

	private static Order convertStringToOrder(String str, List<Garage> garages, List<Master> masters)
			throws ParseException {
		String[] orderComponents = str.split(SEPARATOR);
		return new Order(Long.parseLong(orderComponents[0]), DateWorker.parse(orderComponents[1]),
				DateWorker.parse(orderComponents[2]), DateWorker.parse(orderComponents[3]),
				Double.parseDouble(orderComponents[4]), OrderState.valueOf(orderComponents[5]),
				getGarageById(garages, Long.parseLong(orderComponents[6])),
				orderComponents[7].equals("null") ? null : getMasterById(masters, Long.parseLong(orderComponents[7])));

	}

	private static Garage getGarageById(List<Garage> garages, long id) {
		for (Garage garage : garages) {
			if (garage.getId() == id)
				return garage;
		}
		return null;
	}

	private static Master getMasterById(List<Master> masters, long id) {
		for (Master master : masters) {
			if (master.getId() == id)
				return master;
		}
		return null;
	}

}
