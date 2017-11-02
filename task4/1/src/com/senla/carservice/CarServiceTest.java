package com.senla.carservice;

import java.util.Date;

import com.senla.carservice.beans.*;
import com.senla.carservice.facade.CarService;

public class CarServiceTest {

	public static void main(String[] args) {
		CarService carService = new CarService(null, null, null);// args[0],args[1],args[2]);

		// carService.loadDate();

		Garage garage1 = new Garage();
		Garage garage2 = new Garage();
		Garage garage3 = new Garage();
		carService.addGarage(garage1);
		carService.addGarage(garage2);
		carService.addGarage(garage3);
		for (int i = 0; i < 12; i++) {
			carService.addGarage(new Garage());
		}
		carService.getGarages();

		Master master1 = new Master("Petrov Ivan");
		Master master2 = new Master("Sidorov Dima");
		carService.addMaster(master1);
		carService.addMaster(master2);
		carService.sortMastersByAlphabetAscending();

		Order order = new Order(new Date(), new Date(), new Date(), 20.5, garage1);
		carService.addOrder(order);
		Order order2 = new Order(new Date(), new Date(), new Date(), 16, garage2);
		carService.addOrder(order2);
		carService.getOrders();
		carService.sortOrdersByPrice();
		
		carService.getGarages();
		carService.cancelOrder(order2);
		carService.getOrders();
		carService.getGarages();

		carService.getMasters();
		carService.getFreeDate();
		carService.getFreeCarServicePlaceNum(new Date());
		carService.getFreeGarages();

		carService.getRemoteOrders(new Date(), new Date());

		// carService.exit();;

	}

}
