package com.senla.carservice.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;

import com.senla.carservice.api.dao.IOrderDAO;
import com.senla.carservice.jdbc.connection.MySqlConnection;
import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.model.orderstate.OrderState;
import com.senla.carservice.model.sortfields.order.SortOrderFields;

public class OrderDAO extends GenericDAO<Order> implements IOrderDAO {
	private static IOrderDAO instance;

	public static IOrderDAO getInstance() {
		if (instance == null) {
			instance = new OrderDAO();
		}
		return instance;
	}

	public OrderDAO() {
		super.connection = MySqlConnection.getInstance().getConnection();
	}

	@Override
	public String getSelectQuery() {
		String sql = "SELECT * FROM carservice_db.carservice_order";
		return sql;
	}

	@Override
	public String getUpdateQuery() {
		String sql = "UPDATE carservice_db.carservice_order SET order_state=?, submission_date=?, execution_date=?, planned_start_date=?, price=?, garage_id=?, master_id=?  WHERE id=?;";
		return sql;
	}

	@Override
	public String getDeleteQuery() {
		String sql = "DELETE FROM carservice_db.carservice_order";
		return sql;
	}

	@Override
	public String getCreateQuery() {
		String sql = "INSERT INTO carservice_db.carservice_order VALUES(?,?,?,?,?,?,?);";
		return sql;
	}

	@Override
	public List<Order> parseResultSet(ResultSet resultSet) throws SQLException {
		List<Order> orders = new ArrayList<Order>();
		while (resultSet.next()) {
			Order order = new Order();
			order.setId(resultSet.getLong("id"));
			order.setSubmissionDate(resultSet.getDate("submission_date"));
			order.setExecutionDate(resultSet.getDate("execution_date"));
			order.setPlannedStartDate(resultSet.getDate("planned_start_date"));
			order.setPrice(resultSet.getDouble("price"));
			Long garage_id = resultSet.getLong("garage_id");
			Garage garage = null;
			if (garage_id != null) {
				garage = GarageDAO.getInstance().read(garage_id);
			}
			order.setGarage(garage);
			Long master_id = resultSet.getLong("master_id");
			Master master = null;
			if (master_id != null) {
				master = MasterDAO.getInstance().read(garage_id);
			}
			order.setMaster(master);
			orders.add(order);
		}
		return orders;
	}

	@Override
	public void prepareStatementForUpdate(PreparedStatement statement, Order object) throws SQLException {
		statement.setString(1, object.getState().toString());
		statement.setDate(2, new Date(object.getSubmissionDate().getTime()));
		statement.setDate(3, new Date(object.getExecutionDate().getTime()));
		statement.setDate(4, new Date(object.getPlannedStartDate().getTime()));
		statement.setDouble(5, object.getPrice());
		statement.setLong(6, object.getGarage().getId());
		statement.setLong(7, object.getMaster().getId());
		statement.setLong(8, object.getId());

	}

	@Override
	public void prepareStatementForInsert(PreparedStatement statement, Order object) throws SQLException {
		statement.setString(1, object.getState().toString());
		statement.setDate(2, new Date(object.getSubmissionDate().getTime()));
		statement.setDate(3, new Date(object.getExecutionDate().getTime()));
		statement.setDate(4, new Date(object.getPlannedStartDate().getTime()));
		statement.setDouble(5, object.getPrice());
		statement.setLong(6, object.getGarage().getId());
		statement.setLong(7, object.getMaster().getId());
	}

	@Override
	public boolean updateOrderState(Order order, OrderState state) throws SQLException {
		String sql = "UPDATE carservice_db.carservice_order SET order_state=? WHERE id=?;";
		int result;
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, state.name());
			statement.setLong(2, order.getId());
			result = statement.executeUpdate();
		}
		return result > 0 ? true : false;
	}

	@Override
	public List<Order> getAll(SortOrderFields field, boolean desc) throws SQLException {
		List<Order> list = new ArrayList<>();
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

	@Override
	public List<Order> getOrdersByState(OrderState state) throws SQLException {
		List<Order> list = new ArrayList<>();
		String sql = "SELECT * FROM carservice_db.carservice_order WHERE order_state=?;";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, state.name());
			ResultSet resultSet = statement.executeQuery();
			list = parseResultSet(resultSet);
		}
		return list;
	}

	@Override
	public List<Order> getOrdersByStateAndPeriod(OrderState state, java.util.Date startTimePeriod,
			java.util.Date endTimePeriod) throws SQLException {
		List<Order> list = new ArrayList<>();
		String sql = "SELECT * FROM carservice_db.carservice_order WHERE order_state=? AND submission_date>? AND execution_date<?;";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, state.name());
			statement.setDate(2, new Date(startTimePeriod.getTime()));
			statement.setDate(3, new Date(endTimePeriod.getTime()));
			ResultSet resultSet = statement.executeQuery();
			list = parseResultSet(resultSet);
		}
		return list;
	}

	@Override
	public Order getOrderByMaster(Master master) throws SQLException {
		List<Order> list = new ArrayList<>();
		String sql = getSelectQuery();
		sql += " WHERE master_id = ?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setLong(1, master.getId());
			ResultSet resultSet = statement.executeQuery();
			list = parseResultSet(resultSet);
		}
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.iterator().next();
	}

	@Override
	public Master getMasterByOrder(Order order) throws SQLException {
		List<Master> list = new ArrayList<>();
		String sql = "SELECT m.id,m.name,m.is_free FROM carservice_db.master m JOIN carservice_db.carservice_order o ON m.id=o.master_id WHERE o.id=?;";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setLong(1, order.getId());
			ResultSet resultSet = statement.executeQuery();
			MasterDAO.getInstance().parseResultSet(resultSet);
		}
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.iterator().next();
	}

	@Override
	public int getFreeGarageNumber(java.util.Date date) throws SQLException {
		int freeGarageNum = 0;
		String sql = "SELECT count(*) AS number FROM carservice_db.carservice_order o WHERE o.order_state='EXECUTABLE' AND o.execution_date<?";
		try (PreparedStatement statement = connection.prepareStatement(sql);) {
			statement.setDate(1, new Date(date.getTime()));
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			freeGarageNum = resultSet.getInt("number");
		}
		return freeGarageNum;
	}

	@Override
	public int getFreeMasterNumber(java.util.Date date) throws SQLException {
		int freeGarageNum = 0;
		String sql = "SELECT count(*) AS number FROM carservice_db.carservice_order o WHERE o.order_state='EXECUTABLE' AND o.execution_date<?";
		try (PreparedStatement statement = connection.prepareStatement(sql);) {
			statement.setDate(1, new Date(date.getTime()));
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			freeGarageNum = resultSet.getInt("number");
		}
		return freeGarageNum;
	}

	@Override
	public boolean assignMasterToOrder(Order order, Master master) throws SQLException {
		String sql = "UPDATE carservice_db.carservice_order SET master_id=? WHERE id=?;";
		int result;
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setLong(1, master.getId());
			statement.setLong(2, order.getId());
			result = statement.executeUpdate();
		}
		master.setIsFree(false);
		MasterDAO.getInstance().update(master);
		return result > 0 ? true : false;
	}

	@Override
	public boolean assignGarageToOrder(Order order, Garage garage) throws SQLException {
		connection.setAutoCommit(false);
		Savepoint savepoint = connection.setSavepoint("SavepointForAssign");
		String sql = "UPDATE carservice_db.carservice_order SET garage_id=? WHERE id=?;";
		int result = 0;
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setLong(1, garage.getId());
			statement.setLong(2, order.getId());
			result = statement.executeUpdate();

			garage.setIsFree(false);
			GarageDAO.getInstance().update(garage);

			connection.commit();
		} catch (SQLException e) {
			connection.rollback(savepoint);
		}

		return result > 0 ? true : false;
	}

	@Override
	public List<Long> getExistingId(String idListStr) throws SQLException {
		List<Long> list = new ArrayList<>();
		String sql = "SELECT id FROM carservice_db.carservice_order WHERE id IN (?);";
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
