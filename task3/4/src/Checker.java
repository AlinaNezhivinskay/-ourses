public class Checker {
	public static boolean isEmptySpace(Dish[]dishes)
	{
		for(int i=0;i<dishes.length;i++)
		{
			if(dishes[i]==null)return true;
		}
		return false;
	}
	public static boolean isEmptySpace(Cook[]cooks)
	{
		for(int i=0;i<cooks.length;i++)
		{
			if(cooks[i]==null)return true;
		}
		return false;
	}
	public static boolean isEmptySpace(Order[]orders)
	{
		for(int i=0;i<orders.length;i++)
		{
			if(orders[i]==null)return true;
		}
		return false;
	}
	public static int getFreePosition(Dish[]dishes)
	{
		for(int i=0;i<dishes.length;i++)
		{
			if(dishes[i]==null)return i;
		}
		return -1;
	}
	public static int getFreePosition(Cook[]cooks)
	{
		for(int i=0;i<cooks.length;i++)
		{
			if(cooks[i]==null)return i;
		}
		return -1;
	}
	public static int getFreePosition(Order[]orders)
	{
		for(int i=0;i<orders.length;i++)
		{
			if(orders[i]==null)return i;
		}
		return -1;
	}
	public static boolean isElementOnArray(Dish[]dishs,Dish dish)
	{
		for(int i=0;i<dishs.length;i++)
		{
			if(dishs[i]==null)continue;
			if(dishs[i].equals(dish))return true;
		}
		return false;
	}
	public static boolean isElementOnArray(Cook[]cooks,Cook cook)
	{
		for(int i=0;i<cooks.length;i++)
		{
			if(cooks[i]==null)continue;
			if(cooks[i].equals(cook))return true;
		}
		return false;
	}
	public static boolean isElementOnArray(Order[]orders,Order order)
	{
		if(order!=null)
		{
		for(int i=0;i<orders.length;i++)
		{
			if(orders[i]==null)continue;
			if(orders[i].equals(order))return true;
		}
		}
		return false;
	}
	public static int getPositionOfElement(Dish[]dishs,Dish dish)
	{
		for(int i=0;i<dishs.length;i++)
		{
			if(dishs[i].equals(dish))return i;
		}
		return -1;
	}
	public static int getPositionOfElement(Cook[]cooks,Cook cook)
	{
		for(int i=0;i<cooks.length;i++)
		{
			if(cooks[i].equals(cook))return i;
		}
		return -1;
	}
	public static int getPositionOfElement(Order[]orders,Order order)
	{
		for(int i=0;i<orders.length;i++)
		{
			if(orders[i].equals(order))return i;
		}
		return -1;
	}
	public static boolean isOrderOfTable(Order[]orders,Table table)
	{
		for(int i=0;i<orders.length;i++)
		{
			if(orders[i]==null)continue;
			if(orders[i].getTable().equals(table))return true;
		}
		return false;
	}
	public static int getPositionOfOrderByTable(Order[]orders,Table table)
	{
		for(int i=0;i<orders.length;i++)
		{
			if(orders[i]==null)continue;
			if(orders[i].getTable().equals(table))return i;
		}
		return -1;
	}
	public static boolean isCookOfOrder(Cook[] cooks,Order order)
	{
		for(int i=0;i<cooks.length;i++)
		{
			if(cooks[i]==null || cooks[i].getOrder()==null)continue;
			if(cooks[i].getOrder().equals(order))return true;
		}
		return false;
	}
	public static int getPositionOfCookByOrder(Cook[] cooks,Order order)
	{
		for(int i=0;i<cooks.length;i++)
		{
			if(cooks[i]==null)continue;
			if(cooks[i].getOrder().equals(order))return i;
		}
		return -1;
	}
}
