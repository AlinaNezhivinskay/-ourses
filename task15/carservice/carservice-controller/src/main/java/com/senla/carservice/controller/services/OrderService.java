package com.senla.carservice.controller.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.senla.carservice.api.dao.IGarageDAO;
import com.senla.carservice.api.dao.IMasterDAO;
import com.senla.carservice.api.dao.IOrderDAO;
import com.senla.carservice.api.services.IOrderService;
import com.senla.carservice.connection.HibernateUtil;
import com.senla.carservice.csv.fileworker.CsvFileWorker;
import com.senla.carservice.dao.GarageDAO;
import com.senla.carservice.dao.MasterDAO;
import com.senla.carservice.dao.OrderDAO;
import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.model.orderstate.OrderState;
import com.senla.carservice.model.sortfields.order.SortOrderFields;
import com.senla.carservice.util.utils.DateWorker;

public class OrderService implements IOrderService {
	private IOrderDAO orderDAO;
	private IGarageDAO garageDAO;
	private IMasterDAO masterDAO;
	private final HibernateUtil hibernateUtil = HibernateUtil.getInstance();

	public OrderService() {
		this.orderDAO = new OrderDAO();
		this.garageDAO = new GarageDAO();
		this.masterDAO = new MasterDAO();
	}

	@Override
	public synchronized void addOrder(Order order) throws Exception {
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			orderDAO.create(session, order);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
	}

	@Override
	public synchronized boolean removeOrder(Order order) throws Exception {
		return updateOrderState(order, OrderState.REMOTE);
	}

	@Override
	public synchronized boolean closeOrder(Order order) throws Exception {
		return updateOrderState(order, OrderState.EXECUTED);

	}

	@Override
	public synchronized boolean cancelOrder(Order order) throws Exception {
		return updateOrderState(order, OrderState.CANCELED);
	}

	private synchronized boolean updateOrderState(Order order, OrderState state) throws Exception {
		order.setState(state);
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			boolean stateUpdate = false;
			transaction = session.beginTransaction();
			if (order != null) {
				boolean orderUpdate = orderDAO.update(session, order);
				Garage garage = order.getGarage();
				garage.setIsFree(true);
				boolean garageUpdate = garageDAO.update(session, garage);
				Master master = order.getMaster();
				master.setIsFree(true);
				boolean masterUpdate = true;
				if (master != null) {
					masterUpdate = masterDAO.update(session, master);
				}
				transaction.commit();
				if (orderUpdate && garageUpdate && masterUpdate) {
					stateUpdate = true;
				}
			}
			return stateUpdate;

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
	}

	@Override
	public synchronized void shiftExecution(int daysNum) throws Exception {
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			List<Order> orders = orderDAO.getAll(Order.class, session, null);
			for (Order order : orders) {
				order.setExecutionDate(DateWorker.shiftDate(order.getExecutionDate(), daysNum));
				orderDAO.update(session, order);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
	}

	@Override
	public List<Order> getOrders() throws Exception {
		List<Order> orders;
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			orders = orderDAO.getAll(Order.class, session, null);
			transaction.commit();
			return orders;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}

	}

	@Override
	public List<Order> getCurrentExecutingOrders() throws Exception {
		List<Order> orders;
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			orders = orderDAO.getOrdersByState(session, OrderState.EXECUTABLE, null);
			transaction.commit();
			return orders;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
	}

	@Override
	public List<Order> getOrders(OrderState state, Date startTimePeriod, Date endTimePeriod) throws Exception {
		List<Order> orders;
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			orders = orderDAO.getOrdersByStateAndPeriod(session, state, startTimePeriod, endTimePeriod);
			transaction.commit();
			return orders;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
	}

	@Override
	public List<Order> sort(SortOrderFields sortField) throws Exception {
		List<Order> orders;
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			orders = orderDAO.getAll(Order.class, session, sortField.name());
			transaction.commit();
			return orders;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
	}

	@Override
	public List<Order> sortOrders(OrderState state, SortOrderFields sortField) throws Exception {
		List<Order> orders;
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			orders = orderDAO.getOrdersByState(session, state, sortField.name());
			transaction.commit();
			return orders;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
	}

	@Override
	public Order getOrderByMaster(Master master) throws Exception {
		Order order;
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			order = orderDAO.getOrderByMaster(session, master);
			transaction.commit();
			return order;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
	}

	@Override
	public Master getMasterByOrder(Order order) throws Exception {
		Master master;
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			master = orderDAO.getMasterByOrder(session, order);
			transaction.commit();
			return master;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
	}

	@Override
	public int getFreeGarageNumber(Date date) throws Exception {
		int result = 0;
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			result = orderDAO.getExecutedOnDateOrdersNum(session, date);
			transaction.commit();
			return result;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
	}

	@Override
	public int getFreeMasterNumber(Date date) throws Exception {
		int result = 0;
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			result = orderDAO.getExecutedOnDateOrdersNum(session, date);
			transaction.commit();
			return result;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
	}

	@Override
	public Order getOrderById(Long id) throws Exception {
		Order order;
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			order = orderDAO.read(Order.class, session, id);
			transaction.commit();
			return order;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
	}

	@Override
	public synchronized boolean assignMasterToOrder(Order order, Master master) throws Exception {
		order.setMaster(master);
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			boolean stateUpdate = orderDAO.update(session, order);
			master.setIsFree(false);
			masterDAO.update(session, master);
			transaction.commit();
			return stateUpdate;

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
	}

	@Override
	public synchronized boolean assignGarageToOrder(Order order, Garage garage) throws Exception {
		order.setGarage(garage);
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			boolean stateUpdate = orderDAO.update(session, order);
			garage.setIsFree(false);
			garageDAO.update(session, garage);
			transaction.commit();
			return stateUpdate;

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
	}

	@Override
	public Order getCopy(Order order) throws CloneNotSupportedException {
		return order.clone();
	}

	@Override
	public synchronized boolean updateOrder(Order order) throws Exception {
		boolean result = false;
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			result = orderDAO.update(session, order);
			transaction.commit();
			return result;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
	}

	@Override
	public boolean exportOrders(List<Order> orders) throws IllegalAccessException, IOException, NoSuchFieldException {
		return CsvFileWorker.safeToFile(orders);
	}

	@Override
	public synchronized boolean importOrders() throws Exception {
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		@SuppressWarnings("unchecked")
		List<Order> orders = (List<Order>) CsvFileWorker.readFromFileOrder();
		try {
			transaction = session.beginTransaction();
			for (Order order : orders) {
				orderDAO.saveOrUpdate(session, order);
			}
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
	}

}
