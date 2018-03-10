package com.senla.carservice.api.dao;

import java.util.List;

import org.hibernate.Session;

import com.senla.carservice.api.dao.genericdao.GenericDAO;
import com.senla.carservice.model.beans.Garage;

public interface IGarageDAO extends GenericDAO<Garage> {

	int getFreeGarageNum(Session session);

	List<Garage> getFreeGarages(Session session);
}
