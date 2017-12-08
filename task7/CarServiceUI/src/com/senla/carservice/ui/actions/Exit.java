package com.senla.carservice.ui.actions;

import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.view.facade.CarService;

public class Exit implements IAction {

	@Override
	public void execute() {
		if (!CarService.getInstance().exit()) {
			Printer.print("File Not Found Exception");
		}
	}

}
