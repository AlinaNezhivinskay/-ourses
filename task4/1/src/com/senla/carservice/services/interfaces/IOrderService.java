package com.senla.carservice.services.interfaces;

import java.util.Comparator;
import java.util.Date;

import com.senla.carservice.beans.Master;
import com.senla.carservice.beans.Order;
import com.senla.carservice.orderstate.OrderState;

public interface IOrderService {
	public void addOrder(Order order);

	public boolean removeOrder(Order order);

	public boolean updateOrder(Order order, OrderState state);

	public boolean closeOrder(Order order);

	public boolean cancelOrder(Order order);

	public void shiftExecution(int daysNum);

	public void safeToFile();

	public void readFromFile();

	public Order getOrderByMaster(Master master);

	public Master getMasterByOrder(Order order);

	public Order[] getOrders();

	public Order[] getCurrentExecutingOrders();

	public Order[] getOrders(OrderState state, Date startTimePeriod, Date endTimePeriod);

	public void sort(Comparator<Order> comparator);

	public void sort(Comparator<Order> comparator, Order[] orders);

	public int getFreeMasterNumber(Date date);

	public int getFreeGarageNumber(Date date);
}
