
public class RockComposition extends AMusicalComposition{
	private String rockType;
	public RockComposition(int id,String name,double duration,String rockType)
	{
		super(id,name,duration);
		super.setGenre("Rock");
		this.rockType=rockType;
	}
	public String getRockType()
	{
		return rockType;
	}
	public void setRockType(String rockType)
	{
		this.rockType=rockType;
	}
	public String toString()
	{
		return super.toString()+"-"+rockType;
	}
}
