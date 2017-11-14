package com.senla.carservice.ui.actions.order;

import com.senla.carservice.model.beans.Order;
import com.senla.carservice.model.orderstate.OrderState;
import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.util.utils.Reader;
import com.senla.carservice.view.facade.CarService;

public class UpdateOrder implements IAction {

	@Override
	public void execute() {
		CarService carService = CarService.getInstance();
		Printer.print(carService.getOrders());
		Printer.print("Choose order to update");
		Order order = carService.getOrderById(Reader.readInt());
		Printer.print(1 + OrderState.CANCELED.toString());
		Printer.print(2 + OrderState.EXECUTABLE.toString());
		Printer.print(3 + OrderState.EXECUTED.toString());
		Printer.print(4 + OrderState.REMOTE.toString());
		int orderIndex = Reader.readInt();
		OrderState state = OrderState.EXECUTABLE;
		switch (orderIndex) {
		case 1:
			state = OrderState.CANCELED;
			break;
		case 2:
			state = OrderState.EXECUTABLE;
			break;
		case 3:
			state = OrderState.EXECUTED;
			break;
		case 4:
			state = OrderState.REMOTE;
			break;
		}
		carService.updateOrder(order, state);
		Printer.print("Master is updated");

	}

}
