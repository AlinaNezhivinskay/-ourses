package com.senla.carservice.ui.actions.master;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.senla.carservice.model.beans.Master;
import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.ui.handler.ClientHandler;
import com.senla.carservice.util.carservicecommands.Commands;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.util.utils.Reader;

public class ExportMasters implements IAction {

	@SuppressWarnings("unchecked")
	@Override
	public void execute() {
		List<Object> params;
		Map<Commands, List<Object>> request = new HashMap<>();
		request.put(Commands.getMasters, null);
		Map<String, Object> responce = ClientHandler.handle(request);

		List<Master> mastersInRep = (List<Master>) responce.get("value");
		if (mastersInRep.size() == 0) {
			Printer.print("There is no masters!");
		} else {
			Printer.print(mastersInRep);
			Printer.print("Choose masters to export (1-end input,2-all)");
			List<Master> masters = new ArrayList<Master>();
			int index;
			do {
				index = Reader.readInt();

				params = new ArrayList<>();
				params.add(index);
				request = new HashMap<>();
				request.put(Commands.getMasterById, params);
				responce = ClientHandler.handle(request);

				Master master = (Master) responce.get("value");
				if (master != null) {
					masters.add(master);
				}
			} while (index != 1 && index != 2);
			if (index == 2) {
				request = new HashMap<>();
				request.put(Commands.getMasters, null);
				responce = ClientHandler.handle(request);
				masters = (List<Master>) responce.get("value");
			}
			params = new ArrayList<>();
			params.add(masters);
			request = new HashMap<>();
			request.put(Commands.exportMasters, params);
			ClientHandler.handle(request);
		}

	}

}
