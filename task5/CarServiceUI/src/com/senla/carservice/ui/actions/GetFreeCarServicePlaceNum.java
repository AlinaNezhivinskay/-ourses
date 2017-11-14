package com.senla.carservice.ui.actions;

import java.util.Date;

import com.senla.carservice.ui.actions.api.IAction;
import com.senla.carservice.util.utils.Printer;
import com.senla.carservice.util.utils.Reader;
import com.senla.carservice.view.facade.CarService;

public class GetFreeCarServicePlaceNum implements IAction {

	@Override
	public void execute() {
		Printer.print("Input Order execution date (dd.MM.yyyy)");
		Date date = Reader.readDate();
		Printer.print("Free Place Number=" + CarService.getInstance().getFreeCarServicePlaceNum(date));

	}

}
