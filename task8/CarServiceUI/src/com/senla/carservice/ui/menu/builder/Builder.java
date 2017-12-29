package com.senla.carservice.ui.menu.builder;

import java.util.ArrayList;
import java.util.List;

import com.senla.carservice.configuration.properties.PropertyRepository;
import com.senla.carservice.ui.actions.Exit;
import com.senla.carservice.ui.actions.GetFreeCarServicePlaceNum;
import com.senla.carservice.ui.actions.GetFreeDate;
import com.senla.carservice.ui.actions.garage.AddGarage;
import com.senla.carservice.ui.actions.garage.ExportGarages;
import com.senla.carservice.ui.actions.garage.GetFreeGarages;
import com.senla.carservice.ui.actions.garage.GetGarages;
import com.senla.carservice.ui.actions.garage.ImportGarage;
import com.senla.carservice.ui.actions.garage.RemoveGarage;
import com.senla.carservice.ui.actions.master.AddMaster;
import com.senla.carservice.ui.actions.master.ExportMasters;
import com.senla.carservice.ui.actions.master.GetMasterByOrder;
import com.senla.carservice.ui.actions.master.GetMasters;
import com.senla.carservice.ui.actions.master.ImportMasters;
import com.senla.carservice.ui.actions.master.RemoveMaster;
import com.senla.carservice.ui.actions.master.SortMastersByAlphabetAscending;
import com.senla.carservice.ui.actions.master.SortMastersByAlphabetDescending;
import com.senla.carservice.ui.actions.master.SortMastersByEmployment;
import com.senla.carservice.ui.actions.order.AddOrder;
import com.senla.carservice.ui.actions.order.AssignMasterToOrder;
import com.senla.carservice.ui.actions.order.CancelOrder;
import com.senla.carservice.ui.actions.order.CloseOrder;
import com.senla.carservice.ui.actions.order.ExportOrders;
import com.senla.carservice.ui.actions.order.GetCopy;
import com.senla.carservice.ui.actions.order.GetOrderByMaster;
import com.senla.carservice.ui.actions.order.GetOrders;
import com.senla.carservice.ui.actions.order.ImportOrders;
import com.senla.carservice.ui.actions.order.RemoveOrder;
import com.senla.carservice.ui.actions.order.ShiftExecution;
import com.senla.carservice.ui.actions.order.SortExecutingOrdersByExecutionDate;
import com.senla.carservice.ui.actions.order.SortExecutingOrdersByPrice;
import com.senla.carservice.ui.actions.order.SortExecutingOrdersBySubmissionDate;
import com.senla.carservice.ui.actions.order.SortOrdersByExecutionDate;
import com.senla.carservice.ui.actions.order.SortOrdersByPlannedStartDate;
import com.senla.carservice.ui.actions.order.SortOrdersByPrice;
import com.senla.carservice.ui.actions.order.SortOrdersBySubmissionDate;
import com.senla.carservice.ui.actions.order.UpdateOrder;
import com.senla.carservice.ui.menu.Menu;
import com.senla.carservice.ui.menu.menuitem.MenuItem;

public class Builder {
	private Menu rootMenu;

	public Menu getRootMenu() {
		return rootMenu;
	}

	public void buildMenu() {
		PropertyRepository properties = PropertyRepository.getInstance();
		Menu mainMenu = new Menu();
		mainMenu.setName("Main menu");

		Menu garageMenu = new Menu();
		garageMenu.setName("Garage menu");
		List<MenuItem> garageMenuItems = new ArrayList<MenuItem>();
		if (Boolean.parseBoolean(properties.getProperty("addGaragePossibility"))) {
			garageMenuItems.add(new MenuItem("Add garage", new AddGarage(), garageMenu));
		}
		if (Boolean.parseBoolean(properties.getProperty("removeGaragePossibility"))) {
			garageMenuItems.add(new MenuItem("Remove garage", new RemoveGarage(), garageMenu));
		}
		garageMenuItems.add(new MenuItem("Get garages", new GetGarages(), garageMenu));
		garageMenuItems.add(new MenuItem("Get free garages", new GetFreeGarages(), garageMenu));
		garageMenuItems.add(new MenuItem("Export", new ExportGarages(), garageMenu));
		garageMenuItems.add(new MenuItem("Import", new ImportGarage(), garageMenu));
		garageMenuItems.add(new MenuItem("Return", null, mainMenu));
		garageMenu.setMenuItems(garageMenuItems);

		Menu masterMenu = new Menu();

		Menu sortMasterMenu = new Menu();
		sortMasterMenu.setName("Sort master menu");
		List<MenuItem> sortMasterMenuItems = new ArrayList<MenuItem>();
		sortMasterMenuItems
				.add(new MenuItem("Sort by Alphabet Ascending", new SortMastersByAlphabetAscending(), masterMenu));
		sortMasterMenuItems
				.add(new MenuItem("Sort by Alphabet Descending", new SortMastersByAlphabetDescending(), masterMenu));
		sortMasterMenuItems.add(new MenuItem("Sort by Employment", new SortMastersByEmployment(), masterMenu));
		sortMasterMenuItems.add(new MenuItem("Return", null, masterMenu));
		sortMasterMenu.setMenuItems(sortMasterMenuItems);

		masterMenu.setName("Master menu");
		List<MenuItem> masterMenuItems = new ArrayList<MenuItem>();
		masterMenuItems.add(new MenuItem("Add master", new AddMaster(), masterMenu));
		masterMenuItems.add(new MenuItem("Remove master", new RemoveMaster(), masterMenu));
		masterMenuItems.add(new MenuItem("Get masters", new GetMasters(), masterMenu));
		masterMenuItems.add(new MenuItem("Get master by order", new GetMasterByOrder(), masterMenu));
		masterMenuItems.add(new MenuItem("Sort masters", null, sortMasterMenu));
		masterMenuItems.add(new MenuItem("Export", new ExportMasters(), masterMenu));
		masterMenuItems.add(new MenuItem("Import", new ImportMasters(), masterMenu));
		masterMenuItems.add(new MenuItem("Return", null, mainMenu));
		masterMenu.setMenuItems(masterMenuItems);

		Menu orderMenu = new Menu();

		Menu sortOrderMenu = new Menu();
		sortOrderMenu.setName("Sort order menu");
		List<MenuItem> sortOrderMenuItems = new ArrayList<MenuItem>();
		sortOrderMenuItems
				.add(new MenuItem("Sort orders by execution date", new SortOrdersByExecutionDate(), orderMenu));
		sortOrderMenuItems
				.add(new MenuItem("Sort orders by planned start date", new SortOrdersByPlannedStartDate(), orderMenu));
		sortOrderMenuItems
				.add(new MenuItem("Sort orders by submission date", new SortOrdersBySubmissionDate(), orderMenu));
		sortOrderMenuItems.add(new MenuItem("Sort orders by price", new SortOrdersByPrice(), orderMenu));
		sortOrderMenuItems.add(new MenuItem("Sort execution orders by execution date",
				new SortExecutingOrdersByExecutionDate(), orderMenu));
		sortOrderMenuItems.add(new MenuItem("Sort execution orders by submission date",
				new SortExecutingOrdersBySubmissionDate(), orderMenu));
		sortOrderMenuItems
				.add(new MenuItem("Sort execution orders by price", new SortExecutingOrdersByPrice(), orderMenu));
		sortOrderMenuItems.add(new MenuItem("Return", null, orderMenu));
		sortOrderMenu.setMenuItems(sortOrderMenuItems);

		orderMenu.setName("Order menu");
		List<MenuItem> orderMenuItems = new ArrayList<MenuItem>();
		orderMenuItems.add(new MenuItem("Add order", new AddOrder(), orderMenu));
		if (Boolean.parseBoolean(properties.getProperty("removeOrderPossibility"))) {
			orderMenuItems.add(new MenuItem("Remove order", new RemoveOrder(), orderMenu));
		}
		orderMenuItems.add(new MenuItem("Cancel order", new CancelOrder(), orderMenu));
		orderMenuItems.add(new MenuItem("Close order", new CloseOrder(), orderMenu));
		if (Boolean.parseBoolean(properties.getProperty("shiftOrderPossibility"))) {
			orderMenuItems.add(new MenuItem("Shift orders", new ShiftExecution(), orderMenu));
		}
		orderMenuItems.add(new MenuItem("Get order copy", new GetCopy(), orderMenu));
		orderMenuItems.add(new MenuItem("Get orders", new GetOrders(), orderMenu));
		orderMenuItems.add(new MenuItem("Get order by master", new GetOrderByMaster(), orderMenu));
		orderMenuItems.add(new MenuItem("Change order", new UpdateOrder(), orderMenu));
		orderMenuItems.add(new MenuItem("Assign master to order", new AssignMasterToOrder(), orderMenu));
		orderMenuItems.add(new MenuItem("Sort orders", null, sortOrderMenu));
		orderMenuItems.add(new MenuItem("Export", new ExportOrders(), orderMenu));
		orderMenuItems.add(new MenuItem("Import", new ImportOrders(), orderMenu));
		orderMenuItems.add(new MenuItem("Return", null, mainMenu));
		orderMenu.setMenuItems(orderMenuItems);

		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		menuItems.add(new MenuItem("Garages", null, garageMenu));
		menuItems.add(new MenuItem("Masters", null, masterMenu));
		menuItems.add(new MenuItem("Orders", null, orderMenu));
		menuItems.add(new MenuItem("Get number of free places", new GetFreeCarServicePlaceNum(), mainMenu));
		menuItems.add(new MenuItem("Get free Date", new GetFreeDate(), mainMenu));
		menuItems.add(new MenuItem("Exit", new Exit(), null));
		mainMenu.setMenuItems(menuItems);

		rootMenu = mainMenu;
	}

}
