package com.senla.carservice.services;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import com.senla.carservice.beans.Master;
import com.senla.carservice.beans.Order;
import com.senla.carservice.repositories.HistoryOrderRepository;
import com.senla.carservice.repositories.OrderRepository;
import com.senla.carservice.services.interfaces.IOrderService;
import com.senla.carservice.utils.ArrayWorker;
import com.senla.carservice.utils.DateWorker;
import com.senla.carservice.orderstate.OrderState;

public class OrderService implements IOrderService {
	private OrderRepository orderRepository;
	private HistoryOrderRepository historyOrderRepository;

	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
		this.historyOrderRepository = HistoryOrderRepository.getInstance();
	}

	@Override
	public void addOrder(Order order) {
		orderRepository.addOrder(order);
	}

	@Override
	public boolean removeOrder(Order order) {
		return orderRepository.removeOrder(order);
	}

	@Override
	public boolean updateOrder(Order order) {
		return orderRepository.updateOrder(order);
	}

	@Override
	public boolean closeOrder(Order order) {
		if (!ArrayWorker.isElementOnArray(orderRepository.getOrders(), order))
			return false;
		orderRepository.getOrders()[ArrayWorker.getPositionOfElement(orderRepository.getOrders(), order)].getGarage()
				.setIsFree(true);
		orderRepository.getOrders()[ArrayWorker.getPositionOfElement(orderRepository.getOrders(), order)]
				.setState(OrderState.EXECUTED);
		return true;
	}

	@Override
	public boolean cancelOrder(Order order) {
		if (!ArrayWorker.isElementOnArray(orderRepository.getOrders(), order))
			return false;
		orderRepository.getOrders()[ArrayWorker.getPositionOfElement(orderRepository.getOrders(), order)].getGarage()
				.setIsFree(true);
		orderRepository.getOrders()[ArrayWorker.getPositionOfElement(orderRepository.getOrders(), order)]
				.setState(OrderState.CANCELED);
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
					executingOrders = ArrayWorker.expandArray(executingOrders);
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
			if (orderRepository.getOrders()[i].getState().equals(state)
					&& orderRepository.getOrders()[i].getExecutionDate().after(startTimePeriod)
					&& orderRepository.getOrders()[i].getExecutionDate().before(endTimePeriod)) {
				if (!ArrayWorker.addElementInArray(orders, orderRepository.getOrders()[i])) {
					orders = ArrayWorker.expandArray(orders);
					i--;
				}
			}
		}
		return orders;
	}

	@Override
	public void sort(Comparator<Order> comparator, Order[] orders) {
		Arrays.sort(orders, comparator);
		;
	}

	@Override
	public void readFromFile() {
		orderRepository.readFromFile();

	}

	@Override
	public Order getOrderByMaster(Master master) {
		if (master == null)
			return null;
		for (int i = 0; i < historyOrderRepository.getHistoryOrders().length; i++) {
			if (historyOrderRepository.getHistoryOrders()[i].getMaster().equals(master)) {
				return historyOrderRepository.getHistoryOrders()[i].getOrder();
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

}
