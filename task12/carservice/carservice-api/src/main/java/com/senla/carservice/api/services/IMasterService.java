package com.senla.carservice.api.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.sortfields.master.SortMasterFields;

public interface IMasterService {
	void addMaster(Master master) throws Exception;

	Master getMasterById(Long id) throws Exception;

	boolean removeMaster(Master master) throws Exception;

	boolean updateMaster(Master master, boolean isFree) throws Exception;

	List<Master> getMasters() throws Exception;

	int getFreeMastersNumber(Date date) throws Exception;

	List<Master> getFreeMasters() throws Exception;

	boolean exportMasters(List<Master> masters) throws IllegalAccessException, IOException, NoSuchFieldException;

	boolean importMasters() throws Exception;

	List<Master> sort(SortMasterFields field) throws Exception;
}
