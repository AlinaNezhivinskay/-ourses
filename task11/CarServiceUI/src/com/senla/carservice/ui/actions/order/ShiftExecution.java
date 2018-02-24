package com.senla.carservice.ui.actions.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.ui.handler.ClientHandler;
import com.senla.carservice.util.carservicecommands.Commands;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.util.utils.Reader;

public class ShiftExecution implements IAction {

	@Override
	public void execute() {
		Printer.print("Input number of days");
		List<Object> params = new ArrayList<>();
		params.add(Reader.readInt());
		Map<Commands, List<Object>> request = new HashMap<>();
		request.put(Commands.shiftExecution, params);
		ClientHandler.handle(request);
	}

}
