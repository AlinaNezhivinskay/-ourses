package com.senla.carservice.ui.actions.master;

import java.util.ArrayList;
import java.util.List;

import com.senla.carservice.model.beans.Master;
import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.util.utils.Reader;
import com.senla.carservice.view.facade.CarService;

public class ExportMasters implements IAction {

	@Override
	public void execute() {
		CarService carService = CarService.getInstance();
		Printer.print("Input File Path");
		String filePath = Reader.readFilePath();
		List<Master> mastersInRep = carService.getMasters();
		if (mastersInRep.size() == 0) {
			Printer.print("There is no masters!");
		} else {
			Printer.print(mastersInRep);
			Printer.print("Choose masters to export (1-end input,2-all)");
			List<Master> masters = new ArrayList<Master>();
			int index;
			do {
				index = Reader.readInt();
				Master master = carService.getMasterById(index);
				if (master != null) {
					masters.add(master);
				}
			} while (index != 1 && index != 2);
			if (index == 2) {
				masters = carService.getMasters();
			}
			carService.exportMasters(filePath, masters);
		}

	}

}
