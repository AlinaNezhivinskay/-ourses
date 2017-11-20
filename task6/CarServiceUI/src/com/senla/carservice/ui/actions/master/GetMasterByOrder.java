package com.senla.carservice.ui.actions.master;

import com.senla.carservice.model.beans.Master;
import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.util.utils.Reader;
import com.senla.carservice.view.facade.CarService;

public class GetMasterByOrder implements IAction {

	@Override
	public void execute() {
		CarService carService = CarService.getInstance();
		Printer.print(carService.getOrders());
		Printer.print("Choose order");
		Master master = carService.getMasterByOrder(carService.getOrderById(Reader.readInt()));
		if (master != null) {
			Printer.print(master);
		} else {
			Printer.print("There is no master for this order!!!");
		}
	}

}
