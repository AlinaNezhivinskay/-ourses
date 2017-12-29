package com.senla.carservice.model.comparators.entity;

import java.util.Comparator;

import com.senla.carservice.model.beans.abstractentity.Entity;

public class IdComparator implements Comparator<Entity> {

	@Override
	public int compare(Entity o1, Entity o2) {
		return Long.compare(o1.getId(), o2.getId());
	}

}
