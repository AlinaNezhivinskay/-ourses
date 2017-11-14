package com.senla.carservice.api.services;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.senla.carservice.model.beans.Master;

public interface IMasterService {
	public void addMaster(Master master);

	public Master getMasterById(long id);

	public boolean removeMaster(Master master);

	public boolean updateMaster(Master master, boolean isFree);

	public void safeToFile() throws Exception;

	public void readFromFile() throws Exception;

	public List<Master> getMasters();

	public int getFreeMastersNumber(Date date);

	public void sort(Comparator<Master> comparator);

	public List<Master> getFreeMasters();
}
