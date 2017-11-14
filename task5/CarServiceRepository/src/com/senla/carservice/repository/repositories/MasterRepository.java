package com.senla.carservice.repository.repositories;

import java.util.ArrayList;
import java.util.List;

import com.danco.training.TextFileWorker;
import com.senla.carservice.api.repositories.IMasterRepository;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.util.utils.Converter;

public class MasterRepository implements IMasterRepository {
	private TextFileWorker masterFileWorker;
	private static long lastId = 0;
	private List<Master> masters;

	public MasterRepository(String fileName) {
		if (fileName != null) {
			masterFileWorker = new TextFileWorker(fileName);
		} else {
			masterFileWorker = new TextFileWorker("masters.txt");
		}
		masters = new ArrayList<Master>();
	}

	@Override
	public List<Master> getMasters() {
		return masters;
	}

	@Override
	public Master getMaster(long id) {
		for (Master master : masters) {
			if (master.getId() == id)
				return master;
		}
		return null;
	}

	@Override
	public void addMaster(Master master) {
		master.setId(lastId);
		incrementLastId();
		masters.add(master);
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
	public void safeToFile() {
		masterFileWorker.writeToFile(Converter.convertMastersToStrings(masters));
	}

	@Override
	public void readFromFile() {
		masters = Converter.convertStringsToMasters(masterFileWorker.readFromFile());
		lastId = masters.get(masters.size() - 1).getId();
		incrementLastId();
	}

	private void incrementLastId() {
		lastId += 10;
	}

}
