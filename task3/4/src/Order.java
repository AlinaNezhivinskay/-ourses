
public class Order {
	private int id;
	private int tableNumber;
	private Dish[] orderedDishs;
	public Order(int id,int tableNumber,Menu menu,int[]dishesIdFromMenu)
	{
		this.id=id;
		this.tableNumber=tableNumber;
		orderedDishs=new Dish[dishesIdFromMenu.length];
		for(int i=0;i<orderedDishs.length;i++)
		{
			orderedDishs[i]=menu.getDishById(dishesIdFromMenu[i]);
		}
	}
	public int getId()
	{
		return id;
	}
	public int getTableNumber()
	{
		return tableNumber;
	}
	public void setName(int tableNumber)
	{
		this.tableNumber=tableNumber;
	}
	public Dish[] getOrderedDishs()
	{
		return orderedDishs;
	}
	public void setPrice(Dish[] orderedDishs)
	{
		this.orderedDishs=orderedDishs;
	}
	public void printCheck()
	{
		System.out.println("Check");
		double sum=0;
		for(int i=0;i<orderedDishs.length;i++)
		{
			System.out.println(orderedDishs[i]);
			sum+=orderedDishs[i].getPrice();
		}
		System.out.println("Total sum="+sum);
	}
	public boolean equals(Object o)
	{
		return (id==((Order)o).getId())? true:false;
	}
	public String toString()
	{
		return Integer.toString(id);
	}
}
