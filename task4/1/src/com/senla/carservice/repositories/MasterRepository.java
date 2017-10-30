package com.senla.carservice.repositories;

import com.danco.training.TextFileWorker;
import com.senla.carservice.beans.Master;
import com.senla.carservice.utils.ArrayWorker;
import com.senla.carservice.utils.Converter;

public class MasterRepository {
	private static final TextFileWorker MASTER_FILE_WORKER = new TextFileWorker(
			"E:/учёба Алины/Courses/task4/1/masters.txt");
	private static long lastId = 0;
	private Master[] masters;

	public MasterRepository() {
		masters = new Master[ArrayWorker.ARRAY_LENGTH];
	}

	public Master[] getMasters() {
		return masters;
	}

	public Master getMaster(long id) {
		for (int i = 0; i < masters.length; i++) {
			if (masters[i].getId() == id)
				return masters[i];
		}
		return null;
	}

	public void addMaster(Master master) {
		if (!ArrayWorker.isEmptySpace(masters)) {
			masters = ArrayWorker.expandArray(masters);
		}
		master.setId(lastId);
		incrementLastId();
		masters[ArrayWorker.getFreePosition(masters)] = master;
	}

	public boolean removeMaster(Master master) {
		if (!ArrayWorker.isElementOnArray(masters, master))
			return false;
		masters[ArrayWorker.getPositionOfElement(masters, master)] = null;
		return true;
	}

	public boolean updateMaster(Master master) {
		if (!ArrayWorker.isElementOnArray(masters, master))
			return false;
		masters[ArrayWorker.getPositionOfElement(masters, master)] = master;
		return true;
	}

	public void safeToFile() {
		MASTER_FILE_WORKER.writeToFile(Converter.convertMastersToStrings(masters));
	}

	public void readFromFile() {
		masters = Converter.convertStringsToMasters(MASTER_FILE_WORKER.readFromFile());
	}

	private void incrementLastId() {
		lastId += 10;
	}

}
