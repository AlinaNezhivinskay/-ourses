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

public class GetCopy implements IAction {

	@SuppressWarnings("unchecked")
	@Override
	public void execute() {
		Map<Commands, List<Object>> request = new HashMap<>();
		request.put(Commands.getOrders, null);
		Map<String, Object> responce = ClientHandler.handle(request);
		Printer.print((List<Order>) responce.get("value"));
		Printer.print("Choose Order to copy");

		List<Object> params = new ArrayList<>();
		params.add(Reader.readInt());
		request = new HashMap<>();
		request.put(Commands.getOrderById, params);
		responce = ClientHandler.handle(request);

		Order order = (Order) responce.get("value");

		params = new ArrayList<>();
		params.add(order);
		request = new HashMap<>();
		request.put(Commands.getCopy, params);
		responce = ClientHandler.handle(request);

		Order orderCopy = (Order) responce.get("value");

		if (orderCopy == null) {
			Printer.print("Order cannot be copy");
		} else {
			Printer.print(orderCopy);
			params.add(orderCopy);
			request = new HashMap<>();
			request.put(Commands.addOrder, params);
			ClientHandler.handle(request);
		}

	}

}
