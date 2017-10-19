
public class RestaurantTest {

	public static void main(String[] args) {
		Restaurant restaurant=new Restaurant(5, 5, 5);
		RestaurantManager manager=new RestaurantManager(restaurant);
		Dish dish1=new Dish(1,"Soup",15.5);
		manager.addDish(dish1);
		Dish dish2=new Dish(2,"Cutlet",25);
		manager.addDish(dish2);
		Dish dish3=new Dish(3,"Buckwheat",10.5);
		manager.addDish(dish3);
		Dish dish4=new Dish(4,"Pasta",17);
		manager.addDish(dish4);
		Dish dish5=new Dish(5,"Juice",5.2);
		manager.addDish(dish5);
		
		Printer.printMenu(restaurant.getMenu());
		manager.removeDish(dish3);
		Printer.printMenu(restaurant.getMenu());
		manager.addDish(dish3);
		Printer.printMenu(restaurant.getMenu());
		
		Cook cook1=new Cook(1,"Petrov");
		manager.addCook(cook1);
		Printer.printCookEmployement(restaurant.getCooks());
		Cook cook2=new Cook(2,"Dian");
		manager.addCook(cook2);
		Cook cook3=new Cook(3,"Fiat");
		manager.addCook(cook3);
		Printer.printCookEmployement(restaurant.getCooks());
		manager.removeCook(cook2);
		Printer.printCookEmployement(restaurant.getCooks());
		
		Order order1=new Order(1,new Table(1),new Dish[]{dish2,dish4});
		Printer.printOrder(order1);
		manager.addOrder(order1);
		Order order2=new Order(2,new Table(5),new Dish[]{dish1});
		manager.addOrder(order2);
		Table table7=new Table(7);
		Order order3=new Order(3,table7,new Dish[] {dish1,dish5});
		manager.addOrder(order3);
		
		manager.assignOrderToCook(order2);
		manager.assignOrderToCook(order1);
		manager.assignOrderToCook(order3);
		Printer.printCookEmployement(restaurant.getCooks());
		
		Printer.print((manager.getOrderTotalPrice(order3)));
		
		Printer.printCook(manager.getCookByOrder(order1));
		Printer.printCook(manager.getCookByTable(table7));
	}

}
