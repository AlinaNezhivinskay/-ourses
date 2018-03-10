package com.senla.carservice.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.senla.carservice.api.dao.genericdao.GenericDAO;
import com.senla.carservice.model.beans.abstractentity.Entity;

public abstract class AbstractDAO<T extends Entity> implements GenericDAO<T> {

	@Override
	public boolean create(Session session, T object) {
		if (object == null) {
			return false;
		}
		session.save(object);
		return true;
	}

	@Override
	public T read(Class<T> type, Session session, long key) {
		@SuppressWarnings("unchecked")
		T object = (T) session.get(type, key);
		return object;
	}

	@Override
	public boolean update(Session session, T object) throws Exception {
		if (object == null) {
			return false;
		}
		session.update(object);
		return true;
	}

	@Override
	public boolean saveOrUpdate(Session session, T object) throws Exception {
		if (object == null) {
			return false;
		}
		session.saveOrUpdate(object);
		return true; 
	}

	@Override
	public boolean delete(Session session, T object) {
		if (object == null) {
			return false;
		}
		session.delete(object);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll(Class<T> type, Session session, String sort) {
		Criteria criteria = session.createCriteria(type);
		if (sort != null) {
			criteria.addOrder(Order.asc(sort));
		}

		return criteria.list();
	}
}
