package com.senla.carservice.ui.actions.garage;

import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.util.utils.Reader;
import com.senla.carservice.view.facade.CarService;

public class RemoveGarage implements IAction {

	@Override
	public void execute() {
		CarService carService = CarService.getInstance();
		Printer.print(carService.getGarages());
		Printer.print("Choose garage to remove");
		Garage garage = carService.getGarageById(Reader.readInt());
		if (carService.removeGarage(garage)) {
			Printer.print("Removal was successful");
		}
		Printer.print("Removal was NOT successful");

	}

}
