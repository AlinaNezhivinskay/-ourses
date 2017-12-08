package com.senla.carservice.ui.actions.garage;

import java.util.ArrayList;
import java.util.List;

import com.senla.carservice.api.facade.ICarService;
import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.util.utils.Reader;
import com.senla.carservice.view.facade.CarService;

public class ExportGarages implements IAction {

	@Override
	public void execute() {
		ICarService carService = CarService.getInstance();
		List<Garage> garagesInRep = carService.getGarages();
		if (garagesInRep.size() == 0) {
			Printer.print("There is no garages!");
		} else {
			Printer.print(garagesInRep);
			Printer.print("Choose garages to export (1-end input,2-all)");
			List<Garage> garages = new ArrayList<Garage>();
			int index;
			do {
				index = Reader.readInt();
				Garage garage = carService.getGarageById(index);
				if (garage != null) {
					garages.add(garage);
				}
			} while (index != 1 && index != 2);
			if (index == 2) {
				garages = garagesInRep;
			}
			carService.exportGarages(garages);
		}
	}

}
