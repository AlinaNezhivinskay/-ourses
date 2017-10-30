package com.senla.carservice.repositories;

import com.danco.training.TextFileWorker;
import com.senla.carservice.beans.HistoryOrder;
import com.senla.carservice.utils.ArrayWorker;
import com.senla.carservice.utils.Converter;

public class HistoryOrderRepository {
	private static final TextFileWorker ORDER_HISTORY_FILE_WORKER = new TextFileWorker(
			"E:/учёба Алины/Courses/task4/1/hystoryOrders.txt");
	private static long lastId = 0;
	private static HistoryOrderRepository historyOrderRepository;

	public static HistoryOrderRepository getInstance() {
		return historyOrderRepository == null ? historyOrderRepository = new HistoryOrderRepository()
				: historyOrderRepository;
	}

	private HistoryOrder[] historyOrders;

	private HistoryOrderRepository() {
		historyOrders = new HistoryOrder[ArrayWorker.ARRAY_LENGTH];
	}

	public HistoryOrder[] getHistoryOrders() {
		return historyOrders;
	}

	public HistoryOrder getHistoryOrder(long id) {
		for (int i = 0; i < historyOrders.length; i++) {
			if (historyOrders[i] == null)
				continue;
			if (historyOrders[i].getId() == id)
				return historyOrders[i];
		}
		return null;
	}

	public void addHistoryOrders(HistoryOrder historyOrder) {
		if (!ArrayWorker.isEmptySpace(historyOrders)) {
			historyOrders = ArrayWorker.expandArray(historyOrders);
		}
		historyOrder.setId(lastId);
		incrementLastId();
		historyOrders[ArrayWorker.getFreePosition(historyOrders)] = historyOrder;

	}

	public boolean removeHistoryOrder(HistoryOrder historyOrder) {
		if (!ArrayWorker.isElementOnArray(historyOrders, historyOrder))
			return false;
		historyOrders[ArrayWorker.getPositionOfElement(historyOrders, historyOrder)] = null;
		return true;
	}

	public boolean updateHistoryOrder(HistoryOrder historyOrder) {
		if (!ArrayWorker.isElementOnArray(historyOrders, historyOrder))
			return false;
		historyOrders[ArrayWorker.getPositionOfElement(historyOrders, historyOrder)] = historyOrder;
		return true;
	}

	public void safeToFile() {
		ORDER_HISTORY_FILE_WORKER.writeToFile(Converter.convertHisoryOrdersToStrings(historyOrders));
	}

	public void readFromFile() {
		historyOrders = Converter.convertStringsToHistoryOrders(ORDER_HISTORY_FILE_WORKER.readFromFile());
	}

	private void incrementLastId() {
		lastId += 10;
	}
}
