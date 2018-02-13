package com.senla.carservice.api.dao;

import java.sql.SQLException;
import java.util.List;

import com.senla.carservice.api.dao.genericdao.IGenericDAO;
import com.senla.carservice.model.beans.Garage;

public interface IGarageDAO extends IGenericDAO<Garage> {

	int getFreeGarageNum() throws SQLException;

	List<Garage> getFreeGarages() throws SQLException;

	List<Long> getExistingId(String idListStr) throws SQLException;
}
