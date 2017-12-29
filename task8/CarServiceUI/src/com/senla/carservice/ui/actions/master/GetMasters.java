package com.senla.carservice.ui.actions.master;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.senla.carservice.model.beans.Master;
import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.ui.handler.ClientHandler;
import com.senla.carservice.util.carservicecommands.Commands;
import com.senla.carservice.util.utils.Printer;

public class GetMasters implements IAction {

	@SuppressWarnings("unchecked")
	@Override
	public void execute() {
		Map<Commands, List<Object>> request = new HashMap<>();
		request.put(Commands.getMasters, null);
		Map<String, Object> responce = ClientHandler.handle(request);
		Printer.print((List<Master>) responce.get("value"));

	}

}
