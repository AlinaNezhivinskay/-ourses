package com.senla.carservice.comparators.entity;

import java.util.Comparator;

import com.senla.carservice.beans.abstractentity.Entity;

public class NullEntityComparator implements Comparator<Entity> {

	@Override
	public int compare(Entity o1, Entity o2) {
		if (o1 == null) {
			return 1;
		}
		if (o2 == null) {
			return -1;
		}
		return 0;
	}

}
