package com.senla.carservice.api.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.senla.carservice.model.beans.Master;

public interface IMasterService {
	void addMaster(Master master);

	Master getMasterById(Long id);

	boolean removeMaster(Master master);

	boolean updateMaster(Master master, boolean isFree);

	List<Master> getMasters();

	int getFreeMastersNumber(Date date);

	void sort(Comparator<Master> comparator);

	List<Master> getFreeMasters();

	boolean exportMasters(List<Master> masters) throws IllegalAccessException, IOException, NoSuchFieldException;

	boolean importMasters() throws FileNotFoundException, IOException, IllegalAccessException, NoSuchFieldException,
			InstantiationException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException;

	void restoreData(List<Master> masters);
}
