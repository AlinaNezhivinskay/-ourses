package com.senla.carservice.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.senla.carservice.api.dao.IOrderDAO;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.model.orderstate.OrderState;

public class OrderDAO extends AbstractDAO<Order> implements IOrderDAO {

	@Override
	public List<Order> getOrdersByState(Session session, OrderState state, String sort) {

		Criteria criteria = session.createCriteria(Order.class);
		criteria.add(Restrictions.eq("state", state));
		if (sort != null) {
			criteria.addOrder(org.hibernate.criterion.Order.asc(sort));
		}
		return criteria.list();
	}

	@Override
	public List<Order> getOrdersByStateAndPeriod(Session session, OrderState state, java.util.Date startTimePeriod,
			java.util.Date endTimePeriod) {
		Criteria criteria = session.createCriteria(Order.class);
		criteria.add(Restrictions.eq("state", state));
		criteria.add(Restrictions.between("submissionDate", startTimePeriod, endTimePeriod));
		return criteria.list();
	}

	@Override
	public Order getOrderByMaster(Session session, Master master) {
		if (master == null) {
			return null;
		}
		Criteria criteria = session.createCriteria(Order.class);
		criteria.add(Restrictions.eq("master", master));
		List result = criteria.list();
		Order order = null;
		if (result != null) {
			order = (Order) result.get(0);
		}
		return order;
	}
	@Override
	public Master getMasterByOrder(Session session, Order order) {
		if (order == null) {
			return null;
		}
		Criteria criteria = session.createCriteria(Order.class);
		criteria.add(Restrictions.eq("id", order.getId()));
		criteria.add(Restrictions.eq("state", OrderState.EXECUTABLE));
		List result = criteria.list();
		Order dbOrder = null;
		if (result != null) {
			order = (Order) result.get(0);
		}
		return dbOrder.getMaster();
	}

	@Override
	public int getExecutedOnDateOrdersNum(Session session, java.util.Date date) {
		int ordersNum = 0;
		Criteria criteria = session.createCriteria(Order.class);
		criteria.setProjection(Projections.rowCount());
		criteria.add(Restrictions.eq("state", OrderState.EXECUTABLE));
		criteria.add(Restrictions.lt("executionDate", date));
		List result = criteria.list();
		if (result != null) {
			ordersNum = (int) result.get(0);
		}

		return ordersNum;
	}
}
