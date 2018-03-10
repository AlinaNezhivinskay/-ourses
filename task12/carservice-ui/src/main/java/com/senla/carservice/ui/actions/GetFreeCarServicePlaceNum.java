package com.senla.carservice.ui.actions;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.ui.handler.ClientHandler;
import com.senla.carservice.util.carservicecommands.Commands;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.util.utils.Reader;

public class GetFreeCarServicePlaceNum implements IAction {

	@Override
	public void execute() {
		Printer.print("Input Order execution date (dd.MM.yyyy)");
		Date date = Reader.readDate();

		Map<Commands, List<Object>> request = new HashMap<>();
		List<Object> params = new ArrayList<>();
		params.add(date);
		request.put(Commands.getFreeCarServicePlaceNum, params);
		Map<String, Object> responce = ClientHandler.handle(request);

		Printer.print("Free Place Number=" + (int) responce.get("value"));

	}

}
