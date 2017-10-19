
public class Order {
	private int id;
	private Table table;
	private Dish[] orderedDishs;
	public Order(int id,Table table,Dish[] dishs)
	{
		this.id=id;
		this.table=table;
		orderedDishs=dishs;
	}
	public int getId()
	{
		return id;
	}
	public Table getTable()
	{
		return table;
	}
	public void setTable(Table table)
	{
		this.table=table;
	}
	public Dish[] getOrderedDishs()
	{
		return orderedDishs;
	}
	public void setOrderedDishs(Dish[] orderedDishs)
	{
		this.orderedDishs=orderedDishs;
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
