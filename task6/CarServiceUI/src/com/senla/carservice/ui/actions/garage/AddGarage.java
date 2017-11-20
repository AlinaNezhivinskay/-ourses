package com.senla.carservice.ui.actions.garage;

import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.view.facade.CarService;

public class AddGarage implements IAction {

	@Override
	public void execute() {
		CarService.getInstance().addGarage(new Garage());
		Printer.print("Garage successfully added");
	}

}
