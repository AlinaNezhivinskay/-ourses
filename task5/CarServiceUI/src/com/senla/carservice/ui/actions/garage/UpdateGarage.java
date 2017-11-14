package com.senla.carservice.ui.actions.garage;

import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.util.utils.Reader;
import com.senla.carservice.view.facade.CarService;

public class UpdateGarage implements IAction {

	@Override
	public void execute() {
		CarService carService = CarService.getInstance();
		Printer.print(carService.getGarages());
		Printer.print("Choose garage to update");
		Garage garage = carService.getGarageById(Reader.readInt());
		Printer.print("1-free, 2-Not free");
		Printer.print("Choose state");
		boolean state = Reader.readInt() == 1 ? true : false;
		carService.updateGarage(garage, state);
		Printer.print("Garage is updated");

	}

}
