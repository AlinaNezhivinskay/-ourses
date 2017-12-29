package com.senla.carservice.ui.actions.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.senla.carservice.model.beans.Order;
import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.ui.handler.ClientHandler;
import com.senla.carservice.util.carservicecommands.Commands;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.util.utils.Reader;

public class RemoveOrder implements IAction {

	@SuppressWarnings("unchecked")
	@Override
	public void execute() {
		Map<Commands, List<Object>> request = new HashMap<>();
		request.put(Commands.getOrders, null);
		Map<String, Object> responce = ClientHandler.handle(request);
		Printer.print((List<Order>) responce.get("value"));
		Printer.print("Choose Order to remove");

		List<Object> params = new ArrayList<>();
		params.add(Reader.readLong());
		request = new HashMap<>();
		request.put(Commands.getOrderById, params);
		responce = ClientHandler.handle(request);

		Order order = (Order) responce.get("value");

		params = new ArrayList<>();
		params.add(order);
		request = new HashMap<>();
		request.put(Commands.removeOrder, params);
		responce = ClientHandler.handle(request);

		if ((boolean) responce.get("value")) {
			Printer.print("Removal was successful");
		} else {
			Printer.print("Removal was NOT successful");
		}

	}

}
