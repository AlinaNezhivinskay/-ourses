package com.senla.carservice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.senla.carservice.api.dao.genericdao.IGenericDAO;
import com.senla.carservice.model.beans.abstractentity.Entity;

public abstract class GenericDAO<T extends Entity> implements IGenericDAO<T> {

	Connection connection;

	public abstract String getSelectQuery();

	public abstract String getUpdateQuery();

	public abstract String getDeleteQuery();

	public abstract String getCreateQuery();

	@Override
	public abstract List<T> parseResultSet(ResultSet resultSet) throws SQLException;

	public abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws SQLException;

	public abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws SQLException;

	@Override
	public boolean create(T object) throws SQLException {
		String sql = getCreateQuery();
		int count;
		try (PreparedStatement statement = connection.prepareStatement(sql);) {
			prepareStatementForInsert(statement, object);
			count = statement.executeUpdate();
		}
		return count > 0 ? true : false;
	}

	@Override
	public T read(long key) throws SQLException {
		List<T> list = new ArrayList<>();
		String sql = getSelectQuery();
		sql += " WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setLong(1, key);
			ResultSet resultSet = statement.executeQuery();
			list = parseResultSet(resultSet);
		}
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.iterator().next();
	}

	@Override
	public boolean update(T object) throws SQLException {
		String sql = getUpdateQuery();
		int count;
		try (PreparedStatement statement = connection.prepareStatement(sql);) {
			prepareStatementForUpdate(statement, object);
			count = statement.executeUpdate();
		}
		return count > 0 ? true : false;
	}

	@Override
	public boolean delete(T object) throws SQLException {
		String sql = getDeleteQuery();
		sql += " WHERE id = ?";
		int count;
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setLong(1, object.getId());
			count = statement.executeUpdate();
		}
		return count > 0 ? true : false;
	}

	@Override
	public List<T> getAll() throws SQLException {
		List<T> list = new ArrayList<>();
		String sql = getSelectQuery();
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			ResultSet resultSet = statement.executeQuery();
			list = parseResultSet(resultSet);
		}
		return list;
	}
}
