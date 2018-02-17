package com.senla.carservice.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.carservice.api.dao.IOrderDAO;
import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.model.orderstate.OrderState;

public class OrderDAO extends GenericDAO<Order> implements IOrderDAO {
	private static Logger log = Logger.getLogger(OrderDAO.class.getName());

	private final String SELECT_QUERY = "SELECT o.*,g.is_free AS garage_is_free,m.name AS master_name,m.is_free AS master_is_free FROM carservice_db.orders o JOIN carservice_db.garages g ON o.garage_id=g.id LEFT JOIN carservice_db.masters m ON o.master_id=m.id";
	private final String UPDATE_QUERY = "UPDATE carservice_db.orders SET order_state=?, submission_date=?, execution_date=?, planned_start_date=?, price=?, garage_id=?, master_id=?  WHERE id=?";
	private final String DELETE_QUERY = "DELETE FROM carservice_db.orders";
	private final String CREATE_QUERY = "INSERT INTO carservice_db.orders VALUES(?,?,?,?,?,?,?)";

	private final String SELECT_BY_MASTER_QUERY = SELECT_QUERY + " WHERE master_id = ?";
	private final String SELECT_BY_STATE_QUERY = SELECT_QUERY + " WHERE order_state=?";
	private final String SELECT_BY_STATE_AND_PERIOD_QUERY = SELECT_BY_STATE_QUERY
			+ " AND submission_date>? AND execution_date<?";
	private final String EXECUTED_ON_DATE_ORDERS_NUM_QUERY = "SELECT count(*) AS number FROM carservice_db.orders o WHERE o.order_state='EXECUTABLE' AND o.execution_date<?";

	@Override
	protected String getSelectQuery() {
		return SELECT_QUERY;
	}

	@Override
	protected String getSelectByIdQuery() {
		return SELECT_QUERY + " WHERE o.id=?";
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
	protected List<Order> parseResultSet(ResultSet resultSet) throws SQLException {
		List<Order> orders = new ArrayList<Order>();
		while (resultSet.next()) {
			Order order = new Order();
			order.setId(resultSet.getLong("id"));
			order.setSubmissionDate(resultSet.getDate("submission_date"));
			order.setExecutionDate(resultSet.getDate("execution_date"));
			order.setPlannedStartDate(resultSet.getDate("planned_start_date"));
			order.setPrice(resultSet.getDouble("price"));

			Garage garage = new Garage();
			garage.setId(resultSet.getLong("garage_id"));
			garage.setIsFree(resultSet.getBoolean("garage_is_free"));
			order.setGarage(garage);

			Long master_id = resultSet.getLong("master_id");
			if (master_id != null) {
				Master master = new Master();
				master.setId(master_id);
				master.setName(resultSet.getString("master_name"));
				master.setIsFree(resultSet.getBoolean("master_is_free"));
				order.setMaster(master);
			}
			orders.add(order);
		}
		return orders;
	}

	@Override
	public void prepareStatementForUpdate(PreparedStatement statement, Order object) throws SQLException {
		statement.setString(1, object.getState().toString());
		statement.setDate(2, convertDate(object.getSubmissionDate()));
		statement.setDate(3, convertDate(object.getExecutionDate()));
		statement.setDate(4, convertDate(object.getPlannedStartDate()));
		statement.setDouble(5, object.getPrice());
		statement.setLong(6, object.getGarage().getId());
		if (object.getMaster() == null) {
			statement.setNull(7, java.sql.Types.INTEGER);
		} else {
			statement.setLong(7, object.getMaster().getId());
		}
		statement.setLong(8, object.getId());

	}

	@Override
	public void prepareStatementForInsert(PreparedStatement statement, Order object) throws SQLException {
		statement.setString(1, object.getState().toString());
		statement.setDate(2, convertDate(object.getSubmissionDate()));
		statement.setDate(3, convertDate(object.getExecutionDate()));
		statement.setDate(4, convertDate(object.getPlannedStartDate()));
		statement.setDouble(5, object.getPrice());
		statement.setLong(6, object.getGarage().getId());
		if (object.getMaster() == null) {
			statement.setNull(7, java.sql.Types.INTEGER);
		} else {
			statement.setLong(7, object.getMaster().getId());
		}
	}

	@Override
	public List<Order> getOrdersByState(Connection connection, OrderState state, String sort) throws Exception {
		List<Order> list = new ArrayList<>();
		String sql = SELECT_BY_STATE_QUERY;
		if (sort != null) {
			sql += " ORDER BY " + sort;
		}
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, state.name());
			ResultSet resultSet = statement.executeQuery();
			list = parseResultSet(resultSet);
		} catch (SQLException e) {
			log.error("SQLException", e);
			throw new Exception(e);
		}
		return list;
	}

	@Override
	public List<Order> getOrdersByStateAndPeriod(Connection connection, OrderState state,
			java.util.Date startTimePeriod, java.util.Date endTimePeriod) throws Exception {
		List<Order> list = new ArrayList<>();
		try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_STATE_AND_PERIOD_QUERY)) {
			statement.setString(1, state.name());
			statement.setDate(2, convertDate(startTimePeriod));
			statement.setDate(3, convertDate(endTimePeriod));
			ResultSet resultSet = statement.executeQuery();
			list = parseResultSet(resultSet);
		} catch (SQLException e) {
			log.error("SQLException", e);
			throw new Exception(e);
		}
		return list;
	}

	@Override
	public Order getOrderByMaster(Connection connection, Master master) throws Exception {
		if (master == null) {
			return null;
		}
		List<Order> list = new ArrayList<>();
		try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_MASTER_QUERY)) {
			statement.setLong(1, master.getId());
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
	public int getExecutedOnDateOrdersNum(Connection connection, java.util.Date date) throws Exception {
		int ordersNum = 0;
		try (PreparedStatement statement = connection.prepareStatement(EXECUTED_ON_DATE_ORDERS_NUM_QUERY);) {
			statement.setDate(1, convertDate(date));
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			ordersNum = resultSet.getInt("number");
		} catch (SQLException e) {
			log.error("SQLException", e);
			throw new Exception(e);
		}
		return ordersNum;
	}

	private Date convertDate(java.util.Date date) {
		return new Date(date.getTime());
	}

}
