package com.senla.carservice.api.dao.genericdao;

import java.sql.Connection;
import java.util.List;

public interface IGenericDAO<T> {
	boolean create(Connection connection, T object) throws Exception;

	T read(Connection connection, long key) throws Exception;

	boolean update(Connection connection, T object) throws Exception;

	boolean delete(Connection connection, T object) throws Exception;

	List<T> getAll(Connection connection, String sort) throws Exception;
}
