package com.senla.carservice.util.utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.model.orderstate.OrderState;

public class Converter {
	public static String[] convertGaragesToStrings(List<Garage> garages) {
		String[] strGarages = new String[garages.size()];
		for (int i = 0; i < garages.size(); i++) {
			strGarages[i] = convertGarageToString(garages.toArray(new Garage[garages.size()])[i]);
		}
		return strGarages;
	}

	public static List<Garage> convertStringsToGarages(String[] strings) {
		List<Garage> garages = new ArrayList<Garage>(strings.length);
		for (int i = 0; i < strings.length; i++) {
			garages.add(convertStringToGarage(strings[i]));
		}
		return garages;
	}

	private static String convertGarageToString(Garage garage) {
		if (garage == null)
			return null;
		StringBuilder str = new StringBuilder();
		str.append(garage.getId()).append('/').append(garage.getIsFree());
		return str.toString();
	}

	private static Garage convertStringToGarage(String str) {
		String[] garageComponents = str.split("/");
		return new Garage(Long.parseLong(garageComponents[0]), Boolean.parseBoolean(garageComponents[1]));
	}

	public static String[] convertMastersToStrings(List<Master> masters) {
		String[] strMasters = new String[masters.size()];
		for (int i = 0; i < masters.size(); i++) {
			strMasters[i] = convertMasterToString((masters.toArray(new Master[masters.size()]))[i]);
		}
		return strMasters;
	}

	public static List<Master> convertStringsToMasters(String[] strings) {
		List<Master> masters = new ArrayList<Master>(strings.length);
		for (int i = 0; i < strings.length; i++) {
			masters.add(convertStringToMaster(strings[i]));
		}
		return masters;
	}

	private static String convertMasterToString(Master master) {
		StringBuilder str = new StringBuilder();
		str.append(master.getId()).append('/').append(master.getName()).append('/').append(master.getIsFree());
		return str.toString();
	}

	private static Master convertStringToMaster(String str) {
		if (str.equals("null"))
			return null;
		String[] masterComponents = str.split("/");
		return new Master(Long.parseLong(masterComponents[0]), masterComponents[1],
				Boolean.parseBoolean(masterComponents[2]));
	}

	public static String[] convertOrdersToStrings(List<Order> orders) {
		String[] strOrders = new String[orders.size()];
		for (int i = 0; i < orders.size(); i++) {
			strOrders[i] = convertOrderToString(((orders.toArray(new Order[orders.size()]))[i]));
		}
		return strOrders;
	}

	public static List<Order> convertStringsToOrders(String[] strings, List<Garage> garages, List<Master> masters)
			throws ParseException {
		List<Order> orders = new ArrayList<Order>(strings.length);
		for (int i = 0; i < strings.length; i++) {
			orders.add(convertStringToOrder(strings[i], garages, masters));
		}
		return orders;
	}

	private static String convertOrderToString(Order order) {
		StringBuilder str = new StringBuilder();
		str.append(order.getId()).append('/').append(DateWorker.format(order.getSubmissionDate())).append('/')
				.append(DateWorker.format(order.getExecutionDate())).append('/')
				.append(DateWorker.format(order.getPlannedStartDate())).append('/').append(order.getPrice()).append('/')
				.append(order.getState()).append('/').append(order.getGarage().getId()).append('/')
				.append(order.getMaster() == null ? null : order.getMaster().getId());
		return str.toString();
	}

	private static Order convertStringToOrder(String str, List<Garage> garages, List<Master> masters)
			throws ParseException {
		String[] orderComponents = str.split("/");
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
