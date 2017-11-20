package com.senla.carservice.view.facade;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

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
import com.senla.carservice.model.beans.abstractentity.Entity;
import com.senla.carservice.model.comparators.entity.IdComparator;
import com.senla.carservice.model.comparators.masters.AscendingAlphabetComparator;
import com.senla.carservice.model.comparators.masters.DescendingAlphabetComparator;
import com.senla.carservice.model.comparators.masters.EmploymentComparator;
import com.senla.carservice.model.comparators.orders.ExecutionDateComparator;
import com.senla.carservice.model.comparators.orders.PlannedStartDateComparator;
import com.senla.carservice.model.comparators.orders.PriceComparator;
import com.senla.carservice.model.comparators.orders.SubmissionDateComparator;
import com.senla.carservice.model.orderstate.OrderState;
import com.senla.carservice.util.utils.DateWorker;
import com.senla.carservice.util.utils.Serializer;

public class CarService implements ICarService {
	private static Logger log = Logger.getLogger(CarService.class.getName());
	private static CarService instance;
	private IGarageService garageService;
	private IMasterService masterService;
	private IOrderService orderService;

	public static CarService getInstance() {
		if (instance == null) {
			instance = new CarService();
		}
		return instance;
	}

	private CarService() {
		this.garageService = new GarageService();
		this.masterService = new MasterService();
		this.orderService = new OrderService();

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
	public boolean closeOrder(Order order) {
		return orderService.closeOrder(order);
	}

	@Override
	public boolean cancelOrder(Order order) {
		return orderService.cancelOrder(order);
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
		try {
			return orderService.getOrders(OrderState.EXECUTED, DateWorker.formatDate(startDate),
					DateWorker.formatDate(endDate));
		} catch (ParseException e) {
			log.error("ParseException", e);
		}
		return null;
	}

	@Override
	public List<Order> getCanceledOrders(Date startDate, Date endDate) {
		try {
			return orderService.getOrders(OrderState.CANCELED, DateWorker.formatDate(startDate),
					DateWorker.formatDate(endDate));
		} catch (ParseException e) {
			log.error("ParseException", e);
		}
		return null;
	}

	@Override
	public List<Order> getRemoteOrders(Date startDate, Date endDate) {
		try {
			return orderService.getOrders(OrderState.REMOTE, DateWorker.formatDate(startDate),
					DateWorker.formatDate(endDate));
		} catch (ParseException ex) {
			log.error("ParseException: ", ex);
		}
		return null;
	}

	@Override
	public int getFreeCarServicePlaceNum(Date date) {
		try {
			date = DateWorker.formatDate(date);
			int freeMasters = masterService.getFreeMastersNumber(date) + orderService.getFreeMasterNumber(date);
			int freeGarages = garageService.getFreeGarageNumber() + orderService.getFreeGarageNumber(date);
			int freePlace = (freeMasters > freeGarages) ? freeGarages : freeMasters;
			return freePlace;
		} catch (ParseException ex) {
			log.error("ParseException: ", ex);
		}
		return 0;
	}

	@Override
	public Date getFreeDate() {
		try {
			Date date = new Date();
			date = DateWorker.formatDate(date);
			while (getFreeCarServicePlaceNum(date) == 0) {
				date = DateWorker.shiftDate(date, 1);
			}
			return date;
		} catch (ParseException e) {
			log.error("ParseException", e);
		}
		return null;
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
		List<List<? extends Entity>> list = new ArrayList<>();
		List<Garage> garages = garageService.getGarages();
		garages.sort(new IdComparator());
		list.add(garages);
		List<Master> masters = masterService.getMasters();
		masters.sort(new IdComparator());
		list.add(masters);
		List<Order> orders = orderService.getOrders();
		orders.sort(new IdComparator());
		list.add(orders);

		try {
			Serializer.serialize(list);
			return true;
		} catch (IOException e) {
			log.error("IOExcepton", e);
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void loadData() {
		try {
			List<List<? extends Entity>> list = Serializer.deserialize();
			garageService.restoreData((ArrayList<Garage>) list.get(0));
			masterService.restoreData((ArrayList<Master>) list.get(1));
			orderService.restoreData((ArrayList<Order>) list.get(2));
		} catch (ClassNotFoundException e) {
			log.error("ClassNotFoundException", e);
		} catch (IOException e) {
			log.error("IOExcepton", e);
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
	public boolean assignMasterToOrder(Order order, Master master) {
		return orderService.assignMasterToOrder(order, master);
	}

	@Override
	public boolean assignGarageToOrder(Order order, Garage garage) {
		return orderService.assignGarageToOrder(order, garage);
	}

	@Override
	public List<Master> getFreeMasters() {
		return masterService.getFreeMasters();
	}

	@Override
	public boolean updateOrder(Order order) {
		return orderService.updateOrder(order);
	}

	@Override
	public Order getCopy(Order order) {
		try {
			return orderService.getCopy(order);
		} catch (CloneNotSupportedException e) {
			log.error("CopyNotSupportException", e);
		}
		return null;
	}

	@Override
	public boolean exportGarages(String fileName, List<Garage> garages) {
		try {
			return garageService.exportGarages(fileName, garages);
		} catch (IOException e) {
			log.error("IOExcepton", e);
		}
		return false;

	}

	@Override
	public boolean importGarages(String fileName) {
		try {
			return garageService.importGarages(fileName);
		} catch (FileNotFoundException e) {
			log.error("FileNotFound", e);
		} catch (IOException e) {
			log.error("IOExcepton", e);
		}
		return false;

	}

	@Override
	public boolean exportMasters(String fileName, List<Master> masters) {
		try {
			return masterService.exportMasters(fileName, masters);
		} catch (IOException e) {
			log.error("IOExcepton", e);
		}
		return false;
	}

	@Override
	public boolean importMasters(String fileName) {
		try {
			return masterService.importMasters(fileName);
		} catch (FileNotFoundException e) {
			log.error("FileNotFound", e);
		} catch (IOException e) {
			log.error("IOExcepton", e);
		}
		return false;
	}

	@Override
	public boolean exportOrders(String fileName, List<Order> orders) {
		try {
			return orderService.exportOrders(fileName, orders);
		} catch (IOException e) {
			log.error("IOExcepton", e);
		}
		return false;
	}

	@Override
	public boolean importOrders(String fileName) {
		try {
			return orderService.importOrders(fileName);
		} catch (FileNotFoundException e) {
			log.error("FileNotFound", e);
		} catch (IOException e) {
			log.error("IOExcepton", e);
		} catch (ParseException e) {
			log.error("ParseException", e);
		}
		return false;
	}
}
