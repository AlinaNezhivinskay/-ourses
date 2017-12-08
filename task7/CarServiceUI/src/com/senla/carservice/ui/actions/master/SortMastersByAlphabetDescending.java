package com.senla.carservice.ui.actions.master;

import com.senla.carservice.api.facade.ICarService;
import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.view.facade.CarService;

public class SortMastersByAlphabetDescending implements IAction {

	@Override
	public void execute() {
		ICarService carService = CarService.getInstance();
		carService.sortMastersByAlphabetDescending();
		Printer.print(carService.getMasters());
	}

}
