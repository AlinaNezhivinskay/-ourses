package com.senla.carservice.api.services;

import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.model.orderstate.OrderState;

public interface IOrderService {
	public Order getOrderById(long id);

	public void addOrder(Order order);

	public boolean removeOrder(Order order);

	public boolean updateOrder(Order order, OrderState state);

	public boolean closeOrder(Order order);

	public boolean cancelOrder(Order order);

	public void shiftExecution(int daysNum);

	public void safeToFile() throws Exception;

	public void readFromFile(List<Garage> garages, List<Master> masters) throws ParseException, Exception;

	public Order getOrderByMaster(Master master);

	public Master getMasterByOrder(Order order);

	public List<Order> getOrders();

	public List<Order> getCurrentExecutingOrders();

	public List<Order> getOrders(OrderState state, Date startTimePeriod, Date endTimePeriod);

	public void sort(Comparator<Order> comparator);

	public void sort(Comparator<Order> comparator, List<Order> orders);

	public int getFreeMasterNumber(Date date);

	public int getFreeGarageNumber(Date date);

	public void assignMasterToOrder(Order order, Master master);
}
