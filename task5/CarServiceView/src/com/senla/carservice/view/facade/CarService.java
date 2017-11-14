package com.senla.carservice.view.facade;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.senla.carservice.api.facade.ICarService;
import com.senla.carservice.api.services.IGarageService;
import com.senla.carservice.api.services.IMasterService;
import com.senla.carservice.api.services.IOrderService;
import com.senla.carservice.controller.services.GarageService;
import com.senla.carservice.controller.services.MasterService;
import com.senla.carservice.controller.services.OrderService;
import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.model.comparators.masters.AscendingAlphabetComparator;
import com.senla.carservice.model.comparators.masters.DescendingAlphabetComparator;
import com.senla.carservice.model.comparators.masters.EmploymentComparator;
import com.senla.carservice.model.comparators.orders.ExecutionDateComparator;
import com.senla.carservice.model.comparators.orders.PlannedStartDateComparator;
import com.senla.carservice.model.comparators.orders.PriceComparator;
import com.senla.carservice.model.comparators.orders.SubmissionDateComparator;
import com.senla.carservice.model.orderstate.OrderState;
import com.senla.carservice.util.utils.DateWorker;

public class CarService implements ICarService {
	private static Logger log = Logger.getLogger(CarService.class.getName());
	private static CarService instance;
	private IGarageService garageService;
	private IMasterService masterService;
	private IOrderService orderService;

	public static CarService getInstance() {
		if (instance == null) {
			instance = new CarService(null, null, null);
		}
		return instance;
	}

	public static CarService getInstance(String garageFile, String masterFile, String orderFile) {
		if (instance == null) {
			instance = new CarService(garageFile, masterFile, orderFile);
		}
		return instance;
	}

	private CarService(String garageFile, String masterFile, String orderFile) {
		this.garageService = new GarageService(garageFile);
		this.masterService = new MasterService(masterFile);
		this.orderService = new OrderService(orderFile);
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

	@Override
	public void addGarage(Garage garage) {
		garageService.addGarage(garage);
	}

	@Override
	public boolean removeGarage(Garage garage) {
		return garageService.removeGarage(garage);
	}

	@Override
	public void updateGarage(Garage garage, boolean isFree) {
		garageService.updateGarage(garage, isFree);
	}

	@Override
	public List<Garage> getGarages() {
		return garageService.getGarages();
	}

	@Override
	public void addMaster(Master master) {
		masterService.addMaster(master);
	}

	@Override
	public boolean removeMaster(Master master) {
		return masterService.removeMaster(master);
	}

	@Override
	public void updateMaster(Master master, boolean isFree) {
		masterService.updateMaster(master, isFree);
	}

	@Override
	public List<Master> getMasters() {
		return masterService.getMasters();
	}

	@Override
	public void addOrder(Order order) {
		orderService.addOrder(order);
	}

	@Override
	public boolean removeOrder(Order order) {
		return orderService.removeOrder(order);
	}

	@Override
	public void closeOrder(Order order) {
		orderService.closeOrder(order);
	}

	@Override
	public void cancelOrder(Order order) {
		orderService.cancelOrder(order);
	}

	@Override
	public void updateOrder(Order order, OrderState state) {
		orderService.updateOrder(order, state);
	}

	@Override
	public List<Garage> getFreeGarages() {
		return garageService.getFreeGarages();
	}

	@Override
	public List<Order> getOrders() {
		return orderService.getOrders();
	}

	@Override
	public List<Order> getCurrentExecutingOrders() {
		return orderService.getCurrentExecutingOrders();
	}

	@Override
	public Order getOrderByMaster(Master master) {
		return orderService.getOrderByMaster(master);
	}

	@Override
	public Master getMasterByOrder(Order order) {
		return orderService.getMasterByOrder(order);
	}

	@Override
	public List<Order> getExecutedOrders(Date startDate, Date endDate) {
		return orderService.getOrders(OrderState.EXECUTED, startDate, endDate);
	}

	@Override
	public List<Order> getCanceledOrders(Date startDate, Date endDate) {
		return orderService.getOrders(OrderState.CANCELED, startDate, endDate);
	}

	@Override
	public List<Order> getRemoteOrders(Date startDate, Date endDate) {
		try {
			return orderService.getOrders(OrderState.REMOTE, DateWorker.formatDate(startDate),
					DateWorker.formatDate(endDate));
		} catch (ParseException ex) {
			log.log(Level.SEVERE, "ParseException: ", ex);
		}
		return null;
	}

	@Override
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

	@Override
	public Date getFreeDate() {
		Date date = new Date();
		while (getFreeCarServicePlaceNum(date) == 0) {
			date = DateWorker.shiftDate(date, 1);
		}
		return date;
	}

	@Override
	public void sortOrdersBySubmissionDate() {
		orderService.sort(new SubmissionDateComparator());
	}

	@Override
	public void sortOrdersByExecutionDate() {
		orderService.sort(new ExecutionDateComparator());
	}

	@Override
	public void sortOrdersByPlannedStartDate() {
		orderService.sort(new PlannedStartDateComparator());
	}

	@Override
	public void sortOrdersByPrice() {
		orderService.sort(new PriceComparator());
	}

	@Override
	public void sortMastersByAlphabetAscending() {
		masterService.sort(new AscendingAlphabetComparator());

	}

	@Override
	public void sortMastersByAlphabetDescending() {
		masterService.sort(new DescendingAlphabetComparator());

	}

	@Override
	public void sortMastersByEmployment() {
		masterService.sort(new EmploymentComparator());
	}

	@Override
	public void sortExecutingOrdersBySubmissionDate() {
		sortOrdersBySubmissionDate(orderService.getCurrentExecutingOrders());
	}

	@Override
	public void sortExecutingOrdersByExecutionDate() {
		sortOrdersByExecutionDate(orderService.getCurrentExecutingOrders());
	}

	@Override
	public void sortExecutingOrdersByPrice() {
		sortOrdersByPrice(orderService.getCurrentExecutingOrders());
	}

	@Override
	public void sortOrdersBySubmissionDate(List<Order> orders) {
		orderService.sort(new SubmissionDateComparator(), orders);
	}

	@Override
	public void sortOrdersByExecutionDate(List<Order> orders) {
		orderService.sort(new ExecutionDateComparator(), orders);
	}

	@Override
	public void sortOrdersByPrice(List<Order> orders) {
		orderService.sort(new PriceComparator(), orders);
	}

	@Override
	public boolean exit() {
		try {
			garageService.safeToFile();
			masterService.safeToFile();
			orderService.safeToFile();
			return true;
		} catch (Exception ex) {
			log.log(Level.SEVERE, "FileNotFound: ", ex);
			return false;
		}
	}

	@Override
	public boolean loadData() {
		try {
			garageService.readFromFile();
			masterService.readFromFile();
			orderService.readFromFile(garageService.getGarages(), masterService.getMasters());
			return true;
		} catch (ParseException ex) {
			log.log(Level.SEVERE, "ParseException: ", ex);
			return false;
		} catch (Exception ex) {
			log.log(Level.SEVERE, "FileNotFound: ", ex);
			return false;
		}
	}

	@Override
	public Garage getGarageById(long id) {
		return garageService.getGarageById(id);
	}

	@Override
	public Master getMasterById(long id) {
		return masterService.getMasterById(id);
	}

	@Override
	public Order getOrderById(long id) {
		return orderService.getOrderById(id);
	}

	@Override
	public void shiftExecution(int daysNum) {
		orderService.shiftExecution(daysNum);

	}

	@Override
	public void assignMasterToOrder(Order order, Master master) {
		orderService.assignMasterToOrder(order, master);
	}

	@Override
	public List<Master> getFreeMasters() {
		return masterService.getFreeMasters();
	}
}
