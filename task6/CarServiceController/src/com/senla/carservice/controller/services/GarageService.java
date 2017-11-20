package com.senla.carservice.controller.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.senla.carservice.api.repositories.IGarageRepository;
import com.senla.carservice.api.services.IGarageService;
import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.repository.GarageRepository;
import com.senla.carservice.util.utils.Converter;
import com.senla.carservice.util.utils.FileWorker;

public class GarageService implements IGarageService {
	private IGarageRepository garageRepository;

	public GarageService() {
		garageRepository = GarageRepository.getInstance();
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
	public boolean exportGarages(String filePath, List<Garage> garages) throws IOException {
		return FileWorker.writeFile(filePath, Converter.convertGaragesToStrings(garages));
	}

	@Override
	public boolean importGarages(String filePath) throws FileNotFoundException, IOException {
		List<Garage> garages = Converter.convertStringsToGarages(FileWorker.readFile(filePath));
		for (Garage garage : garages) {
			garageRepository.addGarage(garage);
		}
		return true;
	}

}
