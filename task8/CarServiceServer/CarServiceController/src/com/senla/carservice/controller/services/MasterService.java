package com.senla.carservice.controller.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.senla.carservice.api.repositories.IMasterRepository;
import com.senla.carservice.api.services.IMasterService;
import com.senla.carservice.csv.fileworker.CsvFileWorker;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.repository.MasterRepository;

public class MasterService implements IMasterService {
	private IMasterRepository masterRepository;

	public MasterService() {
		this.masterRepository = MasterRepository.getInstance();
	}

	@Override
	public synchronized void addMaster(Master master) {
		masterRepository.addMaster(master);
	}

	@Override
	public synchronized boolean removeMaster(Master master) {
		return masterRepository.removeMaster(master);
	}

	@Override
	public synchronized boolean updateMaster(Master master, boolean isFree) {
		return masterRepository.updateMaster(master, isFree);
	}

	@Override
	public synchronized void restoreData(List<Master> masters) {
		masterRepository.restoreData(masters);
	}

	@Override
	public List<Master> getMasters() {
		return masterRepository.getMasters();
	}

	@Override
	public int getFreeMastersNumber(Date date) {
		int freeMastersNum = 0;
		for (Master master : masterRepository.getMasters()) {
			if (master.getIsFree()) {
				freeMastersNum++;
			}
		}
		return freeMastersNum;
	}

	@Override
	public void sort(Comparator<Master> comparator) {
		masterRepository.getMasters().sort(comparator);

	}

	@Override
	public Master getMasterById(Long id) {
		return masterRepository.getMaster(id);
	}

	@Override
	public List<Master> getFreeMasters() {
		List<Master> freeMasters = new ArrayList<Master>();
		for (Master master : masterRepository.getMasters()) {
			if (master.getIsFree()) {
				freeMasters.add(master);
			}
		}
		return freeMasters;
	}

	@Override
	public boolean exportMasters(List<Master> masters)
			throws IllegalAccessException, IOException, NoSuchFieldException {
		return CsvFileWorker.safeToFile(masters);
	}

	@Override
	public synchronized boolean importMasters()
			throws FileNotFoundException, IOException, IllegalAccessException, NoSuchFieldException,
			InstantiationException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
		@SuppressWarnings("unchecked")
		List<Master> masters = (List<Master>) CsvFileWorker.readFromFileMaster();
		for (Master master : masters) {
			masterRepository.addMaster(master);
		}
		return true;
	}

}
