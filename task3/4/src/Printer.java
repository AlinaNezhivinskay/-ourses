
public class Printer {
	public static void printMessage(String message)
	{
		System.out.println(message);
	}
	public static void print(double number)
	{
		System.out.println(number);
	}
	public static void printDish(Dish dish)
	{
		System.out.println(dish);
	}
	public static void printOrder(Order order)
	{
		Dish[] dishs=order.getOrderedDishs();
		for(int i=0;i<dishs.length;i++)
		{
			System.out.println(dishs[i]);
		}
		System.out.println();
	}
	public static void printCook(Cook cook)
	{
		if(cook==null)System.out.println("null");
		else
		{
		System.out.println(cook.getId()+" "+cook.getName());
		}
	}
	public static void printCookEmployement(Cook[]cooks)
	{
		System.out.println("CookEmployement");
		for(int i=0;i<cooks.length;i++)
		{
			if(cooks[i]==null)
			{
				System.out.println("null");
				continue;
			}
			System.out.println(cooks[i].getName()+" "+cooks[i].getOrder());
		}
		System.out.println();
	}
	public static void printMenu(Dish[] menu)
	{
		System.out.println("Menu");
		for(int i=0;i<menu.length;i++)
		{
			if(menu[i]==null)
			{
				System.out.println("null");
				continue;
			}
			System.out.println(menu[i].getName()+" "+menu[i].getPrice());
		}
		System.out.println();
	}
}
