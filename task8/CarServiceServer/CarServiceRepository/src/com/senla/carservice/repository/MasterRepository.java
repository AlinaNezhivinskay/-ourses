package com.senla.carservice.repository;

import java.util.ArrayList;
import java.util.List;

import com.senla.carservice.api.repositories.IMasterRepository;
import com.senla.carservice.model.beans.Master;

public class MasterRepository implements IMasterRepository {
	private static IMasterRepository instance;
	private static long lastId = 0;
	private List<Master> masters;

	public static IMasterRepository getInstance() {
		if (instance == null) {
			instance = new MasterRepository();
		}
		return instance;
	}

	private MasterRepository() {
		masters = new ArrayList<Master>();
	}

	@Override
	public List<Master> getMasters() {
		return masters;
	}

	@Override
	public Master getMaster(Long id) {
		for (Master master : masters) {
			if (master.getId() == id)
				return master;
		}
		return null;
	}

	@Override
	public boolean addMaster(Master master) {
		master.setId(lastId);
		incrementLastId();
		return masters.add(master);
	}

	@Override
	public boolean removeMaster(Master master) {
		return masters.remove(master);
	}

	@Override
	public boolean updateMaster(Master master, boolean isFree) {
		if (!masters.contains(master))
			return false;
		masters.get(masters.indexOf(master)).setIsFree(isFree);
		return true;
	}

	@Override
	public void restoreData(List<Master> masters) {
		this.masters = masters;
		lastId = masters.get(masters.size() - 1).getId();
		incrementLastId();
	}

	private void incrementLastId() {
		lastId += 10;
	}

}
