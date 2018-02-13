package com.senla.carservice.api.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.sortfields.master.SortMasterFields;

public interface IMasterService {
	void addMaster(Master master) throws SQLException;

	Master getMasterById(Long id) throws SQLException;

	boolean removeMaster(Master master) throws SQLException;

	boolean updateMaster(Master master, boolean isFree) throws SQLException;

	List<Master> getMasters() throws SQLException;

	int getFreeMastersNumber(Date date) throws SQLException;

	List<Master> getFreeMasters() throws SQLException;

	boolean exportMasters(List<Master> masters) throws IllegalAccessException, IOException, NoSuchFieldException;

	boolean importMasters() throws FileNotFoundException, IOException, IllegalAccessException, NoSuchFieldException,
			InstantiationException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException,
			SQLException;

	List<Master> sort(SortMasterFields field, boolean desc) throws SQLException;
}
