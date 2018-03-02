package com.senla.carservice.configuration.propertyreader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
	public static Properties loadProperties(String fileName) throws FileNotFoundException, IOException {
		Properties property = new Properties();
		try (InputStream fileInputStream = PropertyReader.class.getClassLoader().getResourceAsStream(fileName)) {
			property.load(fileInputStream);
		}
		return property;
	}
}