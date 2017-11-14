package com.senla.carservice.api.repositories;

import java.util.List;

import com.senla.carservice.model.beans.Garage;

public interface IGarageRepository {

	public List<Garage> getGarages();

	public Garage getGarage(long id);

	public boolean addGarage(Garage garage);

	public boolean removeGarage(Garage garage);

	public boolean updateGarage(Garage garage, boolean isFree);

	public void safeToFile() throws Exception;

	public void readFromFile() throws Exception;
}
