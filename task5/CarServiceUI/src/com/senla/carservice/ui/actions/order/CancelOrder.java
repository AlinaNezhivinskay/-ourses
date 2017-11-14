package com.senla.carservice.ui.actions.order;

import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.util.utils.Reader;
import com.senla.carservice.view.facade.CarService;

public class CancelOrder implements IAction {

	@Override
	public void execute() {
		CarService carService = CarService.getInstance();
		Printer.print(carService.getOrders());
		Printer.print("Choose Order to cancel");
		carService.cancelOrder(carService.getOrderById(Reader.readInt()));
		Printer.print("Order was canceted");

	}

}
