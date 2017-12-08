package com.senla.carservice.ui.actions.order;

import com.senla.carservice.api.facade.ICarService;
import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.view.facade.CarService;

public class SortOrdersBySubmissionDate implements IAction {

	@Override
	public void execute() {
		ICarService carService = CarService.getInstance();
		carService.sortOrdersBySubmissionDate();
		Printer.print(carService.getOrders());

	}

}
