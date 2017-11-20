package com.senla.carservice.api.repositories;

import java.util.List;

import com.senla.carservice.model.beans.Master;

public interface IMasterRepository {
	List<Master> getMasters();

	Master getMaster(long id);

	boolean addMaster(Master master);

	boolean removeMaster(Master master);

	boolean updateMaster(Master master, boolean isFree);

	void restoreData(List<Master> masters);
}
