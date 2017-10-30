package com.senla.carservice.repositories;

import com.danco.training.TextFileWorker;
import com.senla.carservice.beans.Garage;
import com.senla.carservice.utils.ArrayWorker;
import com.senla.carservice.utils.Converter;

public class GarageRepository {
	private static final TextFileWorker GARAGE_FILE_WORKER = new TextFileWorker(
			"E:/учёба Алины/Courses/task4/1/garages.txt");
	private static long lastId = 0;
	private Garage[] garages;

	public GarageRepository() {
		garages = new Garage[ArrayWorker.ARRAY_LENGTH];
	}

	public Garage[] getGarages() {
		return garages;
	}

	public Garage getGarage(long id) {
		for (int i = 0; i < garages.length; i++) {
			if (garages[i].getId() == id)
				return garages[i];
		}
		return null;
	}

	public void addGarage(Garage garage) {
		if (!ArrayWorker.isEmptySpace(garages)) {
			garages = (Garage[]) ArrayWorker.expandArray(garages);
		}
		garage.setId(lastId);
		incrementLastId();
		garages[ArrayWorker.getFreePosition(garages)] = garage;
	}

	public boolean removeGarage(Garage garage) {
		if (!ArrayWorker.isElementOnArray(garages, garage))
			return false;
		garages[ArrayWorker.getPositionOfElement(garages, garage)] = null;
		return true;
	}

	public boolean updateGarage(Garage garage) {
		if (!ArrayWorker.isElementOnArray(garages, garage))
			return false;
		garages[ArrayWorker.getPositionOfElement(garages, garage)] = garage;
		return true;
	}

	public void safeToFile() {
		GARAGE_FILE_WORKER.writeToFile(Converter.convertGaragesToStrings(garages));
	}

	public void readFromFile() {
		garages = Converter.convertStringsToGarages(GARAGE_FILE_WORKER.readFromFile());
	}

	private void incrementLastId() {
		lastId += 10;
	}
}
