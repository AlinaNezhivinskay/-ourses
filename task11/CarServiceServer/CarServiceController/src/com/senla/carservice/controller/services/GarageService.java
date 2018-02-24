package com.senla.carservice.controller.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.senla.caerservice.dependencyinjection.coordinationrepository.CoordinationRepository;
import com.senla.carservice.api.dao.IGarageDAO;
import com.senla.carservice.api.services.IGarageService;
import com.senla.carservice.csv.fileworker.CsvFileWorker;
import com.senla.carservice.jdbc.connection.DBConnector;
import com.senla.carservice.model.beans.Garage;

public class GarageService implements IGarageService {
	private IGarageDAO garageDAO;
	private final DBConnector dBconnector = DBConnector.getInstance();

	public GarageService() {
		garageDAO = (IGarageDAO) CoordinationRepository.getInstance().getObject(IGarageDAO.class);
	}

	@Override
	public synchronized void addGarage(Garage garage) throws Exception {
		garageDAO.create(dBconnector.getConnection(), garage);
	}

	@Override
	public synchronized boolean removeGarage(Garage garage) throws Exception {
		return garageDAO.delete(dBconnector.getConnection(), garage);
	}

	@Override
	public synchronized boolean updateGarage(Garage garage, boolean isFree) throws Exception {
		garage.setIsFree(isFree);
		return garageDAO.update(dBconnector.getConnection(), garage);
	}

	@Override
	public int getFreeGarageNumber() throws Exception {
		return garageDAO.getFreeGarageNum(dBconnector.getConnection());
	}

	@Override
	public List<Garage> getFreeGarages() throws Exception {
		return garageDAO.getFreeGarages(dBconnector.getConnection());
	}

	@Override
	public List<Garage> getGarages() throws Exception {
		return garageDAO.getAll(dBconnector.getConnection(), null);
	}

	@Override
	public Garage getGarageById(Long id) throws Exception {
		return garageDAO.read(dBconnector.getConnection(), id);
	}

	@Override
	public boolean exportGarages(List<Garage> garages)
			throws IllegalAccessException, IOException, NoSuchFieldException {
		return CsvFileWorker.safeToFile(garages);
	}

	@Override
	public synchronized boolean importGarages() throws FileNotFoundException, IOException, IllegalAccessException,
			NoSuchFieldException, InstantiationException, InvocationTargetException, NoSuchMethodException,
			ClassNotFoundException, Exception {
		@SuppressWarnings("unchecked")
		List<Garage> garages = (List<Garage>) CsvFileWorker.readFromFileGarage();
		List<Garage> dbGarages = garageDAO.getAll(dBconnector.getConnection(), null);
		for (Garage garage : garages) {
			if (dbGarages.contains(garage)) {
				garageDAO.update(dBconnector.getConnection(), garage);
			} else {
				garageDAO.create(dBconnector.getConnection(), garage);
			}
		}
		return true;
	}

}
