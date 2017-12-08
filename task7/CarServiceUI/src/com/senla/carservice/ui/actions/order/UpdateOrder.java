package com.senla.carservice.ui.actions.order;

import com.senla.carservice.api.facade.ICarService;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.util.utils.Reader;
import com.senla.carservice.view.facade.CarService;

public class UpdateOrder implements IAction {

	@Override
	public void execute() {
		ICarService carService = CarService.getInstance();
		Printer.print(carService.getOrders());
		Printer.print("Choose Order to change");
		Order order = carService.getOrderById(Reader.readInt());
		int index;
		do {
			Printer.print("Choose what you want to change");
			Printer.print("1-Submission Date");
			Printer.print("2-Execution Date");
			Printer.print("3-Planned Start Date");
			Printer.print("4-Price");
			Printer.print("5-Garage");
			Printer.print("6-Master");
			Printer.print("0-Exit");
			index = Reader.readInt();
			switch (index) {
			case 1:
				Printer.print("Input Submission Date");
				order.setSubmissionDate(Reader.readDate());
				break;
			case 2:
				Printer.print("Input Execution Date");
				order.setExecutionDate(Reader.readDate());
				break;
			case 3:
				Printer.print("Input Planned Start Date");
				order.setPlannedStartDate(Reader.readDate());
				break;
			case 4:
				Printer.print("Input Price");
				order.setPrice(Reader.readDouble());
				break;
			case 5:
				Printer.print(carService.getGarages());
				Printer.print("Choose garage");
				if (!carService.assignGarageToOrder(order, carService.getGarageById(Reader.readInt()))) {
					Printer.print("Garage is NOT free");
				}
				break;
			case 6:
				Printer.print(carService.getMasters());
				Printer.print("Choose master");
				if (!carService.assignMasterToOrder(order, carService.getMasterById(Reader.readInt()))) {
					Printer.print("Master is NOT free");
				}
				break;
			}
		} while (index != 0);
		if(carService.updateOrder(order)) {
			Printer.print("Updating was successfull");
		}
		else {
			Printer.print("Updating was NOT successfull");
		}
	}

}
