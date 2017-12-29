package com.senla.carservice.model.comparators.masters;

import java.util.Comparator;

import com.senla.carservice.model.beans.Master;

public class EmploymentComparator implements Comparator<Master> {

	@Override
	public int compare(Master master1, Master master2) {
		if (master1 == null) {
			return 1;
		}
		if (master2 == null) {
			return -1;
		}
		return 0;
	}

}
