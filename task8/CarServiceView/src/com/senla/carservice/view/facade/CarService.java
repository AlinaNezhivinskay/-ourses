package com.senla.carservice.view.facade;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.caerservice.dependencyinjection.coordinationrepository.CoordinationRepository;
import com.senla.carservice.api.facade.ICarService;
import com.senla.carservice.api.services.IGarageService;
import com.senla.carservice.api.services.IMasterService;
import com.senla.carservice.api.services.IOrderService;
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
	private static ICarService instance;
	private IGarageService garageService;
	private IMasterService masterService;
	private IOrderService orderService;

	public static ICarService getInstance() {
		if (instance == null) {
			instance = new CarService();
		}
		return instance;
	}

	private CarService() {
		this.garageService = (IGarageService) CoordinationRepository.getInstance().getObject(IGarageService.class);
		this.masterService = (IMasterService) CoordinationRepository.getInstance().getObject(IMasterService.class);
		this.orderService = (IOrderService) CoordinationRepository.getInstance().getObject(IOrderService.class);

	}

	@Override
	public synchronized void addGarage(Garage garage) {
		garageService.addGarage(garage);
	}

	@Override
	public synchronized boolean removeGarage(Garage garage) {
		return garageService.removeGarage(garage);
	}

	@Override
	public synchronized List<Garage> getGarages() {
		return garageService.getGarages();
	}

	@Override
	public synchronized void addMaster(Master master) {
		masterService.addMaster(master);
	}

	@Override
	public synchronized boolean removeMaster(Master master) {
		return masterService.removeMaster(master);
	}

	@Override
	public synchronized List<Master> getMasters() {
		return masterService.getMasters();
	}

	@Override
	public synchronized void addOrder(Order order) {
		orderService.addOrder(order);
	}

	@Override
	public synchronized boolean removeOrder(Order order) {
		return orderService.removeOrder(order);
	}

	@Override
	public synchronized boolean closeOrder(Order order) {
		return orderService.closeOrder(order);
	}

	@Override
	public synchronized boolean cancelOrder(Order order) {
		return orderService.cancelOrder(order);
	}

	@Override
	public synchronized List<Garage> getFreeGarages() {
		return garageService.getFreeGarages();
	}

	@Override
	public synchronized List<Order> getOrders() {
		return orderService.getOrders();
	}

	@Override
	public synchronized List<Order> getCurrentExecutingOrders() {
		return orderService.getCurrentExecutingOrders();
	}

	@Override
	public synchronized Order getOrderByMaster(Master master) {
		return orderService.getOrderByMaster(master);
	}

	@Override
	public synchronized Master getMasterByOrder(Order order) {
		return orderService.getMasterByOrder(order);
	}

	@Override
	public synchronized List<Order> getExecutedOrders(Date startDate, Date endDate) {
		try {
			return orderService.getOrders(OrderState.EXECUTED, DateWorker.formatDate(startDate),
					DateWorker.formatDate(endDate));
		} catch (ParseException e) {
			log.error("ParseException", e);
		}
		return null;
	}

	@Override
	public synchronized List<Order> getCanceledOrders(Date startDate, Date endDate) {
		try {
			return orderService.getOrders(OrderState.CANCELED, DateWorker.formatDate(startDate),
					DateWorker.formatDate(endDate));
		} catch (ParseException e) {
			log.error("ParseException", e);
		}
		return null;
	}

	@Override
	public synchronized List<Order> getRemoteOrders(Date startDate, Date endDate) {
		try {
			return orderService.getOrders(OrderState.REMOTE, DateWorker.formatDate(startDate),
					DateWorker.formatDate(endDate));
		} catch (ParseException ex) {
			log.error("ParseException: ", ex);
		}
		return null;
	}

	@Override
	public synchronized int getFreeCarServicePlaceNum(Date date) {
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
	public synchronized Date getFreeDate() {
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
	public synchronized void sortOrdersBySubmissionDate() {
		orderService.sort(new SubmissionDateComparator());
	}

	@Override
	public synchronized void sortOrdersByExecutionDate() {
		orderService.sort(new ExecutionDateComparator());
	}

	@Override
	public synchronized void sortOrdersByPlannedStartDate() {
		orderService.sort(new PlannedStartDateComparator());
	}

	@Override
	public synchronized void sortOrdersByPrice() {
		orderService.sort(new PriceComparator());
	}

	@Override
	public synchronized void sortMastersByAlphabetAscending() {
		masterService.sort(new AscendingAlphabetComparator());

	}

	@Override
	public synchronized void sortMastersByAlphabetDescending() {
		masterService.sort(new DescendingAlphabetComparator());

	}

	@Override
	public synchronized void sortMastersByEmployment() {
		masterService.sort(new EmploymentComparator());
	}

	@Override
	public synchronized void sortExecutingOrdersBySubmissionDate() {
		sortOrdersBySubmissionDate(orderService.getCurrentExecutingOrders());
	}

	@Override
	public synchronized void sortExecutingOrdersByExecutionDate() {
		sortOrdersByExecutionDate(orderService.getCurrentExecutingOrders());
	}

	@Override
	public synchronized void sortExecutingOrdersByPrice() {
		sortOrdersByPrice(orderService.getCurrentExecutingOrders());
	}

	@Override
	public synchronized void sortOrdersBySubmissionDate(List<Order> orders) {
		orderService.sort(new SubmissionDateComparator(), orders);
	}

	@Override
	public synchronized void sortOrdersByExecutionDate(List<Order> orders) {
		orderService.sort(new ExecutionDateComparator(), orders);
	}

	@Override
	public synchronized void sortOrdersByPrice(List<Order> orders) {
		orderService.sort(new PriceComparator(), orders);
	}

	@Override
	public synchronized boolean exit() {
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
	public synchronized Garage getGarageById(Long id) {
		return garageService.getGarageById(id);
	}

	@Override
	public synchronized Master getMasterById(Long id) {
		return masterService.getMasterById(id);
	}

	@Override
	public synchronized Order getOrderById(Long id) {
		return orderService.getOrderById(id);
	}

	@Override
	public synchronized void shiftExecution(int daysNum) {
		orderService.shiftExecution(daysNum);

	}

	@Override
	public synchronized boolean assignMasterToOrder(Order order, Master master) {
		return orderService.assignMasterToOrder(order, master);
	}

	@Override
	public synchronized boolean assignGarageToOrder(Order order, Garage garage) {
		return orderService.assignGarageToOrder(order, garage);
	}

	@Override
	public synchronized List<Master> getFreeMasters() {
		return masterService.getFreeMasters();
	}

	@Override
	public synchronized boolean updateOrder(Order order) {
		return orderService.updateOrder(order);
	}

	@Override
	public synchronized Order getCopy(Order order) {
		try {
			return orderService.getCopy(order);
		} catch (CloneNotSupportedException e) {
			log.error("CopyNotSupportException", e);
		}
		return null;
	}

	@Override
	public synchronized boolean exportGarages(List<Garage> garages) {
		try {
			return garageService.exportGarages(garages);
		} catch (IOException e) {
			log.error("IOExcepton", e);
		} catch (IllegalAccessException e) {
			log.error("IllegalAccessException", e);
		} catch (NoSuchFieldException e) {
			log.error("NoSuchFieldException", e);
		}
		return false;

	}

	@Override
	public synchronized boolean importGarages() {
		try {
			return garageService.importGarages();
		} catch (FileNotFoundException e) {
			log.error("FileNotFound", e);
		} catch (IOException e) {
			log.error("IOExcepton", e);
		} catch (IllegalAccessException e) {
			log.error("IllegalAccessException", e);
		} catch (NoSuchFieldException e) {
			log.error("NoSuchFieldException", e);
		} catch (InstantiationException e) {
			log.error("InstantiationException", e);
		} catch (InvocationTargetException e) {
			log.error("InvocationTargetException", e);
		} catch (NoSuchMethodException e) {
			log.error("NoSuchMethodException", e);
		} catch (ClassNotFoundException e) {
			log.error("ClassNotFoundException", e);
		}
		return false;

	}

	@Override
	public boolean exportMasters(List<Master> masters) {
		try {
			return masterService.exportMasters(masters);
		} catch (IOException e) {
			log.error("IOExcepton", e);
		} catch (IllegalAccessException e) {
			log.error("IllegalAccessException", e);
		} catch (NoSuchFieldException e) {
			log.error("NoSuchFieldException", e);
		}
		return false;
	}

	@Override
	public synchronized boolean importMasters() {
		try {
			return masterService.importMasters();
		} catch (FileNotFoundException e) {
			log.error("FileNotFound", e);
		} catch (IOException e) {
			log.error("IOExcepton", e);
		} catch (IllegalAccessException e) {
			log.error("IllegalAccessException", e);
		} catch (NoSuchFieldException e) {
			log.error("NoSuchFieldException", e);
		} catch (InstantiationException e) {
			log.error("InstantiationException", e);
		} catch (InvocationTargetException e) {
			log.error("InvocationTargetException", e);
		} catch (NoSuchMethodException e) {
			log.error("NoSuchMethodException", e);
		} catch (ClassNotFoundException e) {
			log.error("ClassNotFoundException", e);
		}
		return false;
	}

	@Override
	public boolean exportOrders(List<Order> orders) {
		try {
			return orderService.exportOrders(orders);
		} catch (IOException e) {
			log.error("IOExcepton", e);
		} catch (IllegalAccessException e) {
			log.error("IllegalAccessException", e);
		} catch (NoSuchFieldException e) {
			log.error("NoSuchFieldException", e);
		}
		return false;
	}

	@Override
	public synchronized boolean importOrders() {
		try {
			return orderService.importOrders();
		} catch (FileNotFoundException e) {
			log.error("FileNotFound", e);
		} catch (IOException e) {
			log.error("IOExcepton", e);
		} catch (IllegalAccessException e) {
			log.error("IllegalAccessException", e);
		} catch (NoSuchFieldException e) {
			log.error("NoSuchFieldException", e);
		} catch (InstantiationException e) {
			log.error("InstantiationException", e);
		} catch (InvocationTargetException e) {
			log.error("InvocationTargetException", e);
		} catch (NoSuchMethodException e) {
			log.error("NoSuchMethodException", e);
		} catch (ClassNotFoundException e) {
			log.error("ClassNotFoundException", e);
		}
		return false;
	}
}
