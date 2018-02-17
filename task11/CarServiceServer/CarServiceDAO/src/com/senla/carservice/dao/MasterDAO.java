package com.senla.carservice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.carservice.api.dao.IMasterDAO;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.beans.Order;

public class MasterDAO extends GenericDAO<Master> implements IMasterDAO {
	private static Logger log = Logger.getLogger(MasterDAO.class.getName());

	private final String SELECT_QUERY = "SELECT * FROM carservice_db.masters";
	private final String UPDATE_QUERY = "UPDATE carservice_db.masters SET name=?,is_free=? WHERE id=?";
	private final String DELETE_QUERY = "DELETE FROM carservice_db.masters";
	private final String CREATE_QUERY = "INSERT INTO carservice_db.masters VALUES(?,?)";

	private final String FREE_MASTERS_NUM_QUERY = "SELECT COUNT(*) AS number FROM carservice_db.masters GROUP BY is_free HAVING is_free=true";
	private final String MASTER_BY_ORDER_QUERY = "SELECT * FROM carservice_db.masters LEFT JOIN carservice_db.orders ON masters.id=orders.master_id WHERE orders.id=?";

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
	protected List<Master> parseResultSet(ResultSet resultSet) throws SQLException {
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
	protected void prepareStatementForUpdate(PreparedStatement statement, Master object) throws SQLException {
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
	public int getFreeMasterNum(Connection connection) throws Exception {
		int freeGarageNum = 0;
		try (PreparedStatement statement = connection.prepareStatement(FREE_MASTERS_NUM_QUERY);) {
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
	public List<Master> getFreeMasters(Connection connection) throws Exception {
		List<Master> list = new ArrayList<>();
		String freeMastersSql = SELECT_QUERY + " WHERE is_free=true";
		try (PreparedStatement statement = connection.prepareStatement(freeMastersSql)) {
			ResultSet resultSet = statement.executeQuery();
			list = parseResultSet(resultSet);
		} catch (SQLException e) {
			log.error("SQLException", e);
			throw new Exception(e);
		}
		return list;
	}

	@Override
	public Master getMasterByOrder(Connection connection, Order order) throws Exception {
		if (order == null) {
			return null;
		}
		List<Master> list = new ArrayList<>();
		try (PreparedStatement statement = connection.prepareStatement(MASTER_BY_ORDER_QUERY)) {
			statement.setLong(1, order.getId());
			ResultSet resultSet = statement.executeQuery();
			parseResultSet(resultSet);
		} catch (SQLException e) {
			log.error("SQLException", e);
			throw new Exception(e);
		}
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.iterator().next();
	}

}
