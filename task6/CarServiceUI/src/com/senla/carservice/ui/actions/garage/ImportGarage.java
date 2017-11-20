package com.senla.carservice.ui.actions.garage;

import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.util.utils.Reader;
import com.senla.carservice.view.facade.CarService;

public class ImportGarage implements IAction {

	@Override
	public void execute() {
		CarService carService = CarService.getInstance();
		Printer.print("Input File Path");
		String filePath = Reader.readFilePath();
		if (carService.importGarages(filePath)) {
			Printer.print("Import was successfull");
		} else {
			Printer.print("Import was NOT successfull");
		}

	}

}
