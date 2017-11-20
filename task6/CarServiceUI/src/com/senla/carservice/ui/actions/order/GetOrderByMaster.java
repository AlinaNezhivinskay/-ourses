package com.senla.carservice.ui.actions.order;

import com.senla.carservice.model.beans.Order;
import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.util.utils.Reader;
import com.senla.carservice.view.facade.CarService;

public class GetOrderByMaster implements IAction {

	@Override
	public void execute() {
		CarService carService = CarService.getInstance();
		Printer.print(carService.getMasters());
		Printer.print("Choose Master");
		Order order = carService.getOrderByMaster(carService.getMasterById(Reader.readInt()));
		if (order != null) {
			Printer.print(order);
		}else {
			Printer.print("There are no order for this master!!!");
		}

	}

}
