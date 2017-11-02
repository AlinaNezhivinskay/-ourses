package com.senla.carservice.comparators.entity;

import java.util.Comparator;

import com.senla.carservice.beans.abstractentity.Entity;

public class IdComparator implements Comparator<Entity> {

	@Override
	public int compare(Entity o1, Entity o2) {
		return Long.compare(o1.getId(), o2.getId());
	}

}
