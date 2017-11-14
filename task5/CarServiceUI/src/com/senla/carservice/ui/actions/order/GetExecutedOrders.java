package com.senla.carservice.ui.actions.order;

import java.util.Date;

import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.util.utils.Reader;
import com.senla.carservice.view.facade.CarService;

public class GetExecutedOrders implements IAction {

	@Override
	public void execute() {
		Printer.print("Input Order execution date (dd.MM.yyyy)");
		Date from = Reader.readDate();
		Printer.print("Input Order planned start date (dd.MM.yyyy)");
		Date to = Reader.readDate();
		Printer.print(CarService.getInstance().getExecutedOrders(from, to));

	}

}
