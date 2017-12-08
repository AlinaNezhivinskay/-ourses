package com.senla.carservice.ui.actions.master;

import com.senla.carservice.api.facade.ICarService;
import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.util.utils.Reader;
import com.senla.carservice.view.facade.CarService;

public class RemoveMaster implements IAction {

	@Override
	public void execute() {
		ICarService carService = CarService.getInstance();
		Printer.print(carService.getMasters());
		Printer.print("Choose Master to remove");
		if (carService.removeMaster(carService.getMasterById(Reader.readInt()))) {
			Printer.print("Removal was successful");
		}
		Printer.print("Removal was NOT successful");

	}

}
