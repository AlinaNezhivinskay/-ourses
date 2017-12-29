package com.senla.carservice.controller.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.senla.caerservice.dependencyinjection.coordinationrepository.CoordinationRepository;
import com.senla.carservice.api.repositories.IGarageRepository;
import com.senla.carservice.api.services.IGarageService;
import com.senla.carservice.csv.fileworker.CsvFileWorker;
import com.senla.carservice.model.beans.Garage;

public class GarageService implements IGarageService {
	private IGarageRepository garageRepository;

	public GarageService() {
		garageRepository = (IGarageRepository) CoordinationRepository.getInstance().getObject(IGarageRepository.class);// GarageRepository.getInstance();
	}

	@Override
	public void addGarage(Garage garage) {
		garageRepository.addGarage(garage);
	}

	@Override
	public boolean removeGarage(Garage garage) {
		return garageRepository.removeGarage(garage);
	}

	@Override
	public boolean updateGarage(Garage garage, boolean isFree) {
		return garageRepository.updateGarage(garage, isFree);
	}

	@Override
	public int getFreeGarageNumber() {
		int freeGaragesNum = 0;
		for (Garage garage : garageRepository.getGarages()) {
			if (garage.getIsFree())
				freeGaragesNum++;
		}
		return freeGaragesNum;
	}

	@Override
	public List<Garage> getFreeGarages() {
		List<Garage> freeGarages = new ArrayList<Garage>();
		for (Garage garage : garageRepository.getGarages()) {
			if (garage.getIsFree()) {
				freeGarages.add(garage);
			}
		}
		return freeGarages;
	}

	@Override
	public void restoreData(List<Garage> garages) {
		garageRepository.restoreData(garages);
	}

	@Override
	public List<Garage> getGarages() {
		return garageRepository.getGarages();
	}

	@Override
	public Garage getGarageById(long id) {
		return garageRepository.getGarage(id);
	}

	@Override
	public boolean exportGarages(List<Garage> garages)
			throws IllegalAccessException, IOException, NoSuchFieldException {
		return CsvFileWorker.safeToFile(garages);
	}

	@Override
	public boolean importGarages()
			throws FileNotFoundException, IOException, IllegalAccessException, NoSuchFieldException,
			InstantiationException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
		@SuppressWarnings("unchecked")
		List<Garage> garages = (List<Garage>) CsvFileWorker.readFromFileGarage();
		for (Garage garage : garages) {
			garageRepository.addGarage(garage);
		}
		return true;
	}

}
