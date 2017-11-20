package com.senla.carservice.ui.actions;

import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.view.facade.CarService;

public class GetFreeDate implements IAction {

	@Override
	public void execute() {
		Printer.print("Free date: " + CarService.getInstance().getFreeDate());

	}

}
