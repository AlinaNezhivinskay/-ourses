package com.senla.carservice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.carservice.api.dao.IGarageDAO;
import com.senla.carservice.model.beans.Garage;

public class GarageDAO extends GenericDAO<Garage> implements IGarageDAO {
	private static Logger log = Logger.getLogger(GarageDAO.class.getName());

	private final String SELECT_QUERY = "SELECT * FROM carservice_db.garages";
	private final String UPDATE_QUERY = "UPDATE carservice_db.garages SET is_free=? WHERE id=?";
	private final String DELETE_QUERY = "DELETE FROM carservice_db.garages";
	private final String CREATE_QUERY = "INSERT INTO carservice_db.garages VALUES(?)";

	private final String FREE_GARAGES_NUM_QUERY = "SELECT COUNT(*) AS number FROM carservice_db.garage GROUP BY is_free HAVING is_free=true";

	@Override
	protected String getSelectQuery() {
		return SELECT_QUERY;
	}

	@Override
	protected String getSelectByIdQuery() {
		return SELECT_QUERY + " WHERE id=?";
	}

	@Override
	protected String getUpdateQuery() {
		return UPDATE_QUERY;
	}

	@Override
	protected String getDeleteQuery() {
		return DELETE_QUERY;
	}

	@Override
	protected String getCreateQuery() {
		return CREATE_QUERY;
	}

	@Override
	protected List<Garage> parseResultSet(ResultSet resultSet) throws SQLException {
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
	protected void prepareStatementForUpdate(PreparedStatement statement, Garage object) throws SQLException {

		statement.setBoolean(1, object.getIsFree());
		statement.setLong(2, object.getId());

	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement, Garage object) throws SQLException {
		statement.setBoolean(1, object.getIsFree());

	}

	@Override
	public int getFreeGarageNum(Connection connection) throws Exception {
		int freeGarageNum = 0;
		try (PreparedStatement statement = connection.prepareStatement(FREE_GARAGES_NUM_QUERY);) {
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			freeGarageNum = resultSet.getInt("number");
		} catch (SQLException e) {
			log.error("SQLException", e);
			throw new Exception(e);
		}
		return freeGarageNum;
	}

	@Override
	public List<Garage> getFreeGarages(Connection connection) throws Exception {
		List<Garage> list = new ArrayList<>();
		String freeGaragesSql = SELECT_QUERY + " WHERE is_free=true";
		try (PreparedStatement statement = connection.prepareStatement(freeGaragesSql)) {
			ResultSet resultSet = statement.executeQuery();
			list = parseResultSet(resultSet);
		} catch (SQLException e) {
			log.error("SQLException", e);
			throw new Exception(e);
		}
		return list;
	}
}
