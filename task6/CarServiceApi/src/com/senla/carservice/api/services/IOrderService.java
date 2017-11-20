package com.senla.carservice.api.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.model.orderstate.OrderState;

public interface IOrderService {
	Order getOrderById(long id);

	void addOrder(Order order);

	boolean removeOrder(Order order);

	boolean updateOrder(Order order);

	boolean closeOrder(Order order);

	boolean cancelOrder(Order order);

	void shiftExecution(int daysNum);

	Order getOrderByMaster(Master master);

	Master getMasterByOrder(Order order);

	List<Order> getOrders();

	List<Order> getCurrentExecutingOrders();

	List<Order> getOrders(OrderState state, Date startTimePeriod, Date endTimePeriod);

	void sort(Comparator<Order> comparator);

	void sort(Comparator<Order> comparator, List<Order> orders);

	int getFreeMasterNumber(Date date);

	int getFreeGarageNumber(Date date);

	boolean assignMasterToOrder(Order order, Master master);

	Order getCopy(Order order) throws CloneNotSupportedException;

	boolean updateOrderState(Order order, OrderState state);

	boolean assignGarageToOrder(Order order, Garage garage);

	boolean exportOrders(String filePath, List<Order> orders) throws IOException;

	boolean importOrders(String filePath) throws FileNotFoundException, IOException, ParseException;

	void restoreData(List<Order> orders);
}
