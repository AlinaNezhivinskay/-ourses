package com.senla.carservice.beans;

import com.senla.carservice.beans.abstractentity.Entity;

public class HistoryOrder extends Entity {
	private long id;
	private Order order;
	private Master master;

	public HistoryOrder(Order order, Master master) {
		this.order = order;
		this.master = master;
	}

	public HistoryOrder(long id, Order order, Master master) {
		this(order, master);
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public Master getMaster() {
		return master;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;

	}

	@Override
	public boolean equils(Object o) {
		if (o == null)
			return false;
		return (id == ((Garage) o).getId()) ? true : false;
	}

}
