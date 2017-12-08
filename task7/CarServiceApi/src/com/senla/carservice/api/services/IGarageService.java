package com.senla.carservice.api.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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

	boolean exportGarages(List<Garage> garages) throws IllegalAccessException, IOException, NoSuchFieldException;

	boolean importGarages() throws FileNotFoundException, IOException, IllegalAccessException, NoSuchFieldException,
			InstantiationException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException;

	void restoreData(List<Garage> garages);
}
