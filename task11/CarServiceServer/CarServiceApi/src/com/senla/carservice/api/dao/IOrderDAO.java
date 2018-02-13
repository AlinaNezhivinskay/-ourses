package com.senla.carservice.api.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.senla.carservice.api.dao.genericdao.IGenericDAO;
import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.model.orderstate.OrderState;
import com.senla.carservice.model.sortfields.order.SortOrderFields;

public interface IOrderDAO extends IGenericDAO<Order> {
	boolean updateOrderState(Order order, OrderState state) throws SQLException;

	List<Order> getAll(SortOrderFields field, boolean desc) throws SQLException;

	List<Order> getOrdersByState(OrderState state) throws SQLException;

	List<Order> getOrdersByStateAndPeriod(OrderState state, Date startTimePeriod, Date endTimePeriod)
			throws SQLException;

	Order getOrderByMaster(Master master) throws SQLException;

	Master getMasterByOrder(Order order) throws SQLException;

	int getFreeGarageNumber(Date date) throws SQLException;

	int getFreeMasterNumber(Date date) throws SQLException;

	boolean assignMasterToOrder(Order order, Master master) throws SQLException;

	boolean assignGarageToOrder(Order order, Garage garage) throws SQLException;

	List<Long> getExistingId(String idListStr) throws SQLException;
}
