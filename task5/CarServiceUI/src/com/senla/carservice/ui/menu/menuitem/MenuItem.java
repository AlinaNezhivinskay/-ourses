package com.senla.carservice.ui.menu.menuitem;

import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.ui.menu.Menu;

public class MenuItem {
	private String title;
	private IAction action;
	private Menu nextMenu;

	public MenuItem(String title, IAction action, Menu nextMenu) {
		this.title = title;
		this.action = action;
		this.nextMenu = nextMenu;
	}

	public String getTitle() {
		return title;
	}

	public IAction getAction() {
		return action;
	}

	public Menu getNextMenu() {
		return nextMenu;
	}

	public void doAction() {
		if (action != null) {
			action.execute();
		}
	}
}
