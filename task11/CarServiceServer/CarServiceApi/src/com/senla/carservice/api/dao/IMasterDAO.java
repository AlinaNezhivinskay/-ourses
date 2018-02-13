package com.senla.carservice.api.dao;

import java.sql.SQLException;
import java.util.List;

import com.senla.carservice.api.dao.genericdao.IGenericDAO;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.sortfields.master.SortMasterFields;

public interface IMasterDAO extends IGenericDAO<Master> {
	int getFreeMasterNum() throws SQLException;

	List<Master> getFreeMasters() throws SQLException;

	List<Long> getExistingId(String idListStr) throws SQLException;

	List<Master> getAll(SortMasterFields field, boolean desc) throws SQLException;
}
