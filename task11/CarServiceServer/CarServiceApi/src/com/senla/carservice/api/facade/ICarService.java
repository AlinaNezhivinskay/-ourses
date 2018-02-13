package com.senla.carservice.api.facade;

import java.util.Date;
import java.util.List;

import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.beans.Order;

public interface ICarService {
	void addGarage(Garage garage);

	boolean removeGarage(Garage garage);

	List<Garage> getGarages();

	Garage getGarageById(Long id);

	void addMaster(Master master);

	boolean removeMaster(Master master);

	List<Master> getMasters();

	List<Master> getFreeMasters();

	Master getMasterById(Long id);

	void addOrder(Order order);

	boolean removeOrder(Order order);

	boolean closeOrder(Order order);

	boolean cancelOrder(Order order);

	void shiftExecution(int daysNum);

	boolean assignMasterToOrder(Order order, Master master);

	List<Garage> getFreeGarages();

	List<Order> getOrders();

	Order getOrderById(Long id);

	List<Order> getCurrentExecutingOrders();

	Order getOrderByMaster(Master master);

	Master getMasterByOrder(Order order);

	List<Order> getExecutedOrders(Date startDate, Date endDate);

	List<Order> getCanceledOrders(Date startDate, Date endDate);

	List<Order> getRemoteOrders(Date startDate, Date endDate);

	int getFreeCarServicePlaceNum(Date date);

	Date getFreeDate();

	List<Order> sortOrdersBySubmissionDate();

	List<Order> sortOrdersByExecutionDate();

	List<Order> sortOrdersByPlannedStartDate();

	List<Order> sortOrdersByPrice();

	List<Master> sortMastersByAlphabetAscending();

	List<Master> sortMastersByAlphabetDescending();

	List<Master> sortMastersByEmployment();

	boolean exit();

	Order getCopy(Order order);

	boolean updateOrder(Order order);

	boolean assignGarageToOrder(Order order, Garage garage);

	boolean exportGarages(List<Garage> garages);

	boolean importGarages();

	boolean exportMasters(List<Master> masters);

	boolean importMasters();

	boolean exportOrders(List<Order> orders);

	boolean importOrders();
}
