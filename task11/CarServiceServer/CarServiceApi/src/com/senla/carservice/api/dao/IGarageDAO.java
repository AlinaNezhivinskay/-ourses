package com.senla.carservice.api.dao;

import java.sql.Connection;
import java.util.List;

import com.senla.carservice.api.dao.genericdao.IGenericDAO;
import com.senla.carservice.model.beans.Garage;

public interface IGarageDAO extends IGenericDAO<Garage> {

	int getFreeGarageNum(Connection connection) throws Exception;

	List<Garage> getFreeGarages(Connection connection) throws Exception;
}
