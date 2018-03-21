package com.senla.carservice.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.senla.carservice.api.dao.IUserDAO;
import com.senla.carservice.model.beans.User;

public class UserDAO extends AbstractDAO<User> implements IUserDAO {

	@Override
	public User getUserByLogin(Session session, String login) {
		if (login == null) {
			return null;
		}
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("login", login));
		List result = criteria.list();
		User user = null;
		if (result != null) {
			user = (User) result.get(0);
		}
		return user;
	}

}
