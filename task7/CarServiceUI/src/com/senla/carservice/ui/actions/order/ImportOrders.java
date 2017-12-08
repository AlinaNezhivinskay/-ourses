package com.senla.carservice.ui.actions.order;

import com.senla.carservice.api.facade.ICarService;
import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.view.facade.CarService;

public class ImportOrders implements IAction {

	@Override
	public void execute() {
		ICarService carService = CarService.getInstance();
		if (carService.importOrders()) {
			Printer.print("Import was successfull");
		} else {
			Printer.print("Import was NOT successfull");
		}

	}

}
