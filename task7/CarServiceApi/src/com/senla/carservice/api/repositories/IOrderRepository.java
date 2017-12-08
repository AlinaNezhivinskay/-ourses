package com.senla.carservice.api.repositories;

import java.util.List;

import com.senla.carservice.model.beans.Order;
import com.senla.carservice.model.orderstate.OrderState;

public interface IOrderRepository {
	List<Order> getOrders();

	Order getOrder(long id);

	boolean addOrder(Order order);

	boolean removeOrder(Order order);

	boolean updateOrderState(Order order, OrderState state);

	boolean updateOrder(Order order);

	void restoreData(List<Order> orders);
}
