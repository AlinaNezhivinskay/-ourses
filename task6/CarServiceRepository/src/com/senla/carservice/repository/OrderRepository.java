package com.senla.carservice.repository;

import java.util.ArrayList;
import java.util.List;

import com.senla.carservice.api.repositories.IOrderRepository;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.model.orderstate.OrderState;

public class OrderRepository implements IOrderRepository {
	private static OrderRepository instance;
	private static long lastId = 0;
	private List<Order> orders;

	public static OrderRepository getInstance() {
		if (instance == null) {
			instance = new OrderRepository();
		}
		return instance;
	}

	private OrderRepository() {
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
	public boolean addOrder(Order order) {
		if (orders.contains(order)) {
			this.updateOrder(order);
			return true;
		}
		order.setId(lastId);
		incrementLastId();
		return orders.add(order);

	}

	@Override
	public boolean removeOrder(Order order) {
		return orders.remove(order);
	}

	@Override
	public boolean updateOrder(Order order) {
		if (!orders.contains(order))
			return false;
		Order repositoryOrder = orders.get(orders.indexOf(order));
		repositoryOrder.setExecutionDate(order.getExecutionDate());
		repositoryOrder.setGarage(order.getGarage());
		repositoryOrder.setMaster(order.getMaster());
		repositoryOrder.setPlannedStartDate(order.getPlannedStartDate());
		repositoryOrder.setPrice(order.getPrice());
		repositoryOrder.setState(order.getState());
		repositoryOrder.setSubmissionDate(order.getSubmissionDate());
		return true;
	}

	@Override
	public boolean updateOrderState(Order order, OrderState state) {
		if (!orders.contains(order))
			return false;
		orders.get(orders.indexOf(order)).setState(state);
		return true;
	}

	@Override
	public void restoreData(List<Order> orders) {
		this.orders = orders;
		lastId = orders.get(orders.size() - 1).getId();
		incrementLastId();
	}

	private void incrementLastId() {
		lastId += 10;
	}

}
