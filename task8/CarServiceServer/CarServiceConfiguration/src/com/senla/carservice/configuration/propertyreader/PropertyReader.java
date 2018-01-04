package com.senla.carservice.configuration.propertyreader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
	public static Properties loadProperties(String fileName) throws FileNotFoundException, IOException {
		Properties property = new Properties();
		try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
			property.load(fileInputStream);
		}
		return property;
	}
}