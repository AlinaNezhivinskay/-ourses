package com.senla.carservice.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.senla.carservice.api.dao.IGarageDAO;
import com.senla.carservice.jdbc.connection.MySqlConnection;
import com.senla.carservice.model.beans.Garage;

public class GarageDAO extends GenericDAO<Garage> implements IGarageDAO {
	private static IGarageDAO instance;

	public static IGarageDAO getInstance() {
		if (instance == null) {
			instance = new GarageDAO();
		}
		return instance;
	}

	public GarageDAO() {
		super.connection = MySqlConnection.getInstance().getConnection();
	}

	@Override
	public String getSelectQuery() {
		String sql = "SELECT * FROM carservice_db.garage";
		return sql;
	}

	@Override
	public String getUpdateQuery() {
		String sql = "UPDATE carservice_db.garage SET is_free=? WHERE id=?;";
		return sql;
	}

	@Override
	public String getDeleteQuery() {
		String sql = "DELETE FROM carservice_db.garage";
		return sql;
	}

	@Override
	public String getCreateQuery() {
		String sql = "INSERT INTO carservice_db.garage VALUES(?);";
		return sql;
	}

	@Override
	public List<Garage> parseResultSet(ResultSet resultSet) throws SQLException {
		List<Garage> garages = new ArrayList<Garage>();
		while (resultSet.next()) {
			Garage garage = new Garage();
			garage.setId(resultSet.getLong("id"));
			garage.setIsFree(resultSet.getBoolean("is_free"));
			garages.add(garage);
		}
		return garages;
	}

	@Override
	public void prepareStatementForUpdate(PreparedStatement statement, Garage object) throws SQLException {
		statement.setBoolean(1, object.getIsFree());
		statement.setLong(2, object.getId());
	}

	@Override
	public void prepareStatementForInsert(PreparedStatement statement, Garage object) throws SQLException {
		statement.setBoolean(1, object.getIsFree());
	}

	@Override
	public int getFreeGarageNum() throws SQLException {
		int freeGarageNum = 0;
		String sql = "SELECT COUNT(*) AS number FROM carservice_db.garage GROUP BY is_free HAVING is_free=true;";
		try (PreparedStatement statement = connection.prepareStatement(sql);) {
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			freeGarageNum = resultSet.getInt("number");
		}
		return freeGarageNum;
	}

	@Override
	public List<Garage> getFreeGarages() throws SQLException {
		List<Garage> list = new ArrayList<>();
		String sql = "SELECT * FROM carservice_db.garage WHERE is_free=true;";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			ResultSet resultSet = statement.executeQuery();
			list = parseResultSet(resultSet);
		}
		return list;
	}

	@Override
	public List<Long> getExistingId(String idListStr) throws SQLException {
		List<Long> list = new ArrayList<>();
		String sql = "SELECT id FROM carservice_db.garage WHERE id IN (?);";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, idListStr);
			ResultSet resultSet = statement.executeQuery();
			list = parseIdResultSet(resultSet);
		}
		return list;
	}

	private List<Long> parseIdResultSet(ResultSet resultSet) throws SQLException {
		List<Long> existingId = new ArrayList<Long>();
		while (resultSet.next()) {
			existingId.add(resultSet.getLong("id"));
		}
		return existingId;
	}
}
