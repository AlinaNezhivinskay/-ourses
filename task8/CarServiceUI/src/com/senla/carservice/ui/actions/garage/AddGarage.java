package com.senla.carservice.ui.actions.garage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.senla.carservice.ui.handler.ClientHandler;
import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.util.carservicecommands.Commands;

public class AddGarage implements IAction {

	@Override
	public void execute() {
		List<Object> params = new ArrayList<>();
		params.add(new Garage());
		Map<Commands, List<Object>> request = new HashMap<>();
		request.put(Commands.addGarage, params);
		ClientHandler.handle(request);
		Printer.print("Garage successfully added");
	}

}
