package com.senla.carservice.api.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.model.orderstate.OrderState;
import com.senla.carservice.model.sortfields.order.SortOrderFields;

public interface IOrderService {
	Order getOrderById(Long id) throws SQLException;

	void addOrder(Order order) throws SQLException;

	boolean removeOrder(Order order) throws SQLException;

	boolean updateOrder(Order order) throws SQLException;

	boolean closeOrder(Order order) throws SQLException;

	boolean cancelOrder(Order order) throws SQLException;

	void shiftExecution(int daysNum) throws SQLException;

	Order getOrderByMaster(Master master) throws SQLException;

	Master getMasterByOrder(Order order) throws SQLException;

	List<Order> getOrders() throws SQLException;

	List<Order> getCurrentExecutingOrders() throws SQLException;

	List<Order> getOrders(OrderState state, Date startTimePeriod, Date endTimePeriod) throws SQLException;

	List<Order> sort(SortOrderFields sortField) throws SQLException;

	int getFreeMasterNumber(Date date) throws SQLException;

	int getFreeGarageNumber(Date date) throws SQLException;

	boolean assignMasterToOrder(Order order, Master master) throws SQLException;

	Order getCopy(Order order) throws CloneNotSupportedException;

	boolean assignGarageToOrder(Order order, Garage garage) throws SQLException;

	boolean exportOrders(List<Order> orders) throws IllegalAccessException, IOException, NoSuchFieldException;

	boolean importOrders() throws FileNotFoundException, IOException, IllegalAccessException, NoSuchFieldException,
			InstantiationException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException,
			SQLException;
}
