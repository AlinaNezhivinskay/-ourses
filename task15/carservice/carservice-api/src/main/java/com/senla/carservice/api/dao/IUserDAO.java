package com.senla.carservice.api.dao;

import org.hibernate.Session;

import com.senla.carservice.api.dao.genericdao.GenericDAO;
import com.senla.carservice.model.beans.User;

public interface IUserDAO extends GenericDAO<User> {
	User getUserByLogin(Session session, String login);
}
