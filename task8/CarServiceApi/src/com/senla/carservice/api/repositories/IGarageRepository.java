package com.senla.carservice.api.repositories;

import java.util.List;

import com.senla.carservice.model.beans.Garage;

public interface IGarageRepository {

	List<Garage> getGarages();

	Garage getGarage(Long id);

	boolean addGarage(Garage garage);

	boolean removeGarage(Garage garage);

	boolean updateGarage(Garage garage, boolean isFree);

	void restoreData(List<Garage> garages);
}
