package com.senla.carservice.controller.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.senla.carservice.api.repositories.IMasterRepository;
import com.senla.carservice.api.services.IMasterService;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.repository.MasterRepository;
import com.senla.carservice.util.utils.Converter;
import com.senla.carservice.util.utils.FileWorker;

public class MasterService implements IMasterService {
	private IMasterRepository masterRepository;

	public MasterService() {
		this.masterRepository = MasterRepository.getInstance();
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
	public void restoreData(List<Master> masters) {
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

	@Override
	public boolean exportMasters(String filePath, List<Master> masters) throws IOException {
		return FileWorker.writeFile(filePath, Converter.convertMastersToStrings(masters));
	}

	@Override
	public boolean importMasters(String filePath) throws FileNotFoundException, IOException {
		List<Master> masters = Converter.convertStringsToMasters(FileWorker.readFile(filePath));
		for (Master master : masters) {
			masterRepository.addMaster(master);
		}
		return true;
	}

}
