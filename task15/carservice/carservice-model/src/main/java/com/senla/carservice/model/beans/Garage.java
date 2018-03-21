package com.senla.carservice.model.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.senla.carservice.annotations.csv.CsvEntity;
import com.senla.carservice.annotations.csv.CsvProperty;
import com.senla.carservice.annotations.csv.propertytype.PropertyType;
import com.senla.carservice.model.beans.abstractentity.Entity;

@javax.persistence.Entity
@Table(name="garages")
@CsvEntity(filename = "garages.csv", valuesSeparator = ";", entityId = "id")
public class Garage extends Entity implements Serializable {
	
	private static final long serialVersionUID = -7129861430140681555L;
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 1)
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private long id;
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 2)
	@Column(name="is_free")
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
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		return (id == ((Garage) obj).getId()) ? true : false;
	}

	@Override
	public String toString() {
		return id + " " + getIsFree();
	}
}
