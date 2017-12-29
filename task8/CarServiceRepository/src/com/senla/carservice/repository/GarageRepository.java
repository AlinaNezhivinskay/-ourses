package com.senla.carservice.repository;

import java.util.ArrayList;
import java.util.List;

import com.senla.carservice.api.repositories.IGarageRepository;
import com.senla.carservice.model.beans.Garage;

public class GarageRepository implements IGarageRepository {
	private static IGarageRepository instance;
	private static long lastId = 0;
	private List<Garage> garages;

	public static IGarageRepository getInstance() {
		if (instance == null) {
			instance = new GarageRepository();
		}
		return instance;
	}

	private GarageRepository() {
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
		if (garages.contains(garage)) {
			this.updateGarage(garage, garage.getIsFree());
			return true;
		}
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
	public void restoreData(List<Garage> garages) {
		this.garages = garages;
		lastId = garages.get(garages.size() - 1).getId();
		incrementLastId();
	}

	private void incrementLastId() {
		lastId += 10;
	}
}
