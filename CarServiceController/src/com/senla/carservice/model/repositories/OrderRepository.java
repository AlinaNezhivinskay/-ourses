package com.senla.carservice.model.repositories;

import java.text.ParseException;
import java.util.ArrayList;

import com.danco.training.TextFileWorker;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.model.orderstate.OrderState;
import com.senla.carservice.model.utils.Converter;

public class OrderRepository {
	private TextFileWorker orderFileWoker;
	private static long lastId = 0;
	private ArrayList<Order> orders;
	private static OrderRepository instance;

	public static OrderRepository getInstance(String fileName) {
		if (instance == null) {
			instance = new OrderRepository(fileName);
		}
		return instance;
	}

	public OrderRepository(String fileName) {
		if (fileName != null) {
			orderFileWoker = new TextFileWorker(fileName);
		} else {
			orderFileWoker = new TextFileWorker("orders.txt");
		}
		orders = new ArrayList<Order>();
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public Order getOrder(long id) {
		for (Order order : orders) {
			if (order.getId() == id)
				return order;
		}
		return null;
	}

	public void addOrder(Order order) {
		order.setId(lastId);
		incrementLastId();
		orders.add(order);

	}

	public boolean removeOrder(Order order) {
		return orders.remove(order);
	}

	public boolean updateOrder(Order order, OrderState state) {
		if (!orders.contains(order))
			return false;
		orders.get(orders.indexOf(order)).setState(state);
		return true;
	}

	public void safeToFile() {
		orderFileWoker.writeToFile(Converter.convertOrdersToStrings(orders));
	}

	public void readFromFile() throws ParseException {
		orders = Converter.convertStringsToOrders(orderFileWoker.readFromFile());
		lastId = orders.get(orders.size() - 1).getId();
		incrementLastId();
	}

	private void incrementLastId() {
		lastId += 10;
	}

}
