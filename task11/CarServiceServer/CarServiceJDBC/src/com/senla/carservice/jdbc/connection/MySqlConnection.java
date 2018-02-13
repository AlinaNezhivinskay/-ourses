package com.senla.carservice.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.senla.carservice.configuration.properties.JdbcPropetryRepository;

public class MySqlConnection {
	private static Logger log = Logger.getLogger(MySqlConnection.class.getName());
	private Connection connection;
	private static MySqlConnection instance;

	public static MySqlConnection getInstance() {
		if (instance == null) {
			instance = new MySqlConnection();
		}
		return instance;
	}

	private MySqlConnection() {
		JdbcPropetryRepository properties = JdbcPropetryRepository.getInstance();
		try {

			connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"),
					properties.getProperty("userpassword"));
		} catch (SQLException e) {
			log.error("SQLException", e);
		}

	}

	public Connection getConnection() {
		return connection;
	}

	public void closeConnection() {
		try {
			if (!connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			log.error("SQLException", e);
		}

	}

}
