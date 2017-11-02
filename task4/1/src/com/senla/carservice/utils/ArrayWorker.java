package com.senla.carservice.utils;

import java.util.Arrays;

import com.senla.carservice.beans.abstractentity.Entity;
import com.senla.carservice.comparators.entity.NullEntityComparator;

public class ArrayWorker {
	public static final int ARRAY_LENGTH = 10;

	public static boolean isEmptySpace(Entity[] entities) {
		for (int i = 0; i < entities.length; i++) {
			if (entities[i] == null)
				return true;
		}
		return false;
	}

	public static int getFreePosition(Entity[] entities) {
		for (int i = 0; i < entities.length; i++) {
			if (entities[i] == null)
				return i;
		}
		return -1;
	}

	public static boolean isElementOnArray(Entity[] entities, Entity entity) {
		if (entity != null) {
			for (int i = 0; i < entities.length; i++) {
				if (entities[i] == null)
					continue;
				if (entities[i].equals(entity))
					return true;
			}
		}
		return false;
	}

	public static int getPositionOfElement(Entity[] entities, Entity entity) {
		for (int i = 0; i < entities.length; i++) {
			if (entities[i].equals(entity))
				return i;
		}
		return -1;
	}

	public static boolean addElementInArray(Entity[] entities, Entity entity) {
		if (!ArrayWorker.isEmptySpace(entities))
			return false;
		entities[ArrayWorker.getFreePosition(entities)] = entity;
		return true;
	}

	public static Entity[] expandArray(Entity[] entities) {
		return Arrays.copyOf(entities, (int) (entities.length * 2));
	}

	public static Entity[] cutNullEntities(Entity[] entities) {
		if (isEmptySpace(entities)) {
			Arrays.sort(entities, new NullEntityComparator());
			return Arrays.copyOfRange(entities, 0, getFreePosition(entities));
		}
		return entities;
	}

}
