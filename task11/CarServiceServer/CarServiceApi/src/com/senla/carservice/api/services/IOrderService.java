package com.senla.carservice.api.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.model.orderstate.OrderState;
import com.senla.carservice.model.sortfields.order.SortOrderFields;

public interface IOrderService {
	Order getOrderById(Long id) throws Exception;

	void addOrder(Order order) throws Exception;

	boolean removeOrder(Order order) throws Exception;

	boolean updateOrder(Order order) throws Exception;

	boolean closeOrder(Order order) throws Exception;

	boolean cancelOrder(Order order) throws Exception;

	void shiftExecution(int daysNum) throws Exception;

	Order getOrderByMaster(Master master) throws Exception;

	List<Order> getOrders() throws Exception;

	List<Order> getCurrentExecutingOrders() throws Exception;

	List<Order> getOrders(OrderState state, Date startTimePeriod, Date endTimePeriod) throws Exception;

	List<Order> sort(SortOrderFields sortField) throws Exception;

	int getFreeMasterNumber(Date date) throws Exception;

	int getFreeGarageNumber(Date date) throws Exception;

	boolean assignMasterToOrder(Order order, Master master) throws Exception;

	Order getCopy(Order order) throws CloneNotSupportedException;

	boolean assignGarageToOrder(Order order, Garage garage) throws Exception;

	boolean exportOrders(List<Order> orders) throws IllegalAccessException, IOException, NoSuchFieldException;

	boolean importOrders() throws FileNotFoundException, IOException, IllegalAccessException, NoSuchFieldException,
			InstantiationException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException, Exception;

	List<Order> sortOrders(OrderState state, SortOrderFields sortField) throws Exception;
}
