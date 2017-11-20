package com.senla.carservice.ui.actions.order;

import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.util.utils.Reader;
import com.senla.carservice.view.facade.CarService;

public class ImportOrders implements IAction {

	@Override
	public void execute() {
		CarService carService = CarService.getInstance();
		Printer.print("Input File Path");
		String filePath = Reader.readFilePath();
		if (carService.importOrders(filePath)) {
			Printer.print("Import was successfull");
		} else {
			Printer.print("Import was NOT successfull");
		}

	}

}
