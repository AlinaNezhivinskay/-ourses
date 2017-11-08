package com.senla.carservice.services;

import com.senla.carservice.beans.Garage;
import com.senla.carservice.repositories.GarageRepository;
import com.senla.carservice.services.api.IGarageService;
import com.senla.carservice.utils.ArrayWorker;

public class GarageService implements IGarageService {
	private GarageRepository garageRepository;

	public GarageService(String fileName) {
		this.garageRepository = new GarageRepository(fileName);
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
	public boolean updateGarage(Garage garage,boolean isFree) {
		return garageRepository.updateGarage(garage,isFree);
	}

	@Override
	public void safeToFile() {
		garageRepository.safeToFile();
	}

	@Override
	public int getFreeGarageNumber() {
		int freeGaragesNum = 0;
		for (int i = 0; i < garageRepository.getGarages().length; i++) {
			if (garageRepository.getGarages()[i] == null) {
				continue;
			}
			if (garageRepository.getGarages()[i].getIsFree())
				freeGaragesNum++;
		}
		return freeGaragesNum;
	}

	@Override
	public Garage[] getFreeGarages() {
		Garage[] freeGarages = new Garage[ArrayWorker.ARRAY_LENGTH];
		for (int i = 0; i < garageRepository.getGarages().length; i++) {
			if(garageRepository.getGarages()[i]==null)continue;
			if (garageRepository.getGarages()[i].getIsFree()) {
				if (!ArrayWorker.addElementInArray(freeGarages, garageRepository.getGarages()[i])) {
					freeGarages = (Garage[])ArrayWorker.expandArray(freeGarages);
					i--;
				}
			}
		}
		return freeGarages;
	}

	@Override
	public void readFromFile() {
		garageRepository.readFromFile();

	}

	@Override
	public Garage[] getGarages() {
		return garageRepository.getGarages();
	}

}
