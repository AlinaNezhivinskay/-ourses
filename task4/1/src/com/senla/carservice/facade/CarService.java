package com.senla.carservice.facade;

import java.util.Date;

import com.senla.carservice.beans.Garage;
import com.senla.carservice.beans.Master;
import com.senla.carservice.beans.Order;
import com.senla.carservice.comparators.masters.*;
import com.senla.carservice.comparators.orders.*;
import com.senla.carservice.orderstate.OrderState;
import com.senla.carservice.services.GarageService;
import com.senla.carservice.services.MasterService;
import com.senla.carservice.services.OrderService;
import com.senla.carservice.services.interfaces.*;
import com.senla.carservice.utils.DateWorker;
import com.senla.carservice.utils.Printer;

public class CarService {
	private IGarageService garageService;
	private IMasterService masterService;
	private IOrderService orderService;

	public CarService(String garageFileName, String masterFileName, String orderFileName) {
		this.garageService = new GarageService(garageFileName);
		this.masterService = new MasterService(masterFileName);
		this.orderService = new OrderService(orderFileName);
	}

	public void addGarage(Garage garage) {
		garageService.addGarage(garage);
	}

	public void removeGarage(Garage garage) {
		garageService.removeGarage(garage);
	}

	public void updateGarage(Garage garage, boolean isFree) {
		garageService.updateGarage(garage, isFree);
	}

	public void getGarages() {
		Printer.print(garageService.getGarages());
	}

	public void addMaster(Master master) {
		masterService.addMaster(master);
	}

	public void removeMaster(Master master) {
		masterService.removeMaster(master);
	}

	public void updateMaster(Master master, boolean isFree) {
		masterService.updateMaster(master, isFree);
	}

	public void getMasters() {
		Printer.print(masterService.getMasters());
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

	public void updateOrder(Order order, OrderState state) {
		orderService.updateOrder(order, state);
	}

	public void getFreeGarages() {
		Printer.print(garageService.getFreeGarages());
	}

	public void getOrders() {
		Printer.print(orderService.getOrders());
	}

	public void getCurrentExecutingOrders() {
		Printer.print(orderService.getCurrentExecutingOrders());
	}

	public void getOrderByMaster(Master master) {
		Printer.print(orderService.getOrderByMaster(master));
	}

	public void getMasterByOrder(Order order) {
		Printer.print(orderService.getMasterByOrder(order));
	}

	public void getExecutedOrders(Date startDate, Date endDate) {
		Printer.print("EXECUTED ORDERS:");
		Printer.print(orderService.getOrders(OrderState.EXECUTED, startDate, endDate));
	}

	public void getCanceledOrders(Date startDate, Date endDate) {
		Printer.print("CANCELED ORDERS:");
		Printer.print(orderService.getOrders(OrderState.CANCELED, startDate, endDate));
	}

	public void getRemoteOrders(Date startDate, Date endDate) {
		Printer.print("REMOTE ORDERS:");
		Printer.print(orderService.getOrders(OrderState.REMOTE, DateWorker.formatDate(startDate),
				DateWorker.formatDate(endDate)));
	}

	public int getFreeCarServicePlaceNum(Date date) {
		date = DateWorker.formatDate(date);
		int freeMasters = masterService.getFreeMastersNumber(date) + orderService.getFreeMasterNumber(date);
		int freeGarages = garageService.getFreeGarageNumber() + orderService.getFreeGarageNumber(date);
		int freePlace = (freeMasters > freeGarages) ? freeGarages : freeMasters;
		Printer.print("Free carService place=" + freePlace);
		return freePlace;
	}

	public void getFreeDate() {
		Date date = new Date();
		while (getFreeCarServicePlaceNum(date) == 0) {
			date = DateWorker.shiftDate(date, 1);
		}
		Printer.print(date);
	}

	public void sortOrdersBySubmissionDate() {
		orderService.sort(new SubmissionDateComparator());
		getOrders();
	}

	public void sortOrdersByExecutionDate() {
		orderService.sort(new ExecutionDateComparator());
		getOrders();
	}

	public void sortOrdersByPlannedStartDate() {
		orderService.sort(new PlannedStartDateComparator());
		getOrders();
	}

	public void sortOrdersByPrice() {
		orderService.sort(new PriceComparator());
		getOrders();
	}

	public void sortMastersByAlphabetAscending() {
		masterService.sort(new AscendingAlphabetComparator());
		getMasters();

	}

	public void sortMastersByAlphabetDescending() {
		masterService.sort(new DescendingAlphabetComparator());
		getMasters();

	}

	public void sortMastersByEmployment() {
		masterService.sort(new EmploymentComparator());
		getMasters();
	}

	public void sortExecutingOrdersBySubmissionDate() {
		sortOrdersBySubmissionDate(orderService.getCurrentExecutingOrders());
	}

	public void sortExecutingOrdersByExecutionDate() {
		sortOrdersByExecutionDate(orderService.getCurrentExecutingOrders());
	}

	public void sortExecutingOrdersByPrice() {
		sortOrdersByPrice(orderService.getCurrentExecutingOrders());
	}

	public void sortOrdersBySubmissionDate(Order[] orders) {
		orderService.sort(new SubmissionDateComparator(), orders);
		Printer.print(orders);
	}

	public void sortOrdersByExecutionDate(Order[] orders) {
		orderService.sort(new ExecutionDateComparator(), orders);
		Printer.print(orders);
	}

	public void sortOrdersByPrice(Order[] orders) {
		orderService.sort(new PriceComparator(), orders);
		Printer.print(orders);
	}

	public void exit() {
		garageService.safeToFile();
		masterService.safeToFile();
		orderService.safeToFile();
	}

	public void loadDate() {
		garageService.readFromFile();
		masterService.readFromFile();
		orderService.readFromFile();
	}
}
