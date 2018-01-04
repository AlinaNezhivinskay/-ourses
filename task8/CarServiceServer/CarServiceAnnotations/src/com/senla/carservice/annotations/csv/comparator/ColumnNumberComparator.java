package com.senla.carservice.annotations.csv.comparator;

import java.util.Comparator;

import com.senla.carservice.annotations.csv.CsvProperty;

public class ColumnNumberComparator implements Comparator<CsvProperty> {

	@Override
	public int compare(CsvProperty o1, CsvProperty o2) {
		return Integer.compare(o1.columnNumber(), o2.columnNumber());

	}

}
