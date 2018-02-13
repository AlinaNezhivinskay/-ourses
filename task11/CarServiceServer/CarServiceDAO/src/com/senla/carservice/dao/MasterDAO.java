package com.senla.carservice.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.senla.carservice.api.dao.IMasterDAO;
import com.senla.carservice.jdbc.connection.MySqlConnection;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.sortfields.master.SortMasterFields;

public class MasterDAO extends GenericDAO<Master> implements IMasterDAO {
	private static IMasterDAO instance;

	public static IMasterDAO getInstance() {
		if (instance == null) {
			instance = new MasterDAO();
		}
		return instance;
	}

	public MasterDAO() {
		super.connection = MySqlConnection.getInstance().getConnection();
	}

	@Override
	public String getSelectQuery() {
		String sql = "SELECT * FROM carservice_db.master";
		return sql;
	}

	@Override
	public String getUpdateQuery() {
		String sql = "UPDATE carservice_db.master SET name=?,is_free=? WHERE id=?;";
		return sql;
	}

	@Override
	public String getDeleteQuery() {
		String sql = "DELETE FROM carservice_db.master";
		return sql;
	}

	@Override
	public String getCreateQuery() {
		String sql = "INSERT INTO carservice_db.master VALUES(?,?);";
		return sql;
	}

	@Override
	public List<Master> parseResultSet(ResultSet resultSet) throws SQLException {
		List<Master> masters = new ArrayList<Master>();
		while (resultSet.next()) {
			Master master = new Master();
			master.setId(resultSet.getLong("id"));
			master.setName(resultSet.getString("name"));
			master.setIsFree(resultSet.getBoolean("is_free"));
			masters.add(master);
		}
		return masters;
	}

	@Override
	public void prepareStatementForUpdate(PreparedStatement statement, Master object) throws SQLException {
		statement.setString(1, object.getName());
		statement.setBoolean(1, object.getIsFree());
		statement.setLong(2, object.getId());

	}

	@Override
	public void prepareStatementForInsert(PreparedStatement statement, Master object) throws SQLException {
		statement.setString(1, object.getName());
		statement.setBoolean(2, object.getIsFree());

	}

	@Override
	public int getFreeMasterNum() throws SQLException {
		int freeGarageNum = 0;
		String sql = "SELECT COUNT(*) AS number FROM carservice_db.master GROUP BY is_free HAVING is_free=true;";
		try (PreparedStatement statement = connection.prepareStatement(sql);) {
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			freeGarageNum = resultSet.getInt("number");
		}
		return freeGarageNum;
	}

	@Override
	public List<Master> getFreeMasters() throws SQLException {
		List<Master> list = new ArrayList<>();
		String sql = "SELECT * FROM carservice_db.master WHERE is_free=true;";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			ResultSet resultSet = statement.executeQuery();
			list = parseResultSet(resultSet);
		}
		return list;
	}

	@Override
	public List<Long> getExistingId(String idListStr) throws SQLException {
		List<Long> list = new ArrayList<>();
		String sql = "SELECT id FROM carservice_db.master WHERE id IN (?);";
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

	@Override
	public List<Master> getAll(SortMasterFields field, boolean desc) throws SQLException {
		List<Master> list = new ArrayList<>();
		String sql = getSelectQuery();
		sql += " ORDER BY = ?";
		if (desc == true) {
			sql += " desc";
		}
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, field.name());
			ResultSet resultSet = statement.executeQuery();
			list = parseResultSet(resultSet);
		}
		return list;
	}

}
