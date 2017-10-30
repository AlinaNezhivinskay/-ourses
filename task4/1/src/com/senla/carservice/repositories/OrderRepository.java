package com.senla.carservice.repositories;

import com.danco.training.TextFileWorker;
import com.senla.carservice.beans.Order;
import com.senla.carservice.orderstate.OrderState;
import com.senla.carservice.utils.ArrayWorker;
import com.senla.carservice.utils.Converter;

public class OrderRepository {
	private static final TextFileWorker ORDER_FILE_WORKER = new TextFileWorker(
			"E:/учёба Алины/Courses/task4/1/orders.txt");
	private static long lastId = 0;
	private Order[] orders;

	public OrderRepository() {
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
			orders = ArrayWorker.expandArray(orders);
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

	public boolean updateOrder(Order order) {
		if (!ArrayWorker.isElementOnArray(orders, order))
			return false;
		orders[ArrayWorker.getPositionOfElement(orders, order)] = order;
		return true;
	}

	public void safeToFile() {
		ORDER_FILE_WORKER.writeToFile(Converter.convertOrdersToStrings(orders));
	}

	public void readFromFile() {
		orders = Converter.convertStringsToOrders(ORDER_FILE_WORKER.readFromFile());
	}

	private void incrementLastId() {
		lastId += 10;
	}

}
