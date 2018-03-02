package com.senla.carservice.ui.actions.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.ui.handler.ClientHandler;
import com.senla.carservice.util.carservicecommands.Commands;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.util.utils.Reader;

public class UpdateOrder implements IAction {

	@SuppressWarnings("unchecked")
	@Override
	public void execute() {
		Map<Commands, List<Object>> request = new HashMap<>();
		request.put(Commands.getOrders, null);
		Map<String, Object> responce = ClientHandler.handle(request);
		Printer.print((List<Order>) responce.get("value"));
		Printer.print("Choose Order to change");
		List<Object> params = new ArrayList<>();
		params.add(Reader.readLong());
		request = new HashMap<>();
		request.put(Commands.getOrderById, params);
		responce = ClientHandler.handle(request);

		Order order = (Order) responce.get("value");
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
				request = new HashMap<>();
				request.put(Commands.getGarages, null);
				responce = ClientHandler.handle(request);
				Printer.print((List<Garage>) responce.get("value"));
				Printer.print("Choose garage");

				params = new ArrayList<>();
				params.add(Reader.readLong());
				request = new HashMap<>();
				request.put(Commands.getGarageById, params);
				responce = ClientHandler.handle(request);

				Garage garage = (Garage) responce.get("value");

				params = new ArrayList<>();
				params.add(order);
				params.add(garage);
				request = new HashMap<>();
				request.put(Commands.assignGarageToOrder, params);
				responce = ClientHandler.handle(request);

				if (!(boolean) responce.get("value")) {
					Printer.print("Garage is NOT free");
				}
				break;
			case 6:
				request = new HashMap<>();
				request.put(Commands.getFreeMasters, null);
				responce = ClientHandler.handle(request);
				Printer.print((List<Master>) responce.get("value"));

				Printer.print("Choose master");
				params = new ArrayList<>();
				params.add(Reader.readLong());
				request = new HashMap<>();
				request.put(Commands.getMasterById, params);
				responce = ClientHandler.handle(request);

				Master master = (Master) responce.get("value");

				params = new ArrayList<>();
				params.add(order);
				params.add(master);
				request = new HashMap<>();
				request.put(Commands.assignMasterToOrder, params);
				responce = ClientHandler.handle(request);

				if (!(boolean) responce.get("value")) {
					Printer.print("Master is NOT free");
				}
				break;
			}
		} while (index != 0);

		params = new ArrayList<>();
		params.add(order);
		request = new HashMap<>();
		request.put(Commands.updateOrder, params);
		responce = ClientHandler.handle(request);
		if ((boolean) responce.get("value")) {
			Printer.print("Updating was successfull");
		} else {
			Printer.print("Updating was NOT successfull");
		}
	}

}
