public class Restaurant {
	private Dish[] menu;
	private Cook[] cooks;
	private Order[] orders;
	public Restaurant(int dishsNum,int cooksNum,int ordersNum)
	{
		menu=new Dish[dishsNum];
		cooks=new Cook[cooksNum];
		orders=new Order[ordersNum];
	}
	public Dish[] getMenu()
	{
		return menu;
	}
	public void setMenu(Dish[] menu)
	{
		this.menu=menu;
	}
	public Cook[] getCooks()
	{
		return cooks;
	}
	public void setCooks(Cook[] cooks)
	{
		this.cooks=cooks;
	}
	public Order[] getOrders()
	{
		return orders;
	}
	public void setOrders(Order[] orders)
	{
		this.orders=orders;
	}
}
