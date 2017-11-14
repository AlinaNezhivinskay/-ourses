package com.senla.carservice.ui.actions.order;

import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.util.utils.Reader;
import com.senla.carservice.view.facade.CarService;

public class AssignMasterToOrder implements IAction {

	@Override
	public void execute() {
		CarService carService = CarService.getInstance();
		Printer.print(carService.getOrders());
		Printer.print("Choose order");
		Order order = carService.getOrderById(Reader.readInt());
		Printer.print(carService.getFreeMasters());
		Printer.print("Choose master");
		Master master = carService.getMasterById(Reader.readInt());
		carService.assignMasterToOrder(order, master);
	}

}
