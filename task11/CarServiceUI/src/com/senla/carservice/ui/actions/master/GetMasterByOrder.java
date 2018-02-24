package com.senla.carservice.ui.actions.master;

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

public class GetMasterByOrder implements IAction {

	@SuppressWarnings("unchecked")
	@Override
	public void execute() {
		List<Object> params;
		Map<Commands, List<Object>> request = new HashMap<>();
		request.put(Commands.getOrders, null);
		Map<String, Object> responce = ClientHandler.handle(request);
		Printer.print((List<Order>) responce.get("value"));
		Printer.print("Choose order");

		params = new ArrayList<>();
		params.add(Reader.readLong());
		request = new HashMap<>();
		request.put(Commands.getOrderById, params);
		responce = ClientHandler.handle(request);

		Order order = (Order) responce.get("value");

		params = new ArrayList<>();
		params.add(order);
		request = new HashMap<>();
		request.put(Commands.getMasterByOrder, params);
		responce = ClientHandler.handle(request);

		Master master = (Master) responce.get("value");

		if (master != null) {
			Printer.print(master);
		} else {
			Printer.print("There is no master for this order!!!");
		}
	}

}
