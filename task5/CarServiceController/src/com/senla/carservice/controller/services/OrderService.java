package com.senla.carservice.controller.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.senla.carservice.api.repositories.IOrderRepository;
import com.senla.carservice.api.services.IOrderService;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.model.comparators.entity.IdComparator;
import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.model.orderstate.OrderState;
import com.senla.carservice.repository.repositories.OrderRepository;
import com.senla.carservice.util.utils.DateWorker;

public class OrderService implements IOrderService {
	private IOrderRepository orderRepository;

	public OrderService(String fileName) {
		this.orderRepository = new OrderRepository(fileName);
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
	public boolean updateOrder(Order order, OrderState state) {
		return orderRepository.updateOrder(order, state);
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
		orders.get(orders.indexOf(order)).setState(state);
		return true;
	}

	@Override
	public void shiftExecution(int daysNum) {
		for (Order order : orderRepository.getOrders()) {
			order.setExecutionDate(DateWorker.shiftDate(order.getExecutionDate(), daysNum));
		}
	}

	@Override
	public void safeToFile() throws Exception {
		orderRepository.getOrders().sort(new IdComparator());
		orderRepository.safeToFile();

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
	public void readFromFile(List<Garage> garages, List<Master> masters) throws ParseException, Exception {
		orderRepository.readFromFile(garages, masters);

	}

	@Override
	public Order getOrderByMaster(Master master) {
		if (master == null)
			return null;
		for (Order order : orderRepository.getOrders()) {
			if (order.getMaster().equals(master)) {
				return order;
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
	public void assignMasterToOrder(Order order, Master master) {
		if (order.getMaster() != null) {
			order.getMaster().setIsFree(true);
			order.setMaster(master);
			master.setIsFree(false);
		}
	}

}
