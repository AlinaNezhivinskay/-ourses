
public class Cook {
	private int id;
	private String name;
	private Order order;
	public Cook(int id,String name)
	{
		this.id=id;
		this.name=name;
		order=null;
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
	public boolean isCookFree()
	{
		if(order==null)return true;
		return false;
	}
	public Order getOrder()
	{
		return order;
	}
	public void setOrder(Order order)
	{
		this.order=order;
	}
	public boolean equals(Object o)
	{
		return (id==((Cook)o).getId())? true:false;
	}
	public String toString()
	{
		StringBuilder str=new StringBuilder();
		return str.append(id).append(" ").append(name).toString();
	}
}
