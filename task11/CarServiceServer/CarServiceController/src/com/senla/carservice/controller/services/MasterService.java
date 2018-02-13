package com.senla.carservice.controller.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.senla.carservice.api.dao.IMasterDAO;
import com.senla.carservice.api.services.IMasterService;
import com.senla.carservice.csv.fileworker.CsvFileWorker;
import com.senla.carservice.dao.MasterDAO;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.sortfields.master.SortMasterFields;

public class MasterService implements IMasterService {
	private IMasterDAO masterDAO;

	public MasterService() {
		this.masterDAO = MasterDAO.getInstance();
	}

	@Override
	public synchronized void addMaster(Master master) throws SQLException {
		masterDAO.create(master);
	}

	@Override
	public synchronized boolean removeMaster(Master master) throws SQLException {
		return masterDAO.delete(master);
	}

	@Override
	public synchronized boolean updateMaster(Master master, boolean isFree) throws SQLException {
		return masterDAO.update(master);
	}

	@Override
	public List<Master> getMasters() throws SQLException {
		return masterDAO.getAll();
	}

	@Override
	public int getFreeMastersNumber(Date date) throws SQLException {
		return masterDAO.getFreeMasterNum();
	}

	@Override
	public List<Master> sort(SortMasterFields field, boolean desc) throws SQLException {
		return masterDAO.getAll(field, desc);

	}

	@Override
	public Master getMasterById(Long id) throws SQLException {
		return masterDAO.read(id);
	}

	@Override
	public List<Master> getFreeMasters() throws SQLException {
		return masterDAO.getFreeMasters();
	}

	@Override
	public boolean exportMasters(List<Master> masters)
			throws IllegalAccessException, IOException, NoSuchFieldException {
		return CsvFileWorker.safeToFile(masters);
	}

	@Override
	public synchronized boolean importMasters() throws FileNotFoundException, IOException, IllegalAccessException,
			NoSuchFieldException, InstantiationException, InvocationTargetException, NoSuchMethodException,
			ClassNotFoundException, SQLException {
		@SuppressWarnings("unchecked")
		List<Master> masters = (List<Master>) CsvFileWorker.readFromFileMaster();
		StringBuilder idStr = new StringBuilder();
		for (Master master : masters) {
			idStr.append(master.getId()).append(", ");
		}
		idStr.append(-1);
		List<Long> existingId = masterDAO.getExistingId(idStr.toString());
		for (Master master : masters) {
			if (existingId.contains((Long) master.getId())) {
				masterDAO.update(master);
			} else {
				masterDAO.create(master);
			}
		}
		return true;
	}

}
