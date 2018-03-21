package com.senla.carservice.api.dao;

import java.util.List;

import org.hibernate.Session;

import com.senla.carservice.api.dao.genericdao.GenericDAO;
import com.senla.carservice.model.beans.Master;

public interface IMasterDAO extends GenericDAO<Master> {
	int getFreeMasterNum(Session session);

	List<Master> getFreeMasters(Session session);
}
