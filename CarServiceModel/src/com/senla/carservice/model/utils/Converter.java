package com.senla.carservice.model.utils;

import java.text.ParseException;
import java.util.ArrayList;

import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.model.comparators.entity.IdComparator;
import com.senla.carservice.model.orderstate.OrderState;
import com.senla.carservice.model.repositories.GarageRepository;
import com.senla.carservice.model.repositories.MasterRepository;

public class Converter {
	public static String[] convertGaragesToStrings(ArrayList<Garage> garages) {
		garages.sort(new IdComparator());
		String[] strGarages = new String[garages.size()];
		for (int i = 0; i < garages.size(); i++) {
			strGarages[i] = convertGarageToString(garages.toArray(new Garage[garages.size()])[i]);
		}
		return strGarages;
	}

	public static ArrayList<Garage> convertStringsToGarages(String[] strings) {
		ArrayList<Garage> garages = new ArrayList<Garage>(strings.length);
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

	public static String[] convertMastersToStrings(ArrayList<Master> masters) {
		masters.sort(new IdComparator());
		String[] strMasters = new String[masters.size()];
		for (int i = 0; i < masters.size(); i++) {
			strMasters[i] = convertMasterToString((masters.toArray(new Master[masters.size()]))[i]);
		}
		return strMasters;
	}

	public static ArrayList<Master> convertStringsToMasters(String[] strings) {
		ArrayList<Master> masters = new ArrayList<Master>(strings.length);
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

	public static String[] convertOrdersToStrings(ArrayList<Order> orders) {
		orders.sort(new IdComparator());
		String[] strOrders = new String[orders.size()];
		for (int i = 0; i < orders.size(); i++) {
			strOrders[i] = convertOrderToString(((orders.toArray(new Order[orders.size()]))[i]));
		}
		return strOrders;
	}

	public static ArrayList<Order> convertStringsToOrders(String[] strings)throws ParseException {
		ArrayList<Order> orders = new ArrayList<Order>(strings.length);
		for (int i = 0; i < strings.length; i++) {
			orders.add(convertStringToOrder((strings[i])));
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

	private static Order convertStringToOrder(String str) throws ParseException {
		if (str.equals("null"))
			return null;
		String[] orderComponents = str.split("/");
		return new Order(Long.parseLong(orderComponents[0]), DateWorker.parse(orderComponents[1]),
				DateWorker.parse(orderComponents[2]), DateWorker.parse(orderComponents[3]),
				Double.parseDouble(orderComponents[4]), OrderState.valueOf(orderComponents[5]),
				GarageRepository.getInstance(null).getGarage(Long.parseLong(orderComponents[6])),
				orderComponents[7].equals("null") ? null
						: MasterRepository.getInstance(null).getMaster(Long.parseLong(orderComponents[7])));

	}

}
