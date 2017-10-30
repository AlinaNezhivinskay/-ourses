package com.senla.carservice.comparators.masters;

import java.util.Comparator;
import com.senla.carservice.beans.Master;

public class DescendingAlphabetComparator implements Comparator<Master> {

	@Override
	public int compare(Master master1, Master master2) {
		if (master1 == null && master2 == null) {
			return 0;
		}
		if (master1 == null) {
			return 1;
		}
		if (master2 == null) {
			return -1;
		}
		return master1.getName().compareTo(master2.getName()) * -1;
	}

}
