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

public class RemoveMaster implements IAction {

	@SuppressWarnings("unchecked")
	@Override
	public void execute() {
		List<Object> params;
		Map<Commands, List<Object>> request = new HashMap<>();
		request.put(Commands.getMasters, null);
		Map<String, Object> responce = ClientHandler.handle(request);

		Printer.print((List<Master>) responce.get("value"));
		Printer.print("Choose Master to remove");

		params = new ArrayList<>();
		params.add(Reader.readInt());
		request = new HashMap<>();
		request.put(Commands.getMasterById, params);
		responce = ClientHandler.handle(request);

		Master master = (Master) responce.get("value");

		params = new ArrayList<>();
		params.add(master);
		request = new HashMap<>();
		request.put(Commands.removeMaster, params);
		responce = ClientHandler.handle(request);

		if ((boolean) responce.get("value")) {
			Printer.print("Removal was successful");
		}
		Printer.print("Removal was NOT successful");

	}

}
