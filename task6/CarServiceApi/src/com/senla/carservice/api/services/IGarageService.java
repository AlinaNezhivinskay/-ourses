package com.senla.carservice.api.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.senla.carservice.model.beans.Garage;

public interface IGarageService {
	List<Garage> getGarages();

	Garage getGarageById(long id);

	void addGarage(Garage garage);

	boolean removeGarage(Garage garage);

	boolean updateGarage(Garage garage, boolean isFree);

	List<Garage> getFreeGarages();

	int getFreeGarageNumber();

	boolean exportGarages(String fileName, List<Garage> garages) throws IOException;

	boolean importGarages(String fileName) throws FileNotFoundException, IOException;

	void restoreData(List<Garage> garages);
}
