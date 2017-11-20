package com.senla.carservice.ui.actions.order;

import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.view.facade.CarService;

public class GetOrders implements IAction {

	@Override
	public void execute() {
		Printer.print(CarService.getInstance().getOrders());
	}

}
