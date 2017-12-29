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

public class ExportGarages implements IAction {

	@SuppressWarnings("unchecked")
	@Override
	public void execute() {
		List<Object> params;
		Map<Commands, List<Object>> request = new HashMap<>();
		request.put(Commands.getGarages, null);
		Map<String, Object> responce = ClientHandler.handle(request);

		List<Garage> garagesInRep = (List<Garage>) responce.get("value");

		if (garagesInRep.size() == 0) {
			Printer.print("There is no garages!");
		} else {
			Printer.print(garagesInRep);
			Printer.print("Choose garages to export (1-end input,2-all)");
			List<Garage> garages = new ArrayList<Garage>();
			long index = Reader.readLong();
			while (index != 1 && index != 2) {

				params = new ArrayList<>();
				params.add(index);
				request = new HashMap<>();
				request.put(Commands.getGarageById, params);
				responce = ClientHandler.handle(request);

				Garage garage = (Garage) responce.get("value");
				if (garage != null) {
					garages.add(garage);
				}
				index = Reader.readLong();
			}
			if (index == 2) {
				garages = garagesInRep;
			}

			params = new ArrayList<>();
			params.add((List<Garage>) garages);
			request = new HashMap<>();
			request.put(Commands.exportGarages, params);
			ClientHandler.handle(request);
		}
	}

}
