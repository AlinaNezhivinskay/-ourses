
public class CookManager {
	private Cook[] cooks;
	public CookManager()
	{
		cooks=new Cook[1];
	}
	public void addCook(Cook cook)
	{
		if(cooks[0]==null)cooks[0]=cook;
		else
		{
			Cook[] cooksCopy=cooks.clone();
			cooks=new Cook[cooks.length+1];
			for(int i=0;i<cooksCopy.length;i++)
			{
				cooks[i]=cooksCopy[i];
			}
			cooks[cooks.length-1]=cook;
		}
	}
	public void removeCook(Cook cook)
	{
		for(int i=0;i<cooks.length;i++)
		{
			if(cooks[i].equals(cook))cooks[i]=null;
		}
		Cook[] cooksCopy=cooks.clone();
		cooks=new Cook[cooks.length-1];
		int j=0;
		for(int i=0;i<cooksCopy.length;i++)
		{
			if(cooksCopy[i]!=null)
			{
				cooks[j]=cooksCopy[i];
				j++;
			}
		}
	}
	public void assignOrderToCook(Order order)
	{
		if(getFreeCook()!=null)getFreeCook().setOrder(order);
		getFreeCook().setState(true);
	}
	private Cook getFreeCook()
	{
		for(int i=0;i<cooks.length;i++)
		{
			if(cooks[i].getState()==false)return cooks[i];
		}
		return null;
	}
	public void printCookEmployement()
	{
		System.out.println("CookEmployement");
		for(int i=0;i<cooks.length;i++)
		{
			System.out.println(cooks[i].getName()+" "+cooks[i].getOrder());
		}
	}
	public Cook getCookByOrder(OrderManager orderManager,int orderId)
	{
		Order order=orderManager.getOrderById(orderId);
		for(int i=0;i<cooks.length;i++)
		{
			if(cooks[i].getOrder().equals(order))return cooks[i];
		}
		return null;
	}
	public Cook getCookByTable(OrderManager orderManager,int tableNumber)
	{
		Order order=orderManager.getOrderByTableNum(tableNumber);
		for(int i=0;i<cooks.length;i++)
		{
			if(cooks[i].getOrder().equals(order))return cooks[i];
		}
		return null;
	}
}
