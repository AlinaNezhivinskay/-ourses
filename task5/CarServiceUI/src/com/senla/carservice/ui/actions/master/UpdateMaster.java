package com.senla.carservice.ui.actions.master;

import com.senla.carservice.model.beans.Master;
import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.util.utils.Reader;
import com.senla.carservice.view.facade.CarService;

public class UpdateMaster implements IAction {

	@Override
	public void execute() {
		CarService carService = CarService.getInstance();
		Printer.print(carService.getMasters());
		Printer.print("Choose master to update");
		Master master = carService.getMasterById(Reader.readInt());
		Printer.print("1-free, 2-Not free");
		Printer.print("Choose state");
		boolean state = Reader.readInt() == 1 ? true : false;
		carService.updateMaster(master, state);
		Printer.print("Master is updated");

	}

}
