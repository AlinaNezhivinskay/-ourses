
public class Jazz—omposition extends AMusicalComposition {
	private String jazzType;
	public Jazz—omposition(int id,String name,double duration,String jazzType)
	{
		super(id,name,duration);
		super.setGenre("Jazz");
		this.jazzType=jazzType;
	}
	public String getJazzType()
	{
		return jazzType;
	}
	public void setJazzType(String jazzType)
	{
		this.jazzType=jazzType;
	}
	public String toString()
	{
		return super.toString()+"-"+jazzType;
	}

}
