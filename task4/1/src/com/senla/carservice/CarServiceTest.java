package com.senla.carservice;

import java.util.Date;

import com.senla.carservice.beans.*;
import com.senla.carservice.facade.CarService;
import com.senla.carservice.repositories.*;
import com.senla.carservice.services.*;
import com.senla.carservice.services.interfaces.*;
import com.senla.carservice.utils.Printer;

public class CarServiceTest {

	public static void main(String[] args) {
		IGarageService garageService = new GarageService(new GarageRepository());
		IMasterService masterService = new MasterService(new MasterRepository());
		IOrderService orderService = new OrderService(new OrderRepository());
		CarService carService = new CarService(garageService, masterService, orderService);

		carService.loadDate();

		Garage garage1 = new Garage();
		Garage garage2 = new Garage();
		Garage garage3 = new Garage();
		carService.addGarage(garage1);
		carService.addGarage(garage2);
		carService.addGarage(garage3);
		for (int i = 0; i < 12; i++) {
			carService.addGarage(new Garage());
		}
		Printer.print(carService.getGarages());

		Master master1 = new Master("Petrov Ivan");
		Master master2 = new Master("Sidorov Dima");
		carService.addMaster(master1);
		carService.addMaster(master2);
		Printer.print(carService.getMasters());
		carService.sortMastersByAlphabet(carService.getMasters(), false);
		Printer.print(carService.getMasters());

		Order order = new Order(new Date(), new Date(), new Date(), 20.5, garage1);
		carService.addOrder(order);
		Order order2 = new Order(new Date(), new Date(), new Date(), 16, garage2);
		carService.addOrder(order2);
		Printer.print(carService.getOrders());
		carService.sortOrdersByPrice(carService.getOrders());
		Printer.print(carService.getOrders());
		carService.cancelOrder(order2);
		Printer.print(carService.getOrders());

		Printer.print(carService.getMasters());
		Date date = carService.getFreeDate();
		Printer.print(date);
		Printer.print(carService.getFreeCarServicePlaceNum(new Date()));
		Printer.print(carService.getFreeGarages());

		carService.safeAll();

	}

}
