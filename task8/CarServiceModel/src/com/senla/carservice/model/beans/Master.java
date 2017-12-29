package com.senla.carservice.model.beans;

import java.io.Serializable;

import com.senla.carservice.annotations.csv.CsvEntity;
import com.senla.carservice.annotations.csv.CsvProperty;
import com.senla.carservice.annotations.csv.propertytype.PropertyType;
import com.senla.carservice.model.beans.abstractentity.Entity;

@CsvEntity(filename = "masters.csv", valuesSeparator = ";", entityId = "id")
public class Master extends Entity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7346407055177887514L;
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 1)
	private long id;
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 2)
	private String name;
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 3)
	private boolean isFree;

	public Master() {

	}

	public Master(String name) {
		this.name = name;
		this.isFree = true;
	}

	public Master(long id, String name, boolean isFree) {
		this.id = id;
		this.name = name;
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
	public boolean equals(Object o) {
		return (id == ((Master) o).getId()) ? true : false;
	}

	@Override
	public String toString() {
		return new StringBuilder().append(id).append(' ').append(name).toString();
	}
}
