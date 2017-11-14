package com.senla.carservice.controller.services;

import java.util.ArrayList;
import java.util.List;

import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.model.comparators.entity.IdComparator;
import com.senla.carservice.repository.repositories.GarageRepository;
import com.senla.carservice.api.repositories.IGarageRepository;
import com.senla.carservice.api.services.IGarageService;

public class GarageService implements IGarageService {
	private IGarageRepository garageRepository;

	public GarageService(String fileName) {
		garageRepository = new GarageRepository(fileName);
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
	public void safeToFile() throws Exception {
		garageRepository.getGarages().sort(new IdComparator());
		garageRepository.safeToFile();
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
	public void readFromFile() throws Exception {
		garageRepository.readFromFile();

	}

	@Override
	public List<Garage> getGarages() {
		return garageRepository.getGarages();
	}

	@Override
	public Garage getGarageById(long id) {
		return garageRepository.getGarage(id);
	}

}
