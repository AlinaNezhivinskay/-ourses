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

public class AddMaster implements IAction {

	@Override
	public void execute() {
		Printer.print("Input Master name");
		List<Object> params = new ArrayList<>();
		params.add(new Master(Reader.readString()));
		Map<Commands, List<Object>> request = new HashMap<>();
		request.put(Commands.addMaster, params);
		ClientHandler.handle(request);
		Printer.print("Master successfully added");
	}

}
