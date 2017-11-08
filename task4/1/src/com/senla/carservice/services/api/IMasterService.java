package com.senla.carservice.services.api;

import java.util.Comparator;
import java.util.Date;

import com.senla.carservice.beans.Master;

public interface IMasterService {
	public void addMaster(Master master);

	public boolean removeMaster(Master master);

	public boolean updateMaster(Master master,boolean isFree);

	public void safeToFile();

	public void readFromFile();

	public Master[] getMasters();

	public int getFreeMastersNumber(Date date);

	public void sort(Comparator<Master> comparator);
}
