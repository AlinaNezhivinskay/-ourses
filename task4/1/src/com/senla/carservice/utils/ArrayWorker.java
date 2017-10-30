package com.senla.carservice.utils;

import com.senla.carservice.beans.Garage;
import com.senla.carservice.beans.HistoryOrder;
import com.senla.carservice.beans.Master;
import com.senla.carservice.beans.Order;
import com.senla.carservice.beans.abstractentity.Entity;

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

	public static HistoryOrder[] expandArray(HistoryOrder[] historyOrders) {
		int length = historyOrders.length * 2;
		HistoryOrder[] newEntitiesArray = new HistoryOrder[length];
		for (int i = 0; i < historyOrders.length; i++) {
			newEntitiesArray[i] = historyOrders[i];
		}
		return newEntitiesArray;
	}

	public static Garage[] expandArray(Garage[] garages) {
		int length = garages.length * 2;
		Garage[] newGarageArray = new Garage[length];
		for (int i = 0; i < garages.length; i++) {
			newGarageArray[i] = garages[i];
		}
		return newGarageArray;
	}

	public static Master[] expandArray(Master[] masters) {
		int length = masters.length * 2;
		Master[] newMastersArray = new Master[length];
		for (int i = 0; i < masters.length; i++) {
			newMastersArray[i] = masters[i];
		}
		return newMastersArray;
	}

	public static Order[] expandArray(Order[] orders) {
		int length = orders.length * 2;
		Order[] newOrderArray = new Order[length];
		for (int i = 0; i < orders.length; i++) {
			newOrderArray[i] = orders[i];
		}
		return newOrderArray;
	}
}
