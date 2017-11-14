package com.senla.carservice.ui.actions.order;

import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.view.facade.CarService;

public class SortExecutingOrdersByExecutionDate implements IAction {

	@Override
	public void execute() {
		CarService carService = CarService.getInstance();
		carService.sortExecutingOrdersByExecutionDate();
		Printer.print(carService.getCurrentExecutingOrders());

	}

}
