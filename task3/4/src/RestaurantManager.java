public class RestaurantManager {
	private Restaurant restaurant;
	private final String NON_FREE_SPACE_MESSAGE="There no free space";
	public RestaurantManager(Restaurant restaurant) 
	{
		this.restaurant=restaurant;
	}
	public Restaurant getRestaurant()
	{
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant)
	{
		this.restaurant=restaurant;
	}
	
	public void addDish(Dish dish)
	{
		if(Checker.isEmptySpace(restaurant.getMenu()))
		{
			int position=Checker.getFreePosition(restaurant.getMenu());
			restaurant.getMenu()[position]=dish;
		}
		else
		{
			Printer.printMessage(NON_FREE_SPACE_MESSAGE);
		}
	}
	public void removeDish(Dish dish)
	{
		if(Checker.isElementOnArray(restaurant.getMenu(), dish))
		{
			int position=Checker.getPositionOfElement(restaurant.getMenu(), dish);
			restaurant.getMenu()[position]=null;
		}
		else
		{
			Printer.printMessage("There isn't this dish in restaurant");
		}
	}
	public void addCook(Cook cook)
	{
		if(Checker.isEmptySpace(restaurant.getCooks()))
		{
			int position=Checker.getFreePosition(restaurant.getCooks());
			restaurant.getCooks()[position]=cook;
		}
		else
		{
			Printer.printMessage(NON_FREE_SPACE_MESSAGE);
		}
	}
	public void removeCook(Cook cook)
	{
		if(Checker.isElementOnArray(restaurant.getCooks(), cook))
		{
			int position=Checker.getPositionOfElement(restaurant.getCooks(), cook);
			restaurant.getMenu()[position]=null;
		}
		else
		{
			Printer.printMessage("There isn't this cook in restaurant");
		}
	}
	public void addOrder(Order order)
	{
		if(Checker.isEmptySpace(restaurant.getOrders()))
		{
			int position=Checker.getFreePosition(restaurant.getOrders());
			restaurant.getOrders()[position]=order;
		}
		else
		{
			Printer.printMessage(NON_FREE_SPACE_MESSAGE);
		}
	}
	public void removeOrder(Order order)
	{
		if(Checker.isElementOnArray(restaurant.getOrders(), order))
		{
			int position=Checker.getPositionOfElement(restaurant.getOrders(), order);
			restaurant.getMenu()[position]=null;
		}
		else
		{
			Printer.printMessage("There isn't this order in restaurant");
		}
	}
	public void assignOrderToCook(Order order)
	{
		if(getFreeCook()!=null)getFreeCook().setOrder(order);
		else
		{
			Printer.printMessage("There isn't free cook");
		}
	}
	private Cook getFreeCook()
	{
		for(int i=0;i<restaurant.getCooks().length;i++)
		{
			if(restaurant.getCooks()[i].isCookFree()==true)return restaurant.getCooks()[i];
		}
		return null;
	}
	public double getOrderTotalPrice(Order order)
	{
		Dish[] dishs=order.getOrderedDishs();
		double sum=0;
		for(int i=0;i<dishs.length;i++)
		{
			sum+=dishs[i].getPrice();
		}
		return sum;
	}
	public Cook getCookByOrder(Order order)
	{
		if(Checker.isElementOnArray(restaurant.getOrders(), order))
		{
			if(Checker.isCookOfOrder(restaurant.getCooks(), order))
			{
				return restaurant.getCooks()[Checker.getPositionOfCookByOrder(restaurant.getCooks(), order)];
			}
			else
			{
				Printer.printMessage("There is no Cook who make this Order");
			}
		}
		else
		{
			Printer.printMessage("There is no such Order");
		}
		return null;
	}
	private Order getOrderByTable(Table table)
	{
		if(Checker.isOrderOfTable(restaurant.getOrders(), table))
		{
			return restaurant.getOrders()[Checker.getPositionOfOrderByTable(restaurant.getOrders(), table)];
		}
		else
		{
			Printer.printMessage("There is no Order on this table");
		}
		return null;
	}
	public Cook getCookByTable(Table table)
	{
		Order order=getOrderByTable(table);
		return getCookByOrder(order);
	}
	
}
