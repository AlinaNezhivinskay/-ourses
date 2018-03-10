package com.senla.carservice.ui.actions.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.ui.handler.ClientHandler;
import com.senla.carservice.util.carservicecommands.Commands;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.util.utils.Reader;

public class AssignMasterToOrder implements IAction {

	@SuppressWarnings("unchecked")
	@Override
	public void execute() {

		Map<Commands, List<Object>> request = new HashMap<>();
		request.put(Commands.getOrders, null);
		Map<String, Object> responce = ClientHandler.handle(request);
		Printer.print((List<Order>) responce.get("value"));

		Printer.print("Choose order");
		List<Object> params = new ArrayList<>();
		params.add(Reader.readLong());
		request = new HashMap<>();
		request.put(Commands.getOrderById, params);
		responce = ClientHandler.handle(request);

		Order order = (Order) responce.get("value");

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
		ClientHandler.handle(request);

	}

}
