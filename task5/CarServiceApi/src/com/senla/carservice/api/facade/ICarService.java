package com.senla.carservice.api.facade;

import java.util.Date;
import java.util.List;

import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.model.orderstate.OrderState;

public interface ICarService {
	public void addGarage(Garage garage);

	public boolean removeGarage(Garage garage);

	public void updateGarage(Garage garage, boolean isFree);

	public List<Garage> getGarages();

	public Garage getGarageById(long id);

	public void addMaster(Master master);

	public boolean removeMaster(Master master);

	public void updateMaster(Master master, boolean isFree);

	public List<Master> getMasters();

	public List<Master> getFreeMasters();

	public Master getMasterById(long id);

	public void addOrder(Order order);

	public boolean removeOrder(Order order);

	public void closeOrder(Order order);

	public void cancelOrder(Order order);

	public void updateOrder(Order order, OrderState state);

	public void shiftExecution(int daysNum);

	public void assignMasterToOrder(Order order, Master master);

	public List<Garage> getFreeGarages();

	public List<Order> getOrders();

	public Order getOrderById(long id);

	public List<Order> getCurrentExecutingOrders();

	public Order getOrderByMaster(Master master);

	public Master getMasterByOrder(Order order);

	public List<Order> getExecutedOrders(Date startDate, Date endDate);

	public List<Order> getCanceledOrders(Date startDate, Date endDate);

	public List<Order> getRemoteOrders(Date startDate, Date endDate);

	public int getFreeCarServicePlaceNum(Date date);

	public Date getFreeDate();

	public void sortOrdersBySubmissionDate();

	public void sortOrdersByExecutionDate();

	public void sortOrdersByPlannedStartDate();

	public void sortOrdersByPrice();

	public void sortMastersByAlphabetAscending();

	public void sortMastersByAlphabetDescending();

	public void sortMastersByEmployment();

	public void sortExecutingOrdersBySubmissionDate();

	public void sortExecutingOrdersByExecutionDate();

	public void sortExecutingOrdersByPrice();

	public void sortOrdersBySubmissionDate(List<Order> orders);

	public void sortOrdersByExecutionDate(List<Order> orders);

	public void sortOrdersByPrice(List<Order> orders);

	public boolean exit();

	public boolean loadData();
}
