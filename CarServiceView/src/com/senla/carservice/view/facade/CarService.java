package com.senla.carservice.view.facade;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.model.comparators.masters.*;
import com.senla.carservice.model.comparators.orders.*;
import com.senla.carservice.model.orderstate.OrderState;
import com.senla.carservice.controller.services.GarageService;
import com.senla.carservice.controller.services.MasterService;
import com.senla.carservice.controller.services.OrderService;
import com.senla.carservice.controller.services.api.*;
import com.senla.carservice.model.utils.DateWorker;

public class CarService {
	private static Logger log = Logger.getLogger(CarService.class.getName());
	private IGarageService garageService;
	private IMasterService masterService;
	private IOrderService orderService;

	public CarService(String garageFileName, String masterFileName, String orderFileName) {
		this.garageService = new GarageService(garageFileName);
		this.masterService = new MasterService(masterFileName);
		this.orderService = new OrderService(orderFileName);
		FileHandler fh;
		try {
			fh = new FileHandler("%tLogApp");
			log.addHandler(fh);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public ArrayList<Garage> getGarages() {
		return garageService.getGarages();
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

	public ArrayList<Master> getMasters() {
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

	public void updateOrder(Order order, OrderState state) {
		orderService.updateOrder(order, state);
	}

	public ArrayList<Garage> getFreeGarages() {
		return garageService.getFreeGarages();
	}

	public ArrayList<Order> getOrders() {
		return orderService.getOrders();
	}

	public ArrayList<Order> getCurrentExecutingOrders() {
		return orderService.getCurrentExecutingOrders();
	}

	public Order getOrderByMaster(Master master) {
		return orderService.getOrderByMaster(master);
	}

	public Master getMasterByOrder(Order order) {
		return orderService.getMasterByOrder(order);
	}

	public ArrayList<Order> getExecutedOrders(Date startDate, Date endDate) {
		return orderService.getOrders(OrderState.EXECUTED, startDate, endDate);
	}

	public ArrayList<Order> getCanceledOrders(Date startDate, Date endDate) {
		return orderService.getOrders(OrderState.CANCELED, startDate, endDate);
	}

	public ArrayList<Order> getRemoteOrders(Date startDate, Date endDate) {
		try {
			return orderService.getOrders(OrderState.REMOTE, DateWorker.formatDate(startDate),
					DateWorker.formatDate(endDate));
		} catch (ParseException ex) {
			log.log(Level.SEVERE, "ParseException: ", ex);
		}
		return null;
	}

	public int getFreeCarServicePlaceNum(Date date) {
		try {
			date = DateWorker.formatDate(date);
		} catch (ParseException ex) {
			log.log(Level.SEVERE, "ParseException: ", ex);
		}
		int freeMasters = masterService.getFreeMastersNumber(date) + orderService.getFreeMasterNumber(date);
		int freeGarages = garageService.getFreeGarageNumber() + orderService.getFreeGarageNumber(date);
		int freePlace = (freeMasters > freeGarages) ? freeGarages : freeMasters;
		return freePlace;
	}

	public Date getFreeDate() {
		Date date = new Date();
		while (getFreeCarServicePlaceNum(date) == 0) {
			date = DateWorker.shiftDate(date, 1);
		}
		return date;
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

	public void sortOrdersBySubmissionDate(ArrayList<Order> orders) {
		orderService.sort(new SubmissionDateComparator(), orders);
	}

	public void sortOrdersByExecutionDate(ArrayList<Order> orders) {
		orderService.sort(new ExecutionDateComparator(), orders);
	}

	public void sortOrdersByPrice(ArrayList<Order> orders) {
		orderService.sort(new PriceComparator(), orders);
	}

	public void exit() {
		garageService.safeToFile();
		masterService.safeToFile();
		orderService.safeToFile();
	}

	public void loadDate() {
		garageService.readFromFile();
		masterService.readFromFile();
		try {
			orderService.readFromFile();
		} catch (ParseException ex) {
			log.log(Level.SEVERE, "ParseException: ", ex);
		}
	}
}
