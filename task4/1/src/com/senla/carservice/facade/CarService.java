package com.senla.carservice.facade;

import java.util.Date;

import com.senla.carservice.beans.Garage;
import com.senla.carservice.beans.HistoryOrder;
import com.senla.carservice.beans.Master;
import com.senla.carservice.beans.Order;
import com.senla.carservice.comparators.masters.*;
import com.senla.carservice.comparators.orders.*;
import com.senla.carservice.orderstate.OrderState;
import com.senla.carservice.repositories.HistoryOrderRepository;
import com.senla.carservice.services.interfaces.*;
import com.senla.carservice.utils.DateWorker;

public class CarService {
	private IGarageService garageService;
	private IMasterService masterService;
	private IOrderService orderService;

	public CarService(IGarageService garageService, IMasterService masterService, IOrderService orderService) {
		this.garageService = garageService;
		this.masterService = masterService;
		this.orderService = orderService;
	}

	public void addGarage(Garage garage) {
		garageService.addGarage(garage);
	}

	public void removeGarage(Garage garage) {
		garageService.removeGarage(garage);
	}

	public void updateGarage(Garage garage) {
		garageService.updateGarage(garage);
	}

	public Garage[] getGarages() {
		return garageService.getGarages();
	}

	public void addMaster(Master master) {
		masterService.addMaster(master);
	}

	public void removeMaster(Master master) {
		masterService.removeMaster(master);
	}

	public void updateMaster(Master master) {
		masterService.updateMaster(master);
	}

	public Master[] getMasters() {
		return masterService.getMasters();
	}

	public void addOrder(Order order) {
		orderService.addOrder(order);
	}

	public void removeOrder(Order order) {
		orderService.removeOrder(order);
	}

	public void closeOrder(Order order) {
		orderService.closeOrder(order);
	}

	public void cancelOrder(Order order) {
		orderService.cancelOrder(order);
	}

	public void updateOrder(Order order) {
		orderService.updateOrder(order);
	}

	public void assignMasterToOrder(Order order, Master master) {
		HistoryOrderRepository.getInstance().addHistoryOrders(new HistoryOrder(order, master));
	}

	public Garage[] getFreeGarages() {
		return garageService.getFreeGarages();
	}

	public Order[] getOrders() {
		return orderService.getOrders();
	}

	public Order[] getCurrentExecutingOrders() {
		return orderService.getCurrentExecutingOrders();
	}

	public Order getOrderByMaster(Master master) {
		return orderService.getOrderByMaster(master);
	}

	public Master getMasterByOrder(Order order) {
		return masterService.getMasterByOrder(order);
	}

	public Order[] getExecutedOrders(Date startDate, Date endDate) {
		return orderService.getOrders(OrderState.EXECUTED, startDate, endDate);
	}

	public Order[] getCanceledOrders(Date startDate, Date endDate) {
		return orderService.getOrders(OrderState.CANCELED, startDate, endDate);
	}

	public Order[] getRemoteOrders(Date startDate, Date endDate) {
		return orderService.getOrders(OrderState.REMOTE, startDate, endDate);
	}

	public int getFreeCarServicePlaceNum(Date date) {
		int freeMasters = masterService.getFreeMastersNumber(date);
		int freeGarages = garageService.getFreeGarageNumber() + orderService.getFreeGarageNumber(date);
		return (freeMasters > freeGarages) ? freeGarages : freeMasters;
	}

	public Date getFreeDate() {
		Date date = new Date();
		while (getFreeCarServicePlaceNum(date) == 0) {
			date = DateWorker.shiftDate(date, 1);
		}
		return date;
	}

	public void sortOrdersBySubmissionDate(Order[] orders) {
		orderService.sort(new SubmissionDateComparator(), orders);
	}

	public void sortOrdersByExecutionDate(Order[] orders) {
		orderService.sort(new ExecutionDateComparator(), orders);
	}

	public void sortOrdersByPlannedStartDate(Order[] orders) {
		orderService.sort(new PlannedStartDateComparator(), orders);
	}

	public void sortOrdersByPrice(Order[] orders) {
		orderService.sort(new PriceComparator(), orders);
	}

	public void sortMastersByAlphabet(Master[] masters, boolean isAscending) {
		if (isAscending) {
			masterService.sort(new AscendingAlphabetComparator(), masters);
		} else {
			masterService.sort(new DescendingAlphabetComparator(), masters);
		}
	}

	public void sortMastersByEmployment(Master[] masters) {
		masterService.sort(new EmploymentComparator(), masters);
	}

	public void safeAll() {
		garageService.safeToFile();
		masterService.safeToFile();
		orderService.safeToFile();
		HistoryOrderRepository.getInstance().safeToFile();
	}

	public void loadDate() {
		garageService.readFromFile();
		masterService.readFromFile();
		orderService.readFromFile();
		HistoryOrderRepository.getInstance().readFromFile();
	}
}
