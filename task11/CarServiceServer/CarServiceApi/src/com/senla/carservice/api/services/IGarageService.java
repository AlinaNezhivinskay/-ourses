package com.senla.carservice.api.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import com.senla.carservice.model.beans.Garage;

public interface IGarageService {
	List<Garage> getGarages() throws SQLException;

	Garage getGarageById(Long id) throws SQLException;

	void addGarage(Garage garage) throws SQLException;

	boolean removeGarage(Garage garage) throws SQLException;

	boolean updateGarage(Garage garage, boolean isFree) throws SQLException;

	List<Garage> getFreeGarages() throws SQLException;

	int getFreeGarageNumber() throws SQLException;

	boolean exportGarages(List<Garage> garages) throws IllegalAccessException, IOException, NoSuchFieldException;

	boolean importGarages() throws FileNotFoundException, IOException, IllegalAccessException, NoSuchFieldException,
			InstantiationException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException,
			SQLException;
}
