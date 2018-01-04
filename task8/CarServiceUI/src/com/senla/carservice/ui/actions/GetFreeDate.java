package com.senla.carservice.ui.actions;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.ui.handler.ClientHandler;
import com.senla.carservice.util.carservicecommands.Commands;
import com.senla.carservice.util.utils.Printer;

public class GetFreeDate implements IAction {

	@Override
	public void execute() {
		Map<Commands, List<Object>> request = new HashMap<>();
		request.put(Commands.getFreeDate, null);
		Map<String, Object> responce = ClientHandler.handle(request);
		Printer.print("Free date: " + (Date) responce.get("value"));

	}

}
