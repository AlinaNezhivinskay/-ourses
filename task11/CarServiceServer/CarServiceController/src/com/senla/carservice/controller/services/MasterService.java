package com.senla.carservice.controller.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import com.senla.carservice.api.dao.IMasterDAO;
import com.senla.carservice.api.services.IMasterService;
import com.senla.carservice.csv.fileworker.CsvFileWorker;
import com.senla.carservice.dao.MasterDAO;
import com.senla.carservice.jdbc.connection.DBConnector;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.model.sortfields.master.SortMasterFields;

public class MasterService implements IMasterService {
	private IMasterDAO masterDAO;
	private final DBConnector dBconnector = DBConnector.getInstance();

	public MasterService() {
		this.masterDAO = new MasterDAO();
	}

	@Override
	public synchronized void addMaster(Master master) throws Exception {
		masterDAO.create(dBconnector.getConnection(), master);
	}

	@Override
	public synchronized boolean removeMaster(Master master) throws Exception {
		return masterDAO.delete(dBconnector.getConnection(), master);
	}

	@Override
	public synchronized boolean updateMaster(Master master, boolean isFree) throws Exception {
		master.setIsFree(isFree);
		return masterDAO.update(dBconnector.getConnection(), master);
	}

	@Override
	public List<Master> getMasters() throws Exception {
		return masterDAO.getAll(dBconnector.getConnection(), null);
	}

	@Override
	public int getFreeMastersNumber(Date date) throws Exception {
		return masterDAO.getFreeMasterNum(dBconnector.getConnection());
	}

	@Override
	public List<Master> sort(SortMasterFields field) throws Exception {
		return masterDAO.getAll(dBconnector.getConnection(), field.name());

	}

	@Override
	public Master getMasterById(Long id) throws Exception {
		return masterDAO.read(dBconnector.getConnection(), id);
	}

	@Override
	public List<Master> getFreeMasters() throws Exception {
		return masterDAO.getFreeMasters(dBconnector.getConnection());
	}

	@Override
	public Master getMasterByOrder(Order order) throws Exception {
		return masterDAO.getMasterByOrder(dBconnector.getConnection(), order);
	}

	@Override
	public boolean exportMasters(List<Master> masters)
			throws IllegalAccessException, IOException, NoSuchFieldException {
		return CsvFileWorker.safeToFile(masters);
	}

	@Override
	public synchronized boolean importMasters() throws FileNotFoundException, IOException, IllegalAccessException,
			NoSuchFieldException, InstantiationException, InvocationTargetException, NoSuchMethodException,
			ClassNotFoundException, Exception {
		@SuppressWarnings("unchecked")
		List<Master> masters = (List<Master>) CsvFileWorker.readFromFileMaster();
		List<Master> dbMasters = masterDAO.getAll(dBconnector.getConnection(), null);
		for (Master master : masters) {
			if (dbMasters.contains(master)) {
				masterDAO.update(dBconnector.getConnection(), master);
			} else {
				masterDAO.create(dBconnector.getConnection(), master);
			}
		}
		return true;
	}

}
