package com.senla.carservice.services;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import com.senla.carservice.beans.Master;
import com.senla.carservice.beans.Order;
import com.senla.carservice.repositories.OrderRepository;
import com.senla.carservice.services.api.IOrderService;
import com.senla.carservice.utils.ArrayWorker;
import com.senla.carservice.utils.DateWorker;
import com.senla.carservice.orderstate.OrderState;

public class OrderService implements IOrderService {
	private OrderRepository orderRepository;

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
		if (!ArrayWorker.isElementOnArray(orderRepository.getOrders(), order))
			return false;
		orderRepository.getOrders()[ArrayWorker.getPositionOfElement(orderRepository.getOrders(), order)].getGarage()
				.setIsFree(true);
		if (orderRepository.getOrders()[ArrayWorker.getPositionOfElement(orderRepository.getOrders(), order)]
				.getMaster() != null) {
			orderRepository.getOrders()[ArrayWorker.getPositionOfElement(orderRepository.getOrders(), order)]
					.getMaster().setIsFree(true);
		}
		orderRepository.getOrders()[ArrayWorker.getPositionOfElement(orderRepository.getOrders(), order)]
				.setExecutionDate(new Date());
		orderRepository.getOrders()[ArrayWorker.getPositionOfElement(orderRepository.getOrders(), order)]
				.setState(state);
		return true;
	}

	@Override
	public void shiftExecution(int daysNum) {
		for (int i = 0; i < orderRepository.getOrders().length; i++) {
			orderRepository.getOrders()[i]
					.setExecutionDate(DateWorker.shiftDate(orderRepository.getOrders()[i].getExecutionDate(), daysNum));
		}
	}

	@Override
	public void safeToFile() {
		orderRepository.safeToFile();

	}

	@Override
	public Order[] getOrders() {
		return orderRepository.getOrders();
	}

	@Override
	public Order[] getCurrentExecutingOrders() {
		Order[] executingOrders = new Order[ArrayWorker.ARRAY_LENGTH];
		for (int i = 0; i < orderRepository.getOrders().length; i++) {
			if (orderRepository.getOrders()[i].getState().equals(OrderState.EXECUTABLE)) {
				if (!ArrayWorker.addElementInArray(executingOrders, orderRepository.getOrders()[i])) {
					executingOrders = (Order[]) ArrayWorker.expandArray(executingOrders);
					i--;
				}
			}
		}
		return executingOrders;
	}

	@Override
	public Order[] getOrders(OrderState state, Date startTimePeriod, Date endTimePeriod) {
		Order[] orders = new Order[ArrayWorker.ARRAY_LENGTH];
		for (int i = 0; i < orderRepository.getOrders().length; i++) {
			if (orderRepository.getOrders()[i] == null)
				continue;
			if (orderRepository.getOrders()[i].getState().equals(state)
					&& startTimePeriod.before(orderRepository.getOrders()[i].getSubmissionDate())
					&& endTimePeriod.after(orderRepository.getOrders()[i].getExecutionDate())) {
				if (!ArrayWorker.addElementInArray(orders, orderRepository.getOrders()[i])) {
					orders = (Order[]) ArrayWorker.expandArray(orders);
					i--;
				}
			}
		}
		return orders;
	}

	@Override
	public void sort(Comparator<Order> comparator) {
		Arrays.sort(orderRepository.getOrders(), comparator);
	}

	@Override
	public void sort(Comparator<Order> comparator, Order[] orders) {
		Arrays.sort(orders, comparator);
	}

	@Override
	public void readFromFile() {
		orderRepository.readFromFile();

	}

	@Override
	public Order getOrderByMaster(Master master) {
		if (master == null)
			return null;
		for (int i = 0; i < orderRepository.getOrders().length; i++) {
			if (orderRepository.getOrders()[i] == null) {
				continue;
			}
			if (orderRepository.getOrders()[i].getMaster().equals(master)) {
				return orderRepository.getOrders()[i];
			}
		}
		return null;

	}

	@Override
	public Master getMasterByOrder(Order order) {
		if (order == null)
			return null;
		for (int i = 0; i < orderRepository.getOrders().length; i++) {
			if (orderRepository.getOrders()[i] == null) {
				continue;
			}
			if (orderRepository.getOrders()[i].equals(order)) {
				return orderRepository.getOrders()[i].getMaster();
			}
		}
		return null;
	}

	@Override
	public int getFreeGarageNumber(Date date) {
		int freeGaragesNum = 0;
		for (int i = 0; i < orderRepository.getOrders().length; i++) {
			if (orderRepository.getOrders()[i] == null)
				continue;
			if (orderRepository.getOrders()[i].getExecutionDate().before(date))
				freeGaragesNum++;
		}
		return freeGaragesNum;
	}

	@Override
	public int getFreeMasterNumber(Date date) {
		int freeMastersNum = 0;
		for (int i = 0; i < orderRepository.getOrders().length; i++) {
			if (orderRepository.getOrders()[i] == null)
				continue;
			if (orderRepository.getOrders()[i].getExecutionDate().before(date))
				freeMastersNum++;
		}
		return freeMastersNum;
	}

}
