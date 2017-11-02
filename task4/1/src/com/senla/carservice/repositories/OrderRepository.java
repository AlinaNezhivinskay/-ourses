package com.senla.carservice.repositories;

import com.danco.training.TextFileWorker;
import com.senla.carservice.beans.Order;
import com.senla.carservice.orderstate.OrderState;
import com.senla.carservice.utils.ArrayWorker;
import com.senla.carservice.utils.Converter;

public class OrderRepository {
	private TextFileWorker orderFileWoker;
	private static long lastId = 0;
	private Order[] orders;

	public OrderRepository(String fileName) {
		if (fileName != null) {
			orderFileWoker = new TextFileWorker(fileName);
		} else {
			orderFileWoker = new TextFileWorker("E:/учёба Алины/Courses/task4/1/orders.txt");
		}
		orders = new Order[ArrayWorker.ARRAY_LENGTH];
	}

	public Order[] getOrders() {
		return orders;
	}

	public Order getOrder(long id) {
		for (int i = 0; i < orders.length; i++) {
			if (orders[i].getId() == id)
				return orders[i];
		}
		return null;
	}

	public void addOrder(Order order) {
		if (!ArrayWorker.isEmptySpace(orders)) {
			orders = (Order[]) ArrayWorker.expandArray(orders);
		}
		order.setId(lastId);
		incrementLastId();
		orders[ArrayWorker.getFreePosition(orders)] = order;

	}

	public boolean removeOrder(Order order) {
		if (!ArrayWorker.isElementOnArray(orders, order))
			return false;
		orders[ArrayWorker.getPositionOfElement(orders, order)].setState(OrderState.REMOTE);
		return true;
	}

	public boolean updateOrder(Order order, OrderState state) {
		if (!ArrayWorker.isElementOnArray(orders, order))
			return false;
		orders[ArrayWorker.getPositionOfElement(orders, order)].setState(state);
		return true;
	}

	public void safeToFile() {
		orderFileWoker.writeToFile(Converter.convertOrdersToStrings(orders));
	}

	public void readFromFile() {
		orders = Converter.convertStringsToOrders(orderFileWoker.readFromFile());
		lastId = orders[orders.length - 1].getId();
		incrementLastId();
	}

	private void incrementLastId() {
		lastId += 10;
	}

}
