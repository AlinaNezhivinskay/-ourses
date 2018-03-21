package com.senla.carservice.dependencyinjection.coordinationrepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.senla.carservice.dependencyinjection.coordinationreader.CoordinationReader;

public class CoordinationRepository {
	private static Logger log = Logger.getLogger(CoordinationRepository.class.getName());
	private static CoordinationRepository instance;
	private static final String coordinationFile = "coordination.properties";
	private Map<String, String> coordination;

	public static CoordinationRepository getInstance() {
		if (instance == null) {
			instance = new CoordinationRepository();
		}
		return instance;
	}

	private CoordinationRepository() {
		coordination = new HashMap<String, String>();
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
		Properties property = CoordinationReader.loadProperties(coordinationFile);
		coordination = (Map<String, String>) property.clone();

	}

	public Object getObject(Class<?> key) {
		String value = coordination.get(key.getName());
		try {
			Class<?> oblectClass = Class.forName(value);
			Constructor<?> constructor = oblectClass.getDeclaredConstructor();
			constructor.setAccessible(true);
			return constructor.newInstance();
		} catch (InstantiationException e) {
			log.error("InstantiationException", e);
		} catch (IllegalAccessException e) {
			log.error("IllegalAccessException", e);
		} catch (ClassNotFoundException e) {
			log.error("ClassNotFoundException", e);
		} catch (NoSuchMethodException e) {
			log.error("NoSuchMethodException", e);
		} catch (SecurityException e) {
			log.error("SecurityException", e);
		} catch (InvocationTargetException e) {
			log.error("InvocationTargetException", e);
		}
		return null;
	}
}
