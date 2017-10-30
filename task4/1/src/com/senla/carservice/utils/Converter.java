package com.senla.carservice.utils;


import com.senla.carservice.beans.Garage;
import com.senla.carservice.beans.HistoryOrder;
import com.senla.carservice.beans.Master;
import com.senla.carservice.beans.Order;
import com.senla.carservice.orderstate.OrderState;

public class Converter {
	public static String[] convertGaragesToStrings(Garage[] garages) {
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
		str.append(master.getId()).append('/').append(master.getName());
		return str.toString();
	}

	private static Master convertStringToMaster(String str) {
		if (str.equals("null"))
			return null;
		String[] masterComponents = str.split("/");
		return new Master(Long.parseLong(masterComponents[0]), masterComponents[1]);
	}

	public static String[] convertOrdersToStrings(Order[] orders) {
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
				.append(order.getGarage().getIsFree());
		return str.toString();
	}

	private static Order convertStringToOrder(String str) {
		if (str.equals("null"))
			return null;
		String[] orderComponents = str.split("/");

		return new Order(Long.parseLong(orderComponents[0]), DateWorker.parse(orderComponents[1]),
				DateWorker.parse(orderComponents[2]), DateWorker.parse(orderComponents[3]),
				Double.parseDouble(orderComponents[4]), OrderState.valueOf(orderComponents[5]),
				new Garage(Long.parseLong(orderComponents[6]), Boolean.parseBoolean(orderComponents[7])));

	}

	public static String[] convertHisoryOrdersToStrings(HistoryOrder[] historyOrders) {
		String[] strHistoryOrders = new String[historyOrders.length];
		for (int i = 0; i < historyOrders.length; i++) {
			strHistoryOrders[i] = convertHistoryOrderToString((historyOrders[i]));
		}
		return strHistoryOrders;
	}

	public static HistoryOrder[] convertStringsToHistoryOrders(String[] strings) {
		HistoryOrder[] hystoryOrders = new HistoryOrder[strings.length];
		for (int i = 0; i < strings.length; i++) {
			hystoryOrders[i] = convertStringToHistoryOrder((strings[i]));
		}
		return hystoryOrders;
	}

	private static String convertHistoryOrderToString(HistoryOrder historyOrder) {
		if (historyOrder == null)
			return null;
		StringBuilder str = new StringBuilder();
		str.append(historyOrder.getOrder()).append('/').append(convertOrderToString(historyOrder.getOrder()))
				.append('/').append(convertMasterToString(historyOrder.getMaster()));
		return str.toString();
	}

	private static HistoryOrder convertStringToHistoryOrder(String str) {
		if (str.equals("null"))
			return null;
		String[] historyOrderComponents = str.split("/");
		return new HistoryOrder(Long.parseLong(historyOrderComponents[0]),
				new Order(Long.parseLong(historyOrderComponents[1]), DateWorker.parse(historyOrderComponents[2]),
						DateWorker.parse(historyOrderComponents[3]), DateWorker.parse(historyOrderComponents[4]),
						Double.parseDouble(historyOrderComponents[5]), OrderState.valueOf(historyOrderComponents[6]),
						new Garage(Long.parseLong(historyOrderComponents[7]),
								Boolean.parseBoolean(historyOrderComponents[8]))),
				new Master(Long.parseLong(historyOrderComponents[9]), historyOrderComponents[10]));

	}
}
