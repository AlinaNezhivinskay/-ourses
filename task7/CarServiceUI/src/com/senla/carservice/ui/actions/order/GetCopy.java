package com.senla.carservice.ui.actions.order;

import com.senla.carservice.api.facade.ICarService;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.util.utils.Reader;
import com.senla.carservice.view.facade.CarService;

public class GetCopy implements IAction {

	@Override
	public void execute() {
		ICarService carService = CarService.getInstance();
		Printer.print(carService.getOrders());
		Printer.print("Choose Order to copy");
		Order order = carService.getCopy(carService.getOrderById(Reader.readInt()));
		if (order == null) {
			Printer.print("Order cannot be copy");
		} else {
			Printer.print(order);
			carService.addOrder(order);
		}

	}

}
