package com.senla.carservice.controller.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

import com.senla.carservice.api.dao.IOrderDAO;
import com.senla.carservice.api.services.IGarageService;
import com.senla.carservice.api.services.IMasterService;
import com.senla.carservice.api.services.IOrderService;
import com.senla.carservice.csv.fileworker.CsvFileWorker;
import com.senla.carservice.dao.OrderDAO;
import com.senla.carservice.jdbc.connection.DBConnector;
import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.model.orderstate.OrderState;
import com.senla.carservice.model.sortfields.order.SortOrderFields;
import com.senla.carservice.util.utils.DateWorker;

public class OrderService implements IOrderService {
	private IOrderDAO orderDAO;
	private final DBConnector dBconnector = DBConnector.getInstance();

	public OrderService() {
		this.orderDAO = new OrderDAO();
	}

	@Override
	public synchronized void addOrder(Order order) throws Exception {
		orderDAO.create(dBconnector.getConnection(), order);
	}

	@Override
	public synchronized boolean removeOrder(Order order) throws Exception {
		return updateOrderState(order, OrderState.REMOTE);
	}

	@Override
	public synchronized boolean closeOrder(Order order) throws Exception {
		return updateOrderState(order, OrderState.EXECUTED);

	}

	@Override
	public synchronized boolean cancelOrder(Order order) throws Exception {
		return updateOrderState(order, OrderState.CANCELED);
	}

	private boolean updateOrderState(Order order, OrderState state) throws Exception {
		order.setState(state);
		IGarageService garageService = new GarageService();
		IMasterService masterService = new MasterService();
		Connection connection = dBconnector.getConnection();
		try {
			boolean stateUpdate = false;
			connection.setAutoCommit(false);
			if (order != null) {
				boolean orderUpdate = orderDAO.update(connection, order);
				Garage garage = order.getGarage();
				boolean garageUpdate = garageService.updateGarage(garage, true);
				Master master = order.getMaster();
				boolean masterUpdate = true;
				if (master != null) {
					masterUpdate = masterService.updateMaster(master, true);
				}
				connection.commit();
				if (orderUpdate && garageUpdate && masterUpdate) {
					stateUpdate = true;
				}
			}
			connection.setAutoCommit(true);
			return stateUpdate;

		} catch (Exception e) {
			connection.rollback();
			throw e;
		}
	}

	@Override
	public synchronized void shiftExecution(int daysNum) throws Exception {
		List<Order> orders = orderDAO.getAll(dBconnector.getConnection(), null);
		for (Order order : orders) {
			order.setExecutionDate(DateWorker.shiftDate(order.getExecutionDate(), daysNum));
			orderDAO.update(dBconnector.getConnection(), order);
		}
	}

	@Override
	public List<Order> getOrders() throws Exception {
		return orderDAO.getAll(dBconnector.getConnection(), null);
	}

	@Override
	public List<Order> getCurrentExecutingOrders() throws Exception {
		return orderDAO.getOrdersByState(dBconnector.getConnection(), OrderState.EXECUTABLE, null);
	}

	@Override
	public List<Order> getOrders(OrderState state, Date startTimePeriod, Date endTimePeriod) throws Exception {
		return orderDAO.getOrdersByStateAndPeriod(dBconnector.getConnection(), state, startTimePeriod, endTimePeriod);
	}

	@Override
	public List<Order> sort(SortOrderFields sortField) throws Exception {
		return orderDAO.getAll(dBconnector.getConnection(), sortField.name());
	}

	@Override
	public List<Order> sortOrders(OrderState state, SortOrderFields sortField) throws Exception {
		return orderDAO.getOrdersByState(dBconnector.getConnection(), state, sortField.name());
	}

	@Override
	public Order getOrderByMaster(Master master) throws Exception {
		return orderDAO.getOrderByMaster(dBconnector.getConnection(), master);

	}

	@Override
	public int getFreeGarageNumber(Date date) throws Exception {
		return orderDAO.getExecutedOnDateOrdersNum(dBconnector.getConnection(), date);
	}

	@Override
	public int getFreeMasterNumber(Date date) throws Exception {
		return orderDAO.getExecutedOnDateOrdersNum(dBconnector.getConnection(), date);
	}

	@Override
	public Order getOrderById(Long id) throws Exception {
		return orderDAO.read(dBconnector.getConnection(), id);
	}

	@Override
	public synchronized boolean assignMasterToOrder(Order order, Master master) throws Exception {
		order.setMaster(master);
		IMasterService masterService = new MasterService();
		Connection connection = dBconnector.getConnection();
		try {
			connection.setAutoCommit(false);
			boolean stateUpdate = orderDAO.update(connection, order);
			masterService.updateMaster(master, false);
			connection.commit();
			connection.setAutoCommit(true);
			return stateUpdate;

		} catch (Exception e) {
			connection.rollback();
			throw e;
		}
	}

	@Override
	public synchronized boolean assignGarageToOrder(Order order, Garage garage) throws Exception {
		order.setGarage(garage);
		IGarageService garageService = new GarageService();
		Connection connection = dBconnector.getConnection();
		try {
			connection.setAutoCommit(false);
			boolean stateUpdate = orderDAO.update(connection, order);
			garageService.updateGarage(garage, false);
			connection.commit();
			connection.setAutoCommit(true);
			return stateUpdate;

		} catch (Exception e) {
			connection.rollback();
			throw e;
		}
	}

	@Override
	public Order getCopy(Order order) throws CloneNotSupportedException {
		return order.clone();
	}

	@Override
	public synchronized boolean updateOrder(Order order) throws Exception {
		return orderDAO.update(dBconnector.getConnection(), order);
	}

	@Override
	public boolean exportOrders(List<Order> orders) throws IllegalAccessException, IOException, NoSuchFieldException {
		return CsvFileWorker.safeToFile(orders);
	}

	@Override
	public synchronized boolean importOrders() throws FileNotFoundException, IOException, IllegalAccessException,
			NoSuchFieldException, InstantiationException, InvocationTargetException, NoSuchMethodException,
			ClassNotFoundException, Exception {
		@SuppressWarnings("unchecked")
		List<Order> orders = (List<Order>) CsvFileWorker.readFromFileOrder();
		List<Order> dbOrders = orderDAO.getAll(dBconnector.getConnection(), null);
		for (Order order : orders) {
			if (dbOrders.contains(order)) {
				orderDAO.update(dBconnector.getConnection(), order);
			} else {
				orderDAO.create(dBconnector.getConnection(), order);
			}
		}
		return true;
	}

}
