package com.senla.carservice.services;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import com.senla.carservice.beans.Master;
import com.senla.carservice.beans.Order;
import com.senla.carservice.repositories.HistoryOrderRepository;
import com.senla.carservice.repositories.MasterRepository;
import com.senla.carservice.services.interfaces.IMasterService;

public class MasterService implements IMasterService {
	private MasterRepository masterRepository;
	private HistoryOrderRepository historyOrderRepository;

	public MasterService(MasterRepository masterRepository) {
		this.masterRepository = masterRepository;
		this.historyOrderRepository = HistoryOrderRepository.getInstance();
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
	public boolean updateMaster(Master master) {
		return masterRepository.updateMaster(master);
	}

	@Override
	public void safeToFile() {
		masterRepository.safeToFile();
		historyOrderRepository.safeToFile();

	}

	@Override
	public void readFromFile() {
		masterRepository.readFromFile();
		historyOrderRepository.readFromFile();

	}

	@Override
	public Master[] getMasters() {
		return masterRepository.getMasters();
	}

	@Override
	public Master getMasterByOrder(Order order) {
		if (order == null)
			return null;
		for (int i = 0; i < historyOrderRepository.getHistoryOrders().length; i++) {
			if (historyOrderRepository.getHistoryOrders()[i] == null) {
				continue;
			}
			if (historyOrderRepository.getHistoryOrders()[i].getOrder().equals(order)) {
				return historyOrderRepository.getHistoryOrders()[i].getMaster();
			}
		}
		return null;
	}

	@Override
	public int getFreeMastersNumber(Date date) {
		int freeMastersNum = 0;
		for (int i = 0; i < masterRepository.getMasters().length; i++) {
			if (masterRepository.getMasters()[i] == null) {
				continue;
			}
			if (isMasterInHistoryOrders(masterRepository.getMasters()[i])) {
				if (getOrderByMaster(masterRepository.getMasters()[i]).getExecutionDate().before(date)) {
					freeMastersNum++;
				}
			} else {
				freeMastersNum++;
			}
		}
		return freeMastersNum;
	}

	private boolean isMasterInHistoryOrders(Master master) {
		for (int i = 0; i < historyOrderRepository.getHistoryOrders().length; i++) {
			if (historyOrderRepository.getHistoryOrders()[i] == null) {
				continue;
			}
			if (historyOrderRepository.getHistoryOrders()[i].getMaster().equals(master)) {
				return true;
			}
		}
		return false;
	}

	private Order getOrderByMaster(Master master) {
		if (master == null)
			return null;
		for (int i = 0; i < historyOrderRepository.getHistoryOrders().length; i++) {
			if (historyOrderRepository.getHistoryOrders()[i].getMaster().equals(master)) {
				return historyOrderRepository.getHistoryOrders()[i].getOrder();
			}
		}
		return null;
	}

	@Override
	public void sort(Comparator<Master> comparator, Master[] masters) {
		Arrays.sort(masters, comparator);

	}

}
