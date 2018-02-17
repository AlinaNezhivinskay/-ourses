package com.senla.carservice.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.senla.carservice.configuration.properties.JdbcPropetryRepository;

public class DBConnector {
	private static Logger log = Logger.getLogger(DBConnector.class.getName());
	private Connection connection;
	private static DBConnector instance;

	public static DBConnector getInstance() {
		if (instance == null) {
			instance = new DBConnector();
		}
		return instance;
	}

	private DBConnector() {
		connect();
	}

	private void connect() {
		JdbcPropetryRepository properties = JdbcPropetryRepository.getInstance();
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"),
					properties.getProperty("userpassword"));
		} catch (SQLException e) {
			log.error("SQLException", e);
		}
	}

	public Connection getConnection() {
		if (connection == null) {
			connect();
		}
		return connection;
	}

	public void closeConnection() throws Exception {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
				connection = null;
			}
		} catch (SQLException e) {
			log.error("Exception", e);
			throw new Exception(e);
		}

	}

}
