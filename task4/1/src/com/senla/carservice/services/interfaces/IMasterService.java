package com.senla.carservice.services.interfaces;

import java.util.Comparator;
import java.util.Date;

import com.senla.carservice.beans.Master;
import com.senla.carservice.beans.Order;

public interface IMasterService {
	public void addMaster(Master master);

	public boolean removeMaster(Master master);

	public boolean updateMaster(Master master);

	public void safeToFile();

	public void readFromFile();

	public Master[] getMasters();

	public Master getMasterByOrder(Order order);

	public int getFreeMastersNumber(Date date);

	public void sort(Comparator<Master> comparator, Master[] masters);
}
