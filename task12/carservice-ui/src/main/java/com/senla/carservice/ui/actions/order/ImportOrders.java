package com.senla.carservice.ui.actions.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.ui.handler.ClientHandler;
import com.senla.carservice.util.carservicecommands.Commands;
import com.senla.carservice.util.utils.Printer;

public class ImportOrders implements IAction {

	@Override
	public void execute() {
		Map<Commands, List<Object>> request = new HashMap<>();
		request.put(Commands.importOrders, null);
		Map<String, Object> responce = ClientHandler.handle(request);
		if ((boolean)responce.get("value")) {
			Printer.print("Import was successfull");
		} else {
			Printer.print("Import was NOT successfull");
		}

	}

}
