
public class Menu {
	private Dish[] dishs;
	
	public Menu()
	{
		dishs=new Dish[1];
	}
	public Dish[] getDishs()
	{
		return dishs;
	}
	public void addDish(Dish dish)
	{
		if(dishs[0]==null)dishs[0]=dish;
		else
		{
			Dish[] dishsCopy=dishs.clone();
			dishs=new Dish[dishs.length+1];
			for(int i=0;i<dishsCopy.length;i++)
			{
				dishs[i]=dishsCopy[i];
			}
			dishs[dishs.length-1]=dish;
		}
	}
	public void removeDish(Dish dish)
	{
		for(int i=0;i<dishs.length;i++)
		{
			if(dishs[i].equals(dish))dishs[i]=null;
		}
		Dish[] dishsCopy=dishs.clone();
		dishs=new Dish[dishs.length-1];
		int j=0;
		for(int i=0;i<dishsCopy.length;i++)
		{
			if(dishsCopy[i]!=null)
			{
				dishs[j]=dishsCopy[i];
				j++;
			}
		}
	}
	public void printMenu()
	{
		System.out.println("Menu");
		for(int i=0;i<dishs.length;i++)
		{
			System.out.println(dishs[i]);
		}
	}
	public Dish getDishById(int id)
	{
		for(int i=0;i<dishs.length;i++)
		{
			if(dishs[i].getId()==id)return dishs[i];
		}
		return null;
	}
}
