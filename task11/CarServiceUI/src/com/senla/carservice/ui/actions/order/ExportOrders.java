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

public class ExportOrders implements IAction {

	@SuppressWarnings("unchecked")
	@Override
	public void execute() {
		List<Object> params;
		Map<Commands, List<Object>> request = new HashMap<>();
		request.put(Commands.getOrders, null);
		Map<String, Object> responce = ClientHandler.handle(request);

		List<Order> ordersInRep = (List<Order>) responce.get("value");
		if (ordersInRep.size() == 0) {
			Printer.print("There is no orders!");
		} else {

			Printer.print(ordersInRep);
			Printer.print("Choose orders to export (1-end input,2-all)");
			List<Order> orders = new ArrayList<Order>();
			long index = Reader.readLong();
			while (index != 1 && index != 2) {
				params = new ArrayList<>();
				params.add(index);
				request = new HashMap<>();
				request.put(Commands.getOrderById, params);
				responce = ClientHandler.handle(request);

				Order order = (Order) responce.get("value");
				if (order != null) {
					orders.add(order);
				}
				index = Reader.readLong();
			}
			if (index == 2) {
				request = new HashMap<>();
				request.put(Commands.getOrders, null);
				responce = ClientHandler.handle(request);
				orders = (List<Order>) responce.get("value");
			}
			params = new ArrayList<>();
			params.add(orders);
			request = new HashMap<>();
			request.put(Commands.exportOrders, params);
			responce = ClientHandler.handle(request);
		}

	}

}
