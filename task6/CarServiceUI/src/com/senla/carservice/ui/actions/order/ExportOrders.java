package com.senla.carservice.ui.actions.order;

import java.util.ArrayList;
import java.util.List;

import com.senla.carservice.model.beans.Order;
import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.util.utils.Reader;
import com.senla.carservice.view.facade.CarService;

public class ExportOrders implements IAction {

	@Override
	public void execute() {
		CarService carService = CarService.getInstance();
		Printer.print("Input File Path");
		String filePath = Reader.readFilePath();
		List<Order> ordersInRep = carService.getOrders();
		if (ordersInRep.size() == 0) {
			Printer.print("There is no orders!");
		} else {

			Printer.print(ordersInRep);
			Printer.print("Choose orders to export (1-end input,2-all)");
			List<Order> orders = new ArrayList<Order>();
			int index;
			do {
				index = Reader.readInt();
				Order order = carService.getOrderById(index);
				if (order != null) {
					orders.add(order);
				}
			} while (index != 1 && index != 2);
			if (index == 2) {
				orders = carService.getOrders();
			}
			carService.exportOrders(filePath, orders);
		}

	}

}
