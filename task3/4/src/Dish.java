
public class Dish {
	private int id;
	private String name;
	private double price;
	public Dish(int id,String name,double price)
	{
		this.id=id;
		this.name=name;
		this.price=price;
	}
	public int getId()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	public double getPrice()
	{
		return price;
	}
	public void setPrice(double price)
	{
		this.price=price;
	}
	public boolean equals(Object o)
	{
		return (id==((Dish)o).getId())? true:false;
	}
	public String toString()
	{
		StringBuilder str=new StringBuilder();
		return str.append(id).append(" ").append(name).append(" ").append(price).toString();
	}
}
