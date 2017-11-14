package com.senla.carservice.api.services;

import java.util.List;

import com.senla.carservice.model.beans.Garage;

public interface IGarageService {
	public List<Garage> getGarages();

	public Garage getGarageById(long id);

	public void addGarage(Garage garage);

	public boolean removeGarage(Garage garage);

	public boolean updateGarage(Garage garage, boolean isFree);

	public void safeToFile() throws Exception;

	public void readFromFile() throws Exception;

	public List<Garage> getFreeGarages();

	public int getFreeGarageNumber();
}
