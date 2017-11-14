package com.senla.carservice.api.repositories;

import java.text.ParseException;
import java.util.List;

import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.model.orderstate.OrderState;
import com.senla.carservice.model.beans.Garage;

public interface IOrderRepository {
	public List<Order> getOrders();

	public Order getOrder(long id);

	public void addOrder(Order order);

	public boolean removeOrder(Order order);

	public boolean updateOrder(Order order, OrderState state);

	public void safeToFile() throws Exception;

	public void readFromFile(List<Garage> garages, List<Master> masters) throws ParseException, Exception;
}
