package com.senla.carservice.ui.menu.navigator;

public interface IObservable {
	public void addObserver(IObserver observer);

	public void notifyObservers();
}
