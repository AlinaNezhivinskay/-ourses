package com.senla.carservice.ui.actions.garage;

import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.view.facade.CarService;

public class GetFreeGarages implements IAction {

	@Override
	public void execute() {
		Printer.print(CarService.getInstance().getFreeGarages());
	}

}
