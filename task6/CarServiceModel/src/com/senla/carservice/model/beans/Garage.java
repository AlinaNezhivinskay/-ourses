package com.senla.carservice.model.beans;

import java.io.Serializable;

import com.senla.carservice.model.beans.abstractentity.Entity;

public class Garage extends Entity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7129861430140681555L;
	private long id;
	private boolean isFree;

	public Garage() {
		isFree = true;
	}

	public Garage(long id, boolean isFree) {
		this.id = id;
		this.isFree = isFree;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	public boolean getIsFree() {
		return isFree;
	}

	public void setIsFree(boolean isFree) {
		this.isFree = isFree;
	}

	@Override
	public boolean equils(Object o) {
		if (o == null)
			return false;
		return (id == ((Garage) o).getId()) ? true : false;
	}

	@Override
	public String toString() {
		return id + " " + getIsFree();
	}
}
