package com.senla.carservice.ui.actions.order;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.ui.handler.ClientHandler;
import com.senla.carservice.util.carservicecommands.Commands;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.util.utils.Reader;

public class AddOrder implements IAction {

	@SuppressWarnings("unchecked")
	@Override
	public void execute() {
		List<Object> params = new ArrayList<>();
		Printer.print("Input Order execution date (dd.MM.yyyy)");
		Date executionDate = Reader.readDate();
		Printer.print("Input Order planned start date (dd.MM.yyyy)");
		Date plannedStartDate = Reader.readDate();
		Printer.print("Input Order price");
		double price = Reader.readDouble();

		Map<Commands, List<Object>> request = new HashMap<>();
		request.put(Commands.getFreeGarages, null);
		Map<String, Object> responce = ClientHandler.handle(request);
		Printer.print((List<Garage>) responce.get("value"));

		Printer.print("Choose Garage");
		params = new ArrayList<>();
		params.add(Reader.readLong());
		request = new HashMap<>();
		request.put(Commands.getGarageById, params);
		responce = ClientHandler.handle(request);

		Garage garage = (Garage) responce.get("value");
		try {
			params = new ArrayList<>();
			params.add(new Order(new Date(), executionDate, plannedStartDate, price, garage));
			request = new HashMap<>();
			request.put(Commands.addOrder, params);
			ClientHandler.handle(request);
			Printer.print("Order successfully added");
		} catch (ParseException e) {
			Printer.print("Wrong date format");
		}

	}

}
