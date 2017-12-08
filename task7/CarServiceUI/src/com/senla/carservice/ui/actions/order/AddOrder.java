package com.senla.carservice.ui.actions.order;

import java.text.ParseException;
import java.util.Date;

import com.senla.carservice.api.facade.ICarService;
import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.util.utils.Reader;
import com.senla.carservice.view.facade.CarService;

public class AddOrder implements IAction {

	@Override
	public void execute() {
		ICarService carService = CarService.getInstance();
		Printer.print("Input Order execution date (dd.MM.yyyy)");
		Date executionDate = Reader.readDate();
		Printer.print("Input Order planned start date (dd.MM.yyyy)");
		Date plannedStartDate = Reader.readDate();
		Printer.print("Input Order price");
		double price = Reader.readDouble();
		Printer.print(carService.getFreeGarages());
		Printer.print("Choose Garage");
		Garage garage = carService.getGarageById(Reader.readInt());
		try {
			carService.addOrder(new Order(new Date(), executionDate, plannedStartDate, price, garage));
		} catch (ParseException e) {
			Printer.print("Wrong Date Format!!!");
		}
		Printer.print("Master successfully added");

	}

}
