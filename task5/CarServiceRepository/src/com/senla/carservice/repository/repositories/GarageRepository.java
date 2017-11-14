package com.senla.carservice.repository.repositories;

import java.util.ArrayList;
import java.util.List;

import com.danco.training.TextFileWorker;
import com.senla.carservice.api.repositories.IGarageRepository;
import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.util.utils.Converter;

public class GarageRepository implements IGarageRepository {
	private TextFileWorker garageFileWorker;
	private static long lastId = 0;
	private List<Garage> garages;

	public GarageRepository(String fileName) {
		if (fileName != null) {
			garageFileWorker = new TextFileWorker(fileName);
		} else {
			garageFileWorker = new TextFileWorker("garages.txt");
		}
		garages = new ArrayList<Garage>();
	}

	@Override
	public List<Garage> getGarages() {
		return garages;
	}

	@Override
	public Garage getGarage(long id) {
		for (Garage garage : garages) {
			if (garage.getId() == id)
				return garage;
		}
		return null;
	}

	@Override
	public boolean addGarage(Garage garage) {
		garage.setId(lastId);
		incrementLastId();
		return garages.add(garage);
	}

	@Override
	public boolean removeGarage(Garage garage) {
		return garages.remove(garage);
	}

	@Override
	public boolean updateGarage(Garage garage, boolean isFree) {
		if (!garages.contains(garage))
			return false;
		garages.get(garages.indexOf(garage)).setIsFree(isFree);
		return true;
	}

	@Override
	public void safeToFile() throws Exception{
		garageFileWorker.writeToFile(Converter.convertGaragesToStrings(garages));
	}

	@Override
	public void readFromFile() throws Exception {
		garages = Converter.convertStringsToGarages(garageFileWorker.readFromFile());
		lastId = garages.get(garages.size() - 1).getId();
		incrementLastId();
	}

	private void incrementLastId() {
		lastId += 10;
	}
}
