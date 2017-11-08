package com.senla.carservice.services;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import com.senla.carservice.beans.Master;
import com.senla.carservice.repositories.MasterRepository;
import com.senla.carservice.services.api.IMasterService;

public class MasterService implements IMasterService {
	private MasterRepository masterRepository;

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
	public boolean updateMaster(Master master,boolean isFree) {
		return masterRepository.updateMaster(master,isFree);
	}

	@Override
	public void safeToFile() {
		masterRepository.safeToFile();

	}

	@Override
	public void readFromFile() {
		masterRepository.readFromFile();

	}

	@Override
	public Master[] getMasters() {
		return masterRepository.getMasters();
	}

	@Override
	public int getFreeMastersNumber(Date date) {
		int freeMastersNum = 0;
		for (int i = 0; i < masterRepository.getMasters().length; i++) {
			if (masterRepository.getMasters()[i] == null) {
				continue;
			}
			if (masterRepository.getMasters()[i].getIsFree()) {
				freeMastersNum++;
			}
		}
		return freeMastersNum;
	}

	@Override
	public void sort(Comparator<Master> comparator) {
		Arrays.sort(masterRepository.getMasters(), comparator);

	}

}
