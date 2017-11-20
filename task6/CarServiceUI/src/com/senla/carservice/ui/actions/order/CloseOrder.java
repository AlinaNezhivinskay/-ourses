package com.senla.carservice.ui.actions.order;

import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.util.utils.Reader;
import com.senla.carservice.view.facade.CarService;

public class CloseOrder implements IAction {

	@Override
	public void execute() {
		CarService carService = CarService.getInstance();
		Printer.print(carService.getOrders());
		Printer.print("Choose Order to close");
		if (carService.closeOrder(carService.getOrderById(Reader.readInt()))) {
			Printer.print("Order was closed");
		} else {
			Printer.print("Order was not closed, because there is no such order.");
		}
	}

}
