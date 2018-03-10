package com.senla.carservice.ui.actions.garage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.ui.handler.ClientHandler;
import com.senla.carservice.util.carservicecommands.Commands;
import com.senla.carservice.util.utils.Printer;

public class GetFreeGarages implements IAction {

	@SuppressWarnings("unchecked")
	@Override
	public void execute() {
		List<Object> params = new ArrayList<>();
		Map<Commands, List<Object>> request = new HashMap<>();
		request.put(Commands.getFreeGarages, params);
		Map<String, Object> responce = ClientHandler.handle(request);
		Printer.print((List<Garage>) responce.get("value"));
	}

}
