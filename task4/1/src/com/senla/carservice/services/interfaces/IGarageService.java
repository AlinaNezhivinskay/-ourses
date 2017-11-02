package com.senla.carservice.services.interfaces;

import com.senla.carservice.beans.Garage;

public interface IGarageService {
	public Garage[] getGarages();

	public void addGarage(Garage garage);

	public boolean removeGarage(Garage garage);

	public boolean updateGarage(Garage garage,boolean isFree);

	public void safeToFile();

	public void readFromFile();

	public Garage[] getFreeGarages();

	public int getFreeGarageNumber();
}
