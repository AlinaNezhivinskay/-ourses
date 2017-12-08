package com.senla.carservice.ui.menu.navigator;

import java.util.ArrayList;
import java.util.List;

import com.senla.carservice.ui.menu.Menu;
import com.senla.carservice.ui.menu.menuitem.MenuItem;

public class Navigator implements IObservable{
	private final List<IObserver> observers;
	private Menu currentMenu;

	public Navigator(Menu currentMenu) {
		this.currentMenu = currentMenu;
		observers=new ArrayList<>();
	}

	public void printMenu() {
		System.out.println(currentMenu.getName());
		int i = 1;
		for (MenuItem item : currentMenu.getMenuItems()) {
			System.out.println(i++ + " " + item.getTitle());
		}
	}

	public void navigate(Integer index) {
		MenuItem menuItem = currentMenu.getMenuItems().get(index - 1);
		menuItem.doAction();
		setCurrentMenu(menuItem.getNextMenu());
	}
	
	private void setCurrentMenu(Menu menu) {
		currentMenu = menu;
		notifyObservers();
	}
	
	@Override
	public void notifyObservers() {
		for(IObserver observer:observers) {
			observer.handle(currentMenu);
		}
	}

	@Override
	public void addObserver(IObserver observer) {
		observers.add(observer);
		
	}
	
}
