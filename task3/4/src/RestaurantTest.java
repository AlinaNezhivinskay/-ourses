
public class RestaurantTest {

	public static void main(String[] args) {
		Menu menu=new Menu();
		Dish dish=new Dish(1,"Soup",15.3);
		menu.addDish(dish);
		menu.addDish(new Dish(2,"Cutlet",25));
		menu.addDish(new Dish(3,"Buckwheat",10.5));
		menu.addDish(new Dish(4,"Pasta",17));
		menu.addDish(new Dish(5,"Juice",5.2));
		menu.printMenu();
		System.out.println();
		menu.removeDish(dish);
		menu.printMenu();
		
		CookManager cookManager=new CookManager();
		cookManager.addCook(new Cook(1,"Petrov"));
		cookManager.printCookEmployement();
		System.out.println();
		Cook cook=new Cook(2,"Dian");
		cookManager.addCook(cook);
		System.out.println();
		Cook cook2=new Cook(3,"Fiat");
		cookManager.addCook(cook2);
		cookManager.printCookEmployement();
		System.out.println();
		cookManager.removeCook(cook);
		cookManager.printCookEmployement();
		System.out.println();
		
		OrderManager orderManager=new OrderManager();
		Order order1=new Order(1,1,menu,new int[]{2,5});
		order1.printCheck();
		orderManager.addOrder(order1);
		Order order2=new Order(2,3,menu,new int[] {1});
		orderManager.addOrder(order2);
		Order order3=new Order(3,7,menu,new int[] {2,4});
		orderManager.addOrder(order3);
		
		cookManager.assignOrderToCook(order2);
		cookManager.assignOrderToCook(order1);
		cookManager.printCookEmployement();
		
		orderManager.getOrderByTableNum(7).printCheck();
		
		System.out.println(cookManager.getCookByOrder(orderManager, 1));
		System.out.println(cookManager.getCookByTable(orderManager, 3));
	}

}
