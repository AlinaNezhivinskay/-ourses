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
import com.senla.carservice.util.utils.Reader;

public class RemoveGarage implements IAction {

	@SuppressWarnings("unchecked")
	@Override
	public void execute() {

		List<Object> params;
		Map<Commands, List<Object>> request = new HashMap<>();
		request.put(Commands.getGarages, null);
		Map<String, Object> responce = ClientHandler.handle(request);

		Printer.print((List<Garage>) responce.get("value"));

		Printer.print("Choose garage to remove");
		params = new ArrayList<>();
		params.add(Reader.readInt());
		request = new HashMap<>();
		request.put(Commands.getGarageById, params);
		responce = ClientHandler.handle(request);

		Garage garage = (Garage) responce.get("value");

		params = new ArrayList<>();
		params.add(garage);
		request = new HashMap<>();
		request.put(Commands.removeGarage, params);
		responce = ClientHandler.handle(request);

		if ((boolean) responce.get("value")) {
			Printer.print("Removal was successful");
		}
		Printer.print("Removal was NOT successful");

	}

}
