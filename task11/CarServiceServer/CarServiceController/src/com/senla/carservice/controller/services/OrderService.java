package com.senla.carservice.controller.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.senla.carservice.api.dao.IOrderDAO;
import com.senla.carservice.api.services.IOrderService;
import com.senla.carservice.csv.fileworker.CsvFileWorker;
import com.senla.carservice.dao.OrderDAO;
import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.model.orderstate.OrderState;
import com.senla.carservice.model.sortfields.order.SortOrderFields;
import com.senla.carservice.util.utils.DateWorker;

public class OrderService implements IOrderService {
	private IOrderDAO orderDAO;

	public OrderService() {
		this.orderDAO = OrderDAO.getInstance();
	}

	@Override
	public synchronized void addOrder(Order order) throws SQLException {
		orderDAO.create(order);
	}

	@Override
	public synchronized boolean removeOrder(Order order) throws SQLException {
		return orderDAO.updateOrderState(order, OrderState.REMOTE);
	}

	@Override
	public synchronized boolean closeOrder(Order order) throws SQLException {
		return orderDAO.updateOrderState(order, OrderState.EXECUTED);
	}

	@Override
	public synchronized boolean cancelOrder(Order order) throws SQLException {
		return orderDAO.updateOrderState(order, OrderState.CANCELED);
	}

	/*
	 * private boolean changeOrderState(Order order, OrderState state) { List<Order>
	 * orders = orderRepository.getOrders(); if (!orders.contains(order)) return
	 * false; orders.get(orders.indexOf(order)).getGarage().setIsFree(true); if
	 * (orders.get(orders.indexOf(order)).getMaster() != null) {
	 * orders.get(orders.indexOf(order)).getMaster().setIsFree(true); }
	 * orders.get(orders.indexOf(order)).setExecutionDate(new Date());
	 * orderRepository.updateOrderState(order, state); return true; }
	 */

	@Override
	public synchronized void shiftExecution(int daysNum) throws SQLException {
		List<Order> orders = orderDAO.getAll();
		for (Order order : orders) {
			order.setExecutionDate(DateWorker.shiftDate(order.getExecutionDate(), daysNum));
			orderDAO.update(order);
		}
	}

	@Override
	public List<Order> getOrders() throws SQLException {
		return orderDAO.getAll();
	}

	@Override
	public List<Order> getCurrentExecutingOrders() throws SQLException {
		return orderDAO.getOrdersByState(OrderState.EXECUTABLE);
	}

	@Override
	public List<Order> getOrders(OrderState state, Date startTimePeriod, Date endTimePeriod) throws SQLException {
		return orderDAO.getOrdersByStateAndPeriod(state, startTimePeriod, endTimePeriod);
	}

	@Override
	public List<Order> sort(SortOrderFields sortField) throws SQLException {
		return orderDAO.getAll(sortField, false);
	}

	@Override
	public Order getOrderByMaster(Master master) throws SQLException {
		return orderDAO.getOrderByMaster(master);

	}

	@Override
	public Master getMasterByOrder(Order order) throws SQLException {
		return orderDAO.getMasterByOrder(order);
	}

	@Override
	public int getFreeGarageNumber(Date date) throws SQLException {
		return orderDAO.getFreeGarageNumber(date);
	}

	@Override
	public int getFreeMasterNumber(Date date) throws SQLException {
		return orderDAO.getFreeMasterNumber(date);
	}

	@Override
	public Order getOrderById(Long id) throws SQLException {
		return orderDAO.read(id);
	}

	@Override
	public synchronized boolean assignMasterToOrder(Order order, Master master) throws SQLException {
		return orderDAO.assignMasterToOrder(order, master);
	}

	@Override
	public synchronized boolean assignGarageToOrder(Order order, Garage garage) throws SQLException {
		return orderDAO.assignGarageToOrder(order, garage);
	}

	@Override
	public Order getCopy(Order order) throws CloneNotSupportedException {
		return order.clone();
	}

	@Override
	public synchronized boolean updateOrder(Order order) throws SQLException {
		return orderDAO.update(order);
	}

	@Override
	public boolean exportOrders(List<Order> orders) throws IllegalAccessException, IOException, NoSuchFieldException {
		return CsvFileWorker.safeToFile(orders);
	}

	@Override
	public synchronized boolean importOrders() throws FileNotFoundException, IOException, IllegalAccessException,
			NoSuchFieldException, InstantiationException, InvocationTargetException, NoSuchMethodException,
			ClassNotFoundException, SQLException {
		@SuppressWarnings("unchecked")
		List<Order> orders = (List<Order>) CsvFileWorker.readFromFileOrder();
		StringBuilder idStr = new StringBuilder();
		for (Order order : orders) {
			idStr.append(order.getId()).append(", ");
		}
		idStr.append(-1);
		List<Long> existingId = orderDAO.getExistingId(idStr.toString());
		for (Order order : orders) {
			if (existingId.contains((Long) order.getId())) {
				orderDAO.update(order);
			} else {
				orderDAO.create(order);
			}
		}
		return true;
	}

}
