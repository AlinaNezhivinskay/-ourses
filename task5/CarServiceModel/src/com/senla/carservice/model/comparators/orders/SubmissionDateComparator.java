package com.senla.carservice.model.comparators.orders;

import java.util.Comparator;
import com.senla.carservice.model.beans.Order;

public class SubmissionDateComparator implements Comparator<Order> {

	@Override
	public int compare(Order order1, Order order2) {
		if (order1 == null && order2 == null) {
			return 0;
		}
		if (order1 == null) {
			return 1;
		}
		if (order2 == null) {
			return -1;
		}
		return order1.getSubmissionDate().compareTo(order2.getSubmissionDate());
	}

}
