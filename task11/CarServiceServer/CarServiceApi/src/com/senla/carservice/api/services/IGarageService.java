package com.senla.carservice.api.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.senla.carservice.model.beans.Garage;

public interface IGarageService {
	List<Garage> getGarages() throws Exception;

	Garage getGarageById(Long id) throws Exception;

	void addGarage(Garage garage) throws Exception;

	boolean removeGarage(Garage garage) throws Exception;

	boolean updateGarage(Garage garage, boolean isFree) throws Exception;

	List<Garage> getFreeGarages() throws Exception;

	int getFreeGarageNumber() throws Exception;

	boolean exportGarages(List<Garage> garages) throws IllegalAccessException, IOException, NoSuchFieldException;

	boolean importGarages() throws FileNotFoundException, IOException, IllegalAccessException, NoSuchFieldException,
			InstantiationException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException, Exception;
}
