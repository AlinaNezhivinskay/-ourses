package com.senla.carservice.controller.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import com.senla.caerservice.dependencyinjection.coordinationrepository.CoordinationRepository;
import com.senla.carservice.api.dao.IGarageDAO;
import com.senla.carservice.api.services.IGarageService;
import com.senla.carservice.csv.fileworker.CsvFileWorker;
import com.senla.carservice.model.beans.Garage;

public class GarageService implements IGarageService {
	private IGarageDAO garageDAO;

	public GarageService() {
		garageDAO = (IGarageDAO) CoordinationRepository.getInstance().getObject(IGarageDAO.class);
	}

	@Override
	public synchronized void addGarage(Garage garage) throws SQLException {
		garageDAO.create(garage);
	}

	@Override
	public synchronized boolean removeGarage(Garage garage) throws SQLException {
		return garageDAO.delete(garage);
	}

	@Override
	public synchronized boolean updateGarage(Garage garage, boolean isFree) throws SQLException {
		return garageDAO.update(garage);
	}

	@Override
	public int getFreeGarageNumber() throws SQLException {
		return garageDAO.getFreeGarageNum();
	}

	@Override
	public List<Garage> getFreeGarages() throws SQLException {
		return garageDAO.getFreeGarages();
	}

	@Override
	public List<Garage> getGarages() throws SQLException {
		return garageDAO.getAll();
	}

	@Override
	public Garage getGarageById(Long id) throws SQLException {
		return garageDAO.read(id);
	}

	@Override
	public boolean exportGarages(List<Garage> garages)
			throws IllegalAccessException, IOException, NoSuchFieldException {
		return CsvFileWorker.safeToFile(garages);
	}

	@Override
	public synchronized boolean importGarages() throws FileNotFoundException, IOException, IllegalAccessException,
			NoSuchFieldException, InstantiationException, InvocationTargetException, NoSuchMethodException,
			ClassNotFoundException, SQLException {
		@SuppressWarnings("unchecked")
		List<Garage> garages = (List<Garage>) CsvFileWorker.readFromFileGarage();
		StringBuilder idStr = new StringBuilder();
		for (Garage garage : garages) {
			idStr.append(garage.getId()).append(", ");
		}
		idStr.append(-1);
		List<Long> existingId = garageDAO.getExistingId(idStr.toString());
		for (Garage garage : garages) {
			if (existingId.contains((Long) garage.getId())) {
				garageDAO.update(garage);
			} else {
				garageDAO.create(garage);
			}
		}
		return true;
	}

}
