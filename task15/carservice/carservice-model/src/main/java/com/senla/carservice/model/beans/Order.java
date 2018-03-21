package com.senla.carservice.model.beans;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.senla.carservice.annotations.csv.CsvEntity;
import com.senla.carservice.annotations.csv.CsvProperty;
import com.senla.carservice.annotations.csv.propertytype.PropertyType;
import com.senla.carservice.model.beans.abstractentity.Entity;
import com.senla.carservice.model.orderstate.OrderState;

@javax.persistence.Entity
@Table(name = "orders")
@CsvEntity(filename = "orders.csv", valuesSeparator = ";", entityId = "id")
public class Order extends Entity implements Cloneable, Serializable {

	private static final long serialVersionUID = 2022335980465471810L;
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 2)
	@Column(name = "submission_date")
	private Date submissionDate;
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 3)
	@Column(name = "execution_date")
	private Date executionDate;
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 4)
	@Column(name = "planned_start_date")
	private Date plannedStartDate;
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 5)
	@Column(name = "price")
	private double price;
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 6)
	@Column(name = "order_state")
	@Enumerated(EnumType.STRING)
	private OrderState state;
	@CsvProperty(propertyType = PropertyType.CompositeProperty, columnNumber = 7, keyField = "id")
	@ManyToOne
	@JoinColumn(name = "garage_id")
	private Garage garage;
	@CsvProperty(propertyType = PropertyType.CompositeProperty, columnNumber = 8, keyField = "id")
	@ManyToOne
	@JoinColumn(name = "master_id")
	private Master master;

	public Order() {

	}

	public Order(Date submissionDate, Date executionDate, Date plannedStartDate, double price, Garage garage)
			throws ParseException {
		this.submissionDate = submissionDate;
		this.executionDate = executionDate;
		this.plannedStartDate = plannedStartDate;
		this.price = price;
		state = OrderState.EXECUTABLE;
		garage.setIsFree(false);
		this.garage = garage;
	}

	public Order(long id, Date submissionDate, Date executionDate, Date plannedStartDate, double price,
			OrderState state, Garage garage, Master master) throws ParseException {
		this(submissionDate, executionDate, plannedStartDate, price, garage);
		this.id = id;
		this.state = state;
		this.master = master;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	public Date getExecutionDate() {
		return executionDate;
	}

	public void setExecutionDate(Date executionDate) {
		this.executionDate = executionDate;
	}

	public Date getPlannedStartDate() {
		return plannedStartDate;
	}

	public void setPlannedStartDate(Date plannedStartDate) {
		this.plannedStartDate = plannedStartDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public OrderState getState() {
		return state;
	}

	public void setState(OrderState state) {
		this.state = state;
	}

	public Garage getGarage() {
		return garage;
	}

	public void setGarage(Garage garage) {
		this.garage = garage;
	}

	public Master getMaster() {
		return master;
	}

	public void setMaster(Master master) {
		this.master = master;
	}

	@Override
	public boolean equals(Object o) {
		return (id == ((Order) o).getId()) ? true : false;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(id).append(' ').append("submissionDate: ").append(submissionDate).append(' ')
				.append("executionDate: ").append(executionDate).append(' ').append("plannedStartDate: ")
				.append(plannedStartDate).append(' ').append(price).append(' ').append(state).append(' ');
		if (garage != null) {
			str.append("garage: ").append(garage.getId()).append(' ').append(garage.getIsFree());
		}
		if (master != null) {
			str.append("master: ").append(master.getId()).append(' ').append(master.getIsFree());
		}
		return str.toString();
	}

	@Override
	public Order clone() throws CloneNotSupportedException {
		Order order = (Order) super.clone();
		order.setId(-1);
		order.setGarage(null);
		order.setMaster(null);
		return order;
	}
}
