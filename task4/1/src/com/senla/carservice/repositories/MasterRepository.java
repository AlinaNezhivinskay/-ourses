package com.senla.carservice.repositories;

import com.danco.training.TextFileWorker;
import com.senla.carservice.beans.Master;
import com.senla.carservice.utils.ArrayWorker;
import com.senla.carservice.utils.Converter;

public class MasterRepository {
	private TextFileWorker masterFileWorker;
	private static long lastId = 0;
	private static Master[] masters;

	public MasterRepository(String fileName) {
		if (fileName != null) {
			masterFileWorker = new TextFileWorker(fileName);
		} else {
			masterFileWorker = new TextFileWorker("E:/учёба Алины/Courses/task4/1/masters.txt");
		}
		masters = new Master[ArrayWorker.ARRAY_LENGTH];
	}

	public Master[] getMasters() {
		return masters;
	}

	public static Master getMaster(long id) {
		for (int i = 0; i < masters.length; i++) {
			if (masters[i].getId() == id)
				return masters[i];
		}
		return null;
	}

	public void addMaster(Master master) {
		if (!ArrayWorker.isEmptySpace(masters)) {
			masters = (Master[]) ArrayWorker.expandArray(masters);
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

	public boolean updateMaster(Master master, boolean isFree) {
		if (!ArrayWorker.isElementOnArray(masters, master))
			return false;
		masters[ArrayWorker.getPositionOfElement(masters, master)].setIsFree(isFree);
		return true;
	}

	public void safeToFile() {
		masterFileWorker.writeToFile(Converter.convertMastersToStrings(masters));
	}

	public void readFromFile() {
		masters = Converter.convertStringsToMasters(masterFileWorker.readFromFile());
		lastId = masters[masters.length - 1].getId();
		incrementLastId();
	}

	private void incrementLastId() {
		lastId += 10;
	}

}
