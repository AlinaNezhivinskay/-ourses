package com.senla.carservice.ui.actions.master;

import com.senla.carservice.model.beans.Master;
import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.util.utils.Reader;
import com.senla.carservice.view.facade.CarService;

public class AddMaster implements IAction {

	@Override
	public void execute() {
		Printer.print("Input Master name");
		CarService.getInstance().addMaster(new Master(Reader.readString()));
		Printer.print("Master successfully added");
	}

}
