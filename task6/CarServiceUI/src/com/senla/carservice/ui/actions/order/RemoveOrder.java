package com.senla.carservice.ui.actions.order;

import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.util.utils.Reader;
import com.senla.carservice.view.facade.CarService;

public class RemoveOrder implements IAction {

	@Override
	public void execute() {
		CarService carService = CarService.getInstance();
		Printer.print(carService.getOrders());
		Printer.print("Choose Order to remove");
		if (carService.removeOrder(carService.getOrderById(Reader.readInt()))) {
			Printer.print("Removal was successful");
		}
		Printer.print("Removal was NOT successful");

	}

}
