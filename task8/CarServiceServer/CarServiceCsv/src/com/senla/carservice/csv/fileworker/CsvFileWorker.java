package com.senla.carservice.csv.fileworker;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.senla.carservice.annotations.csv.CsvEntity;
import com.senla.carservice.configuration.properties.PropertyRepository;
import com.senla.carservice.csv.converter.CsvConverter;
import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.model.beans.abstractentity.Entity;
import com.senla.carservice.util.utils.FileWorker;

public class CsvFileWorker {
	public static boolean safeToFile(List<? extends Entity> entities)
			throws IllegalAccessException, IOException, NoSuchFieldException {
		if (entities == null) {
			return false;
		}
		Class<? extends Entity> entityClass = entities.get(0).getClass();
		CsvEntity annotation = entityClass.getDeclaredAnnotation(CsvEntity.class);
		String fileName = annotation.filename();
		String separator = annotation.valuesSeparator();

		List<String> strings = CsvConverter.convertToStrings(entities, separator);

		String folder = getFolder();
		String filePath = folder + fileName;

		return FileWorker.writeFile(filePath, strings);
	}

	public static List<? extends Entity> readFromFileGarage()
			throws IllegalAccessException, IOException, NoSuchFieldException, InstantiationException,
			InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
		String filePath = getFolder() + getFileName(Garage.class);
		List<String> strings = FileWorker.readFile(filePath);
		return CsvConverter.convertFromStrings(Garage.class, strings);
	}

	public static List<? extends Entity> readFromFileMaster() throws IllegalAccessException, IOException,
			NoSuchFieldException, InstantiationException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException {
		String filePath = getFolder() + getFileName(Master.class);
		List<String> strings = FileWorker.readFile(filePath);
		return CsvConverter.convertFromStrings(Master.class, strings);
	}

	public static List<? extends Entity> readFromFileOrder() throws IllegalAccessException, IOException,
			NoSuchFieldException, InstantiationException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException {
		String filePath = getFolder() + getFileName(Order.class);
		List<String> strings = FileWorker.readFile(filePath);
		return CsvConverter.convertFromStrings(Order.class, strings);
	}

	private static String getFileName(Class<? extends Entity> cl) {
		CsvEntity annotation = cl.getDeclaredAnnotation(CsvEntity.class);
		return annotation.filename();
	}

	private static String getFolder() {
		return PropertyRepository.getInstance().getProperty("folderPath");
	}
}
