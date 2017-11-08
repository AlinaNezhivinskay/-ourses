package com.senla.carservice.model.repositories;

import java.util.ArrayList;

import com.danco.training.TextFileWorker;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.utils.Converter;

public class MasterRepository {
	private TextFileWorker masterFileWorker;
	private static long lastId = 0;
	private ArrayList<Master> masters;
	private static MasterRepository instance;

	public static MasterRepository getInstance(String fileName) {
		if (instance == null) {
			instance = new MasterRepository(fileName);
		}
		return instance;
	}

	public MasterRepository(String fileName) {
		if (fileName != null) {
			masterFileWorker = new TextFileWorker(fileName);
		} else {
			masterFileWorker = new TextFileWorker("E:/masters.txt");
		}
		masters = new ArrayList<Master>();
	}

	public ArrayList<Master> getMasters() {
		return masters;
	}

	public Master getMaster(long id) {
		for (Master master : masters) {
			if (master.getId() == id)
				return master;
		}
		return null;
	}

	public void addMaster(Master master) {
		master.setId(lastId);
		incrementLastId();
		masters.add(master);
	}

	public boolean removeMaster(Master master) {
		return masters.remove(master);
	}

	public boolean updateMaster(Master master, boolean isFree) {
		if (!masters.contains(master))
			return false;
		masters.get(masters.indexOf(master)).setIsFree(isFree);
		return true;
	}

	public void safeToFile() {
		masterFileWorker.writeToFile(Converter.convertMastersToStrings(masters));
	}

	public void readFromFile() {
		masters = Converter.convertStringsToMasters(masterFileWorker.readFromFile());
		lastId = masters.get(masters.size() - 1).getId();
		incrementLastId();
	}

	private void incrementLastId() {
		lastId += 10;
	}

}
