package com.senla.carservice.repository.repositories;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.danco.training.TextFileWorker;
import com.senla.carservice.api.repositories.IOrderRepository;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.model.orderstate.OrderState;
import com.senla.carservice.util.utils.Converter;

public class OrderRepository implements IOrderRepository {
	private TextFileWorker orderFileWoker;
	private static long lastId = 0;
	private List<Order> orders;

	public OrderRepository(String fileName) {
		if (fileName != null) {
			orderFileWoker = new TextFileWorker(fileName);
		} else {
			orderFileWoker = new TextFileWorker("orders.txt");
		}
		orders = new ArrayList<Order>();
	}

	@Override
	public List<Order> getOrders() {
		return orders;
	}

	@Override
	public Order getOrder(long id) {
		for (Order order : orders) {
			if (order.getId() == id)
				return order;
		}
		return null;
	}

	@Override
	public void addOrder(Order order) {
		order.setId(lastId);
		incrementLastId();
		orders.add(order);

	}

	@Override
	public boolean removeOrder(Order order) {
		return orders.remove(order);
	}

	@Override
	public boolean updateOrder(Order order, OrderState state) {
		if (!orders.contains(order))
			return false;
		orders.get(orders.indexOf(order)).setState(state);
		return true;
	}

	@Override
	public void safeToFile() {
		orderFileWoker.writeToFile(Converter.convertOrdersToStrings(orders));
	}

	@Override
	public void readFromFile(List<Garage> garages, List<Master> masters) throws ParseException {
		orders = Converter.convertStringsToOrders(orderFileWoker.readFromFile(), garages, masters);
		lastId = orders.get(orders.size() - 1).getId();
		incrementLastId();
	}

	private void incrementLastId() {
		lastId += 10;
	}

}
