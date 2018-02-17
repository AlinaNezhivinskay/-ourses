package com.senla.carservice.api.dao;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import com.senla.carservice.api.dao.genericdao.IGenericDAO;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.model.orderstate.OrderState;

public interface IOrderDAO extends IGenericDAO<Order> {
	List<Order> getOrdersByState(Connection connection, OrderState state, String sort) throws Exception;

	List<Order> getOrdersByStateAndPeriod(Connection connection, OrderState state, Date startTimePeriod,
			Date endTimePeriod) throws Exception;

	Order getOrderByMaster(Connection connection, Master master) throws Exception;

	int getExecutedOnDateOrdersNum(Connection connection, Date date) throws Exception;
}
