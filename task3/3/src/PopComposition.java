
public class PopComposition extends AMusicalComposition {
	private String popType;
	public PopComposition(int id,String name,double duration,String popType)
	{
		super(id,name,duration);
		super.setGenre("Pop");
		this.popType=popType;
	}
	public String getPopType()
	{
		return popType;
	}
	public void setPopType(String popType)
	{
		this.popType=popType;
	}
	public String toString()
	{
		return super.toString()+"-"+popType;
	}

}
