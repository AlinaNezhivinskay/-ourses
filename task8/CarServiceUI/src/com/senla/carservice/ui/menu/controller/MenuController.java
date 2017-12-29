package com.senla.carservice.ui.menu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.senla.carservice.ui.handler.ClientHandler;
import com.senla.carservice.ui.menu.Menu;
import com.senla.carservice.ui.menu.builder.Builder;
import com.senla.carservice.ui.menu.navigator.IObserver;
import com.senla.carservice.ui.menu.navigator.Navigator;
import com.senla.carservice.util.carservicecommands.Commands;
import com.senla.carservice.util.utils.Reader;

public class MenuController implements IObserver {
	private Menu menu;

	public void run() {
		Map<Commands, List<Object>> request = new HashMap<>();
		request.put(Commands.loadData, new ArrayList<>());
		ClientHandler.handle(request);
		Builder builder = new Builder();
		builder.buildMenu();
		Navigator navigator = new Navigator(builder.getRootMenu());
		navigator.addObserver(this);
		do {
			navigator.printMenu();
			navigator.navigate(Reader.readInt());
		} while (menu != null);
	}

	@Override
	public void handle(Menu menu) {
		this.menu = menu;
	}

}
