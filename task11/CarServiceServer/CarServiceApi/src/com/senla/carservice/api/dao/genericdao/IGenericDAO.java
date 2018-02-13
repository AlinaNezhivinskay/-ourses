package com.senla.carservice.api.dao.genericdao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IGenericDAO<T> {
	boolean create(T object) throws SQLException;

	T read(long key) throws SQLException;

	boolean update(T object) throws SQLException;

	boolean delete(T object) throws SQLException;

	List<T> getAll() throws SQLException;

	List<T> parseResultSet(ResultSet resultSet) throws SQLException;
}
