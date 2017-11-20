package com.senla.carservice.ui.actions.master;

import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.util.utils.Reader;
import com.senla.carservice.view.facade.CarService;

public class ImportMasters implements IAction {

	@Override
	public void execute() {
		CarService carService = CarService.getInstance();
		Printer.print("Input File Path");
		String filePath = Reader.readFilePath();
		if (carService.importMasters(filePath)) {
			Printer.print("Import was successfull");
		} else {
			Printer.print("Import was NOT successfull");
		}

	}

}
