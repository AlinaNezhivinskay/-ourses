package com.senla.carservice.configuration.properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.senla.carservice.configuration.propertyreader.PropertyReader;

public class JdbcPropetryRepository {
	private static Logger log = Logger.getLogger(PropertyRepository.class.getName());
	private static JdbcPropetryRepository instance;
	private static final String propertyFile = "jdbc.properties";
	private Map<String, String> properties;

	public static JdbcPropetryRepository getInstance() {
		if (instance == null) {
			instance = new JdbcPropetryRepository();
		}
		return instance;
	}

	private JdbcPropetryRepository() {
		properties = new HashMap<String, String>();
		try {
			loadProperties();
		} catch (FileNotFoundException e) {
			log.error("FileNotFoundException", e);
		} catch (IOException e) {
			log.error("IOException", e);
		}
	}

	@SuppressWarnings("unchecked")
	private void loadProperties() throws FileNotFoundException, IOException {
		Properties property = PropertyReader.loadProperties(propertyFile);
		properties = (Map<String, String>) property.clone();

	}

	public String getProperty(String key) {
		String value = properties.get(key);
		return value;
	}
}
