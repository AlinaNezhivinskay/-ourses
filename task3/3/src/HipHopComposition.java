
public class HipHopComposition extends AMusicalComposition{
	private String hipHopType;
	public HipHopComposition(int id,String name,double duration,String hipHopType)
	{
		super(id,name,duration);
		super.setGenre("Hip-Hop");
		this.hipHopType=hipHopType;
	}
	public String getHipHopType()
	{
		return hipHopType;
	}
	public void setHipHopType(String hipHopType)
	{
		this.hipHopType=hipHopType;
	}
	public String toString()
	{
		return super.toString()+"-"+hipHopType;
	}
}
