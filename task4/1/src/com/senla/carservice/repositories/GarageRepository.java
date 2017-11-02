package com.senla.carservice.repositories;

import com.danco.training.TextFileWorker;
import com.senla.carservice.beans.Garage;
import com.senla.carservice.utils.ArrayWorker;
import com.senla.carservice.utils.Converter;

public class GarageRepository {
	private TextFileWorker garageFileWorker;
	private static long lastId = 0;
	private static Garage[] garages;

	public GarageRepository(String fileName) {
		if (fileName != null) {
			garageFileWorker = new TextFileWorker(fileName);
		} else {
			garageFileWorker = new TextFileWorker("E:/учёба Алины/Courses/task4/1/garages.txt");
		}
		garages = new Garage[ArrayWorker.ARRAY_LENGTH];
	}

	public Garage[] getGarages() {
		return garages;
	}

	public static Garage getGarage(long id) {
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

	public boolean updateGarage(Garage garage, boolean isFree) {
		if (!ArrayWorker.isElementOnArray(garages, garage))
			return false;
		garages[ArrayWorker.getPositionOfElement(garages, garage)].setIsFree(isFree);
		return true;
	}

	public void safeToFile() {
		garageFileWorker.writeToFile(Converter.convertGaragesToStrings(garages));
	}

	public void readFromFile() {
		garages = Converter.convertStringsToGarages(garageFileWorker.readFromFile());
		lastId = garages[garages.length - 1].getId();
		incrementLastId();
	}

	private void incrementLastId() {
		lastId += 10;
	}
}
