package com.senla.carservice.beans;

import com.senla.carservice.beans.abstractentity.Entity;

public class Master extends Entity {
	private long id;
	private String name;
	private boolean isFree;

	public Master(String name) {
		this.name = name;
		this.isFree=true;
	}

	public Master(long id, String name,boolean isFree) {
		this.id = id;
		this.name = name;
		this.isFree=isFree;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public boolean getIsFree() {
		return isFree;
	}

	public void setIsFree(boolean isFree) {
		this.isFree = isFree;
	}

	@Override
	public boolean equils(Object o) {
		return (id == ((Master) o).getId()) ? true : false;
	}

	@Override
	public String toString() {
		return new StringBuilder().append(id).append(' ').append(name).toString();
	}
}
