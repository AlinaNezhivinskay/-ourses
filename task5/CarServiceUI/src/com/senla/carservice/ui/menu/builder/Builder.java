package com.senla.carservice.ui.menu.builder;

import java.util.ArrayList;
import java.util.List;

import com.senla.carservice.ui.actions.Exit;
import com.senla.carservice.ui.actions.GetFreeCarServicePlaceNum;
import com.senla.carservice.ui.actions.GetFreeDate;
import com.senla.carservice.ui.actions.garage.AddGarage;
import com.senla.carservice.ui.actions.garage.GetFreeGarages;
import com.senla.carservice.ui.actions.garage.GetGarages;
import com.senla.carservice.ui.actions.garage.RemoveGarage;
import com.senla.carservice.ui.actions.garage.UpdateGarage;
import com.senla.carservice.ui.actions.master.AddMaster;
import com.senla.carservice.ui.actions.master.GetMasterByOrder;
import com.senla.carservice.ui.actions.master.GetMasters;
import com.senla.carservice.ui.actions.master.RemoveMaster;
import com.senla.carservice.ui.actions.master.SortMastersByAlphabetAscending;
import com.senla.carservice.ui.actions.master.SortMastersByAlphabetDescending;
import com.senla.carservice.ui.actions.master.SortMastersByEmployment;
import com.senla.carservice.ui.actions.master.UpdateMaster;
import com.senla.carservice.ui.actions.order.AddOrder;
import com.senla.carservice.ui.actions.order.AssignMasterToOrder;
import com.senla.carservice.ui.actions.order.CancelOrder;
import com.senla.carservice.ui.actions.order.CloseOrder;
import com.senla.carservice.ui.actions.order.GetOrderByMaster;
import com.senla.carservice.ui.actions.order.GetOrders;
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
		Menu mainMenu = new Menu();
		mainMenu.setName("Main menu");

		Menu garageMenu = new Menu();
		garageMenu.setName("Garage menu");
		List<MenuItem> garageMenuItems = new ArrayList<MenuItem>();
		garageMenuItems.add(new MenuItem("Add garage", new AddGarage(), mainMenu));
		garageMenuItems.add(new MenuItem("Remove garage", new RemoveGarage(), mainMenu));
		garageMenuItems.add(new MenuItem("Update garage", new UpdateGarage(), mainMenu));
		garageMenuItems.add(new MenuItem("Get garages", new GetGarages(), mainMenu));
		garageMenuItems.add(new MenuItem("Get free garages", new GetFreeGarages(), mainMenu));
		garageMenuItems.add(new MenuItem("Return", null, mainMenu));
		garageMenu.setMenuItems(garageMenuItems);

		Menu sortMasterMenu = new Menu();
		sortMasterMenu.setName("Sort master menu");
		List<MenuItem> sortMasterMenuItems = new ArrayList<MenuItem>();
		sortMasterMenuItems
				.add(new MenuItem("Sort by Alphabet Ascending", new SortMastersByAlphabetAscending(), mainMenu));
		sortMasterMenuItems
				.add(new MenuItem("Sort by Alphabet Descending", new SortMastersByAlphabetDescending(), mainMenu));
		sortMasterMenuItems.add(new MenuItem("Sort by Employment", new SortMastersByEmployment(), mainMenu));
		sortMasterMenu.setMenuItems(sortMasterMenuItems);

		Menu masterMenu = new Menu();
		masterMenu.setName("Master menu");
		List<MenuItem> masterMenuItems = new ArrayList<MenuItem>();
		masterMenuItems.add(new MenuItem("Add master", new AddMaster(), mainMenu));
		masterMenuItems.add(new MenuItem("Remove master", new RemoveMaster(), mainMenu));
		masterMenuItems.add(new MenuItem("Update master", new UpdateMaster(), mainMenu));
		masterMenuItems.add(new MenuItem("Get masters", new GetMasters(), mainMenu));
		masterMenuItems.add(new MenuItem("Get master by order", new GetMasterByOrder(), mainMenu));
		masterMenuItems.add(new MenuItem("Sort masters", null, sortMasterMenu));
		masterMenuItems.add(new MenuItem("Return", null, mainMenu));
		masterMenu.setMenuItems(masterMenuItems);

		Menu sortOrderMenu = new Menu();
		sortOrderMenu.setName("Sort order menu");
		List<MenuItem> sortOrderMenuItems = new ArrayList<MenuItem>();
		sortOrderMenuItems
				.add(new MenuItem("Sort orders by execution date", new SortOrdersByExecutionDate(), mainMenu));
		sortOrderMenuItems
				.add(new MenuItem("Sort orders by planned start date", new SortOrdersByPlannedStartDate(), mainMenu));
		sortOrderMenuItems
				.add(new MenuItem("Sort orders by submission date", new SortOrdersBySubmissionDate(), mainMenu));
		sortOrderMenuItems.add(new MenuItem("Sort orders by price", new SortOrdersByPrice(), mainMenu));
		sortOrderMenuItems.add(new MenuItem("Sort execution orders by execution date",
				new SortExecutingOrdersByExecutionDate(), mainMenu));
		sortOrderMenuItems.add(new MenuItem("Sort execution orders by submission date",
				new SortExecutingOrdersBySubmissionDate(), mainMenu));
		sortOrderMenuItems
				.add(new MenuItem("Sort execution orders by price", new SortExecutingOrdersByPrice(), mainMenu));
		sortOrderMenu.setMenuItems(sortOrderMenuItems);

		Menu orderMenu = new Menu();
		orderMenu.setName("Order menu");
		List<MenuItem> orderMenuItems = new ArrayList<MenuItem>();
		orderMenuItems.add(new MenuItem("Add order", new AddOrder(), mainMenu));
		orderMenuItems.add(new MenuItem("Remove order", new RemoveOrder(), mainMenu));
		orderMenuItems.add(new MenuItem("Cancel order", new CancelOrder(), mainMenu));
		orderMenuItems.add(new MenuItem("Close order", new CloseOrder(), mainMenu));
		orderMenuItems.add(new MenuItem("Update order", new UpdateOrder(), mainMenu));
		orderMenuItems.add(new MenuItem("Shift orders", new ShiftExecution(), mainMenu));
		orderMenuItems.add(new MenuItem("Get orders", new GetOrders(), mainMenu));
		orderMenuItems.add(new MenuItem("Get order by master", new GetOrderByMaster(), mainMenu));
		orderMenuItems.add(new MenuItem("Assign master to order", new AssignMasterToOrder(), mainMenu));
		orderMenuItems.add(new MenuItem("Sort orders", null, sortOrderMenu));
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
