package com.senla.carservice.ui.menu.controller;

import com.senla.carservice.ui.menu.Menu;
import com.senla.carservice.ui.menu.builder.Builder;
import com.senla.carservice.ui.menu.navigator.IObserver;
import com.senla.carservice.ui.menu.navigator.Navigator;
import com.senla.carservice.util.utils.Reader;

public class MenuController implements IObserver {
	private Menu menu;

	public void run() {
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
