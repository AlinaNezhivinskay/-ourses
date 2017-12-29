package com.senla.carservice.controller.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.senla.carservice.api.repositories.IOrderRepository;
import com.senla.carservice.api.services.IOrderService;
import com.senla.carservice.csv.fileworker.CsvFileWorker;
import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.model.orderstate.OrderState;
import com.senla.carservice.repository.OrderRepository;
import com.senla.carservice.util.utils.DateWorker;

public class OrderService implements IOrderService {
	private IOrderRepository orderRepository;

	public OrderService() {
		this.orderRepository = OrderRepository.getInstance();
	}

	@Override
	public void addOrder(Order order) {
		orderRepository.addOrder(order);
	}

	@Override
	public boolean removeOrder(Order order) {
		return changeOrderState(order, OrderState.REMOTE);
	}

	@Override
	public boolean updateOrderState(Order order, OrderState state) {
		return orderRepository.updateOrderState(order, state);
	}

	@Override
	public boolean closeOrder(Order order) {
		return changeOrderState(order, OrderState.EXECUTED);
	}

	@Override
	public boolean cancelOrder(Order order) {
		return changeOrderState(order, OrderState.CANCELED);
	}

	private boolean changeOrderState(Order order, OrderState state) {
		List<Order> orders = orderRepository.getOrders();
		if (!orders.contains(order))
			return false;
		orders.get(orders.indexOf(order)).getGarage().setIsFree(true);
		if (orders.get(orders.indexOf(order)).getMaster() != null) {
			orders.get(orders.indexOf(order)).getMaster().setIsFree(true);
		}
		orders.get(orders.indexOf(order)).setExecutionDate(new Date());
		orderRepository.updateOrderState(order, state);
		return true;
	}

	@Override
	public void shiftExecution(int daysNum) {
		for (Order order : orderRepository.getOrders()) {
			order.setExecutionDate(DateWorker.shiftDate(order.getExecutionDate(), daysNum));
		}
	}

	@Override
	public List<Order> getOrders() {
		return orderRepository.getOrders();
	}

	@Override
	public List<Order> getCurrentExecutingOrders() {
		List<Order> executingOrders = new ArrayList<Order>();
		for (Order order : orderRepository.getOrders()) {
			if (order.getState().equals(OrderState.EXECUTABLE)) {
				executingOrders.add(order);
			}
		}
		return executingOrders;
	}

	@Override
	public List<Order> getOrders(OrderState state, Date startTimePeriod, Date endTimePeriod) {
		List<Order> orders = new ArrayList<Order>();
		for (Order order : orderRepository.getOrders()) {
			if (order.getState().equals(state) && startTimePeriod.before(order.getSubmissionDate())
					&& endTimePeriod.after(order.getExecutionDate())) {
				orders.add(order);
			}
		}
		return orders;
	}

	@Override
	public void sort(Comparator<Order> comparator) {
		orderRepository.getOrders().sort(comparator);
	}

	@Override
	public void sort(Comparator<Order> comparator, List<Order> orders) {
		orders.sort(comparator);
	}

	@Override
	public void restoreData(List<Order> orders) {
		orderRepository.restoreData(orders);
	}

	@Override
	public Order getOrderByMaster(Master master) {
		if (master == null)
			return null;
		List<Order> orders = orderRepository.getOrders();
		for (int i = 0; i < orders.size(); i++) {
			Master orderMaster = orders.get(i).getMaster();
			if (orderMaster != null) {
				if (orderMaster.equals(master)) {
					return orders.get(i);
				}
			}
		}
		return null;

	}

	@Override
	public Master getMasterByOrder(Order order) {
		if (order == null)
			return null;
		for (Order orderIter : orderRepository.getOrders()) {
			if (orderIter.equals(order)) {
				return orderIter.getMaster();
			}
		}
		return null;
	}

	@Override
	public int getFreeGarageNumber(Date date) {
		int freeGaragesNum = 0;
		for (Order order : orderRepository.getOrders()) {
			if (order.getExecutionDate().before(date))
				freeGaragesNum++;
		}
		return freeGaragesNum;
	}

	@Override
	public int getFreeMasterNumber(Date date) {
		int freeMastersNum = 0;
		for (Order order : orderRepository.getOrders()) {
			if (order.getExecutionDate().before(date))
				freeMastersNum++;
		}
		return freeMastersNum;
	}

	@Override
	public Order getOrderById(long id) {
		return orderRepository.getOrder(id);
	}

	@Override
	public boolean assignMasterToOrder(Order order, Master master) {
		if (order.getMaster() != null) {
			order.getMaster().setIsFree(true);
		}
		if (master.getIsFree()) {
			order.setMaster(master);
			master.setIsFree(false);
			return true;
		}
		return false;
	}

	@Override
	public boolean assignGarageToOrder(Order order, Garage garage) {
		if (order.getGarage() != null) {
			order.getGarage().setIsFree(true);
		}
		if (garage.getIsFree()) {
			order.setGarage(garage);
			garage.setIsFree(false);
			return true;
		}
		return false;
	}

	@Override
	public Order getCopy(Order order) throws CloneNotSupportedException {
		return order.clone();
	}

	@Override
	public boolean updateOrder(Order order) {
		return orderRepository.updateOrder(order);
	}

	@Override
	public boolean exportOrders(List<Order> orders) throws IllegalAccessException, IOException, NoSuchFieldException {
		return CsvFileWorker.safeToFile(orders);
	}

	@Override
	public boolean importOrders()
			throws FileNotFoundException, IOException, IllegalAccessException, NoSuchFieldException,
			InstantiationException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
		@SuppressWarnings("unchecked")
		List<Order> orders = (List<Order>) CsvFileWorker.readFromFileOrder();
		for (Order order : orders) {
			orderRepository.addOrder(order);
		}
		return true;
	}

}
