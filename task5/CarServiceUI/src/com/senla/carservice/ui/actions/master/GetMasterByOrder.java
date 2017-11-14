package com.senla.carservice.ui.actions.master;

import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.util.utils.Reader;
import com.senla.carservice.view.facade.CarService;

public class GetMasterByOrder implements IAction {

	@Override
	public void execute() {
		CarService carService = CarService.getInstance();
		Printer.print(carService.getOrders());
		Printer.print("Choose order");
		Printer.print(carService.getMasterByOrder(carService.getOrderById(Reader.readInt())));
	}

}
