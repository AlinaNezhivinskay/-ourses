package com.senla.carservice.api.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.senla.carservice.api.dao.genericdao.GenericDAO;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.model.orderstate.OrderState;

public interface IOrderDAO extends GenericDAO<Order> {
	List<Order> getOrdersByState(Session session, OrderState state, String sort);

	List<Order> getOrdersByStateAndPeriod(Session session, OrderState state, Date startTimePeriod, Date endTimePeriod);

	Order getOrderByMaster(Session session, Master master);

	int getExecutedOnDateOrdersNum(Session session, Date date);

	Master getMasterByOrder(Session session, Order order);
}
