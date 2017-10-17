
public class OrderManager {
	private Order[] orders;
	public OrderManager()
	{
		orders=new Order[1];
	}
	public void addOrder(Order order)
	{
		if(orders[0]==null)orders[0]=order;
		else
		{
			Order[] ordersCopy=orders.clone();
			orders=new Order[orders.length+1];
			for(int i=0;i<ordersCopy.length;i++)
			{
				orders[i]=ordersCopy[i];
			}
			orders[orders.length-1]=order;
		}
		
		
	}
	public void removeOrder(int tableNumber)
	{
		Order order=getOrderByTableNum(tableNumber);
		for(int i=0;i<orders.length;i++)
		{
			if(orders[i].equals(order))orders[i]=null;
		}
		Order[] ordersCopy=orders.clone();
		orders=new Order[orders.length-1];
		int j=0;
		for(int i=0;i<ordersCopy.length;i++)
		{
			if(ordersCopy[i]!=null)
			{
				orders[j]=ordersCopy[i];
				j++;
			}
		}
	}
	public Order getOrderByTableNum(int tableNumber)
	{
		for(int i=0;i<orders.length;i++)
		{
			if(orders[i].getTableNumber()==tableNumber)return orders[i];
		}
		return null;
	}
	public Order getOrderById(int id)
	{
		for(int i=0;i<orders.length;i++)
		{
			if(orders[i].getId()==id)return orders[i];
		}
		return null;
	}
}
