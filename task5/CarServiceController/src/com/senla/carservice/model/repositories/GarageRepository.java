package com.senla.carservice.model.repositories;

import java.util.ArrayList;

import com.danco.training.TextFileWorker;
import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.model.utils.Converter;

public class GarageRepository {
	private TextFileWorker garageFileWorker;
	private static long lastId = 0;
	private ArrayList<Garage> garages;
	private static GarageRepository instance;

	public static GarageRepository getInstance(String fileName) {
		if (instance == null) {
			instance = new GarageRepository(fileName);
		}
		return instance;
	}

	public GarageRepository(String fileName) {
		if (fileName != null) {
			garageFileWorker = new TextFileWorker(fileName);
		} else {
			garageFileWorker = new TextFileWorker("garages.txt");
		}
		garages = new ArrayList<Garage>();
	}

	public ArrayList<Garage> getGarages() {
		return garages;
	}

	public Garage getGarage(long id) {
		for (Garage garage : garages) {
			if (garage.getId() == id)
				return garage;
		}
		return null;
	}

	public boolean addGarage(Garage garage) {
		garage.setId(lastId);
		incrementLastId();
		return garages.add(garage);
	}

	public boolean removeGarage(Garage garage) {
		return garages.remove(garage);
	}

	public boolean updateGarage(Garage garage, boolean isFree) {
		if (!garages.contains(garage))
			return false;
		garages.get(garages.indexOf(garage)).setIsFree(isFree);
		return true;
	}

	public void safeToFile() {
		garageFileWorker.writeToFile(Converter.convertGaragesToStrings(garages));
	}

	public void readFromFile() {
		garages = Converter.convertStringsToGarages(garageFileWorker.readFromFile());
		lastId = garages.get(garages.size() - 1).getId();
		incrementLastId();
	}

	private void incrementLastId() {
		lastId += 10;
	}
}
