package com.senla.carservice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.carservice.api.dao.genericdao.IGenericDAO;
import com.senla.carservice.model.beans.abstractentity.Entity;

public abstract class GenericDAO<T extends Entity> implements IGenericDAO<T> {
	private static Logger log = Logger.getLogger(GenericDAO.class.getName());

	protected abstract String getSelectQuery();

	protected abstract String getSelectByIdQuery();

	protected abstract String getUpdateQuery();

	protected abstract String getDeleteQuery();

	protected abstract String getCreateQuery();

	protected abstract List<T> parseResultSet(ResultSet resultSet) throws SQLException;

	protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws SQLException;

	protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws SQLException;

	@Override
	public boolean create(Connection connection, T object) throws Exception {
		if (object == null) {
			return false;
		}
		String sql = getCreateQuery();
		int count;
		try (PreparedStatement statement = connection.prepareStatement(sql);) {
			prepareStatementForInsert(statement, object);
			count = statement.executeUpdate();
		} catch (SQLException e) {
			log.error("SQLException", e);
			throw new Exception(e);
		}
		return count > 0 ? true : false;
	}

	@Override
	public T read(Connection connection, long key) throws Exception {
		List<T> list = new ArrayList<>();
		String sql = getSelectByIdQuery();
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setLong(1, key);
			ResultSet resultSet = statement.executeQuery();
			list = parseResultSet(resultSet);
		} catch (SQLException e) {
			log.error("SQLException", e);
			throw new Exception(e);
		}
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.iterator().next();
	}

	@Override
	public boolean update(Connection connection, T object) throws Exception {
		if (object == null) {
			return false;
		}
		String sql = getUpdateQuery();
		int count;
		try (PreparedStatement statement = connection.prepareStatement(sql);) {
			prepareStatementForUpdate(statement, object);
			count = statement.executeUpdate();
		} catch (SQLException e) {
			log.error("SQLException", e);
			throw new Exception(e);
		}
		return count > 0 ? true : false;
	}

	@Override
	public boolean delete(Connection connection, T object) throws Exception {
		if (object == null) {
			return false;
		}
		String sql = getDeleteQuery();
		sql += " WHERE id = ?";
		int count;
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setLong(1, object.getId());
			count = statement.executeUpdate();
		} catch (SQLException e) {
			log.error("SQLException", e);
			throw new Exception(e);
		}
		return count > 0 ? true : false;
	}

	@Override
	public List<T> getAll(Connection connection, String sort) throws Exception {
		List<T> list = new ArrayList<>();
		String sql = getSelectQuery();
		if (sort != null) {
			sql += " ORDER BY " + sort;
		}
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			ResultSet resultSet = statement.executeQuery();
			list = parseResultSet(resultSet);
		} catch (SQLException e) {
			log.error("SQLException", e);
			throw new Exception(e);
		}
		return list;
	}
}
