package com.senla.carservice.util.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.senla.carservice.configuration.properties.PropertyRepository;
import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.model.beans.abstractentity.Entity;

public class Serializer {
	public static void serialize(List<List<? extends Entity>> list) throws IOException {
		String fileName = PropertyRepository.getInstance().getProperty("dataFile");
		File file = new File(fileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
			for (List<? extends Entity> entityList : list) {
				objectOutputStream.writeObject(entityList);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static List<List<? extends Entity>> deserialize() throws ClassNotFoundException, IOException {
		String fileName = PropertyRepository.getInstance().getProperty("dataFile");
		List<List<? extends Entity>> list = new ArrayList<List<? extends Entity>>();
		try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
			list.add((ArrayList<Garage>) objectInputStream.readObject());
			list.add((ArrayList<Master>) objectInputStream.readObject());
			list.add((ArrayList<Order>) objectInputStream.readObject());
		}
		return list;
	}

}
