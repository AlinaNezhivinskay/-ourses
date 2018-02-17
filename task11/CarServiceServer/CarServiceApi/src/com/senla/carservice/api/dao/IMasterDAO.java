package com.senla.carservice.api.dao;

import java.sql.Connection;
import java.util.List;

import com.senla.carservice.api.dao.genericdao.IGenericDAO;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.beans.Order;

public interface IMasterDAO extends IGenericDAO<Master> {
	int getFreeMasterNum(Connection connection) throws Exception;

	List<Master> getFreeMasters(Connection connection) throws Exception;

	Master getMasterByOrder(Connection connection, Order order) throws Exception;
}
