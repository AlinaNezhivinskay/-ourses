package com.senla.carservice.dependencyinjection.coordinationreader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CoordinationReader {
	public static Properties loadProperties(String fileName) throws FileNotFoundException, IOException {
		Properties property = new Properties();
		try (InputStream fileInputStream = CoordinationReader.class.getClassLoader().getResourceAsStream(fileName)) {
			property.load(fileInputStream);
		}
		return property;
	}
}
