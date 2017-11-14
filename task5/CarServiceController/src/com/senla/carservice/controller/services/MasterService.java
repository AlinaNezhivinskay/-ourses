package com.senla.carservice.controller.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.senla.carservice.api.repositories.IMasterRepository;
import com.senla.carservice.api.services.IMasterService;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.comparators.entity.IdComparator;
import com.senla.carservice.repository.repositories.MasterRepository;

public class MasterService implements IMasterService {
	private IMasterRepository masterRepository;

	public MasterService(String fileName) {
		this.masterRepository = new MasterRepository(fileName);
	}

	@Override
	public void addMaster(Master master) {
		masterRepository.addMaster(master);
	}

	@Override
	public boolean removeMaster(Master master) {
		return masterRepository.removeMaster(master);
	}

	@Override
	public boolean updateMaster(Master master, boolean isFree) {
		return masterRepository.updateMaster(master, isFree);
	}

	@Override
	public void safeToFile() throws Exception {
		masterRepository.getMasters().sort(new IdComparator());
		masterRepository.safeToFile();

	}

	@Override
	public void readFromFile() throws Exception {
		masterRepository.readFromFile();

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
	public Master getMasterById(long id) {
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

}
