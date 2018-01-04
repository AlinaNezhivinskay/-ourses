package com.senla.carservice.ui.actions.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.senla.carservice.model.beans.Order;
import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.ui.handler.ClientHandler;
import com.senla.carservice.util.carservicecommands.Commands;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.util.utils.Reader;

public class GetCanceledOrders implements IAction {

	@SuppressWarnings("unchecked")
	@Override
	public void execute() {
		Printer.print("Input Order execution date (dd.MM.yyyy)");
		Date from = Reader.readDate();
		Printer.print("Input Order planned start date (dd.MM.yyyy)");
		Date to = Reader.readDate();
		List<Object> params = new ArrayList<>();
		params.add(from);
		params.add(to);
		Map<Commands, List<Object>> request = new HashMap<>();
		request.put(Commands.getCanceledOrders, null);
		Map<String, Object> responce = ClientHandler.handle(request);
		Printer.print((List<Order>) responce);

	}

}
