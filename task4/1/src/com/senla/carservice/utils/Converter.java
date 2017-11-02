package com.senla.carservice.utils;

import java.util.Arrays;

import com.senla.carservice.beans.Garage;
import com.senla.carservice.beans.Master;
import com.senla.carservice.beans.Order;
import com.senla.carservice.comparators.entity.IdComparator;
import com.senla.carservice.orderstate.OrderState;
import com.senla.carservice.repositories.GarageRepository;
import com.senla.carservice.repositories.MasterRepository;

public class Converter {
	public static String[] convertGaragesToStrings(Garage[] garages) {
		garages=(Garage[])ArrayWorker.cutNullEntities(garages);
		Arrays.sort(garages, new IdComparator());
		String[] strGarages = new String[garages.length];
		for (int i = 0; i < garages.length; i++) {
			strGarages[i] = convertGarageToString(garages[i]);
		}
		return strGarages;
	}

	public static Garage[] convertStringsToGarages(String[] strings) {
		Garage[] garages = new Garage[strings.length];
		for (int i = 0; i < strings.length; i++) {
			garages[i] = convertStringToGarage(strings[i]);
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
		if (str.equals("null"))
			return null;
		String[] garageComponents = str.split("/");
		return new Garage(Long.parseLong(garageComponents[0]), Boolean.parseBoolean(garageComponents[1]));
	}

	public static String[] convertMastersToStrings(Master[] masters) {
		masters=(Master[])ArrayWorker.cutNullEntities(masters);
		Arrays.sort(masters, new IdComparator());
		String[] strMasters = new String[masters.length];
		for (int i = 0; i < masters.length; i++) {
			strMasters[i] = convertMasterToString(masters[i]);
		}
		return strMasters;
	}

	public static Master[] convertStringsToMasters(String[] strings) {
		Master[] masters = new Master[strings.length];
		for (int i = 0; i < strings.length; i++) {
			masters[i] = convertStringToMaster(strings[i]);
		}
		return masters;
	}

	private static String convertMasterToString(Master master) {
		if (master == null)
			return null;
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

	public static String[] convertOrdersToStrings(Order[] orders) {
		orders=(Order[])ArrayWorker.cutNullEntities(orders);
		Arrays.sort(orders, new IdComparator());
		String[] strOrders = new String[orders.length];
		for (int i = 0; i < orders.length; i++) {
			strOrders[i] = convertOrderToString((orders[i]));
		}
		return strOrders;
	}

	public static Order[] convertStringsToOrders(String[] strings) {
		Order[] orders = new Order[strings.length];
		for (int i = 0; i < strings.length; i++) {
			orders[i] = convertStringToOrder((strings[i]));
		}
		return orders;
	}

	private static String convertOrderToString(Order order) {
		if (order == null)
			return null;
		StringBuilder str = new StringBuilder();
		str.append(order.getId()).append('/').append(DateWorker.format(order.getSubmissionDate())).append('/')
				.append(DateWorker.format(order.getExecutionDate())).append('/')
				.append(DateWorker.format(order.getPlannedStartDate())).append('/').append(order.getPrice()).append('/')
				.append(order.getState()).append('/').append(order.getGarage().getId()).append('/')
				.append(order.getMaster() == null ? null : order.getMaster().getId());
		return str.toString();
	}

	private static Order convertStringToOrder(String str) {
		if (str.equals("null"))
			return null;
		String[] orderComponents = str.split("/");
		return new Order(Long.parseLong(orderComponents[0]), DateWorker.parse(orderComponents[1]),
				DateWorker.parse(orderComponents[2]), DateWorker.parse(orderComponents[3]),
				Double.parseDouble(orderComponents[4]), OrderState.valueOf(orderComponents[5]),
				GarageRepository.getGarage(Long.parseLong(orderComponents[6])), orderComponents[7].equals("null") ? null
						: MasterRepository.getMaster(Long.parseLong(orderComponents[7])));

	}

}
