package com.senla.carservice.api.repositories;

import java.util.List;

import com.senla.carservice.model.beans.Master;

public interface IMasterRepository {
	public List<Master> getMasters();

	public Master getMaster(long id);

	public void addMaster(Master master);

	public boolean removeMaster(Master master);

	public boolean updateMaster(Master master, boolean isFree);

	public void safeToFile() throws Exception;

	public void readFromFile() throws Exception;
}
