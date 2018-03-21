package com.senla.carservice.controller.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.senla.carservice.api.dao.IUserDAO;
import com.senla.carservice.api.services.IUserService;
import com.senla.carservice.connection.HibernateUtil;
import com.senla.carservice.dao.UserDAO;
import com.senla.carservice.model.beans.User;

public class UserService implements IUserService {
	private static Logger log = Logger.getLogger(UserService.class.getName());
	private IUserDAO userDAO;
	private final HibernateUtil hibernateUtil = HibernateUtil.getInstance();

	public UserService() {
		userDAO = new UserDAO();
	}

	@Override
	public synchronized List<User> getUsers() {
		List<User> users = null;
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			users = userDAO.getAll(User.class, session, null);
			transaction.commit();
			return users;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.error("Exception", e);
		}
		return users;
	}

	@Override
	public synchronized User getUserById(Long id) {
		User user = null;
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			user = userDAO.read(User.class, session, id);
			transaction.commit();
			return user;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.error("Exception", e);
		}
		return user;
	}

	@Override
	public synchronized void addUser(User user) {
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			userDAO.create(session, user);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.error("Exception", e);
		}
	}

	@Override
	public synchronized boolean removeUser(User user) {
		boolean result = false;
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			result = userDAO.delete(session, user);
			transaction.commit();
			return result;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.error("Exception", e);
		}
		return result;
	}

	@Override
	public synchronized boolean updateUser(User user, String login, String password) {
		user.setLogin(login);
		user.setPassword(password);
		boolean result = false;
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			result = userDAO.update(session, user);
			transaction.commit();
			return result;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.error("Exception", e);
		}
		return result;
	}

	@Override
	public User getUserByLogin(String login) {
		User user;
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			user = userDAO.getUserByLogin(session, login);
			transaction.commit();
			return user;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.error("Exception", e);
		}
		return null;
	}
}
