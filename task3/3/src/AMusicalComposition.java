
public abstract class AMusicalComposition {
	private int id;
	private String name;
	private double duration;
	private String genre;
	public AMusicalComposition(int id,String name,double duration)
	{
		this.id=id;
		this.name=name;
		this.duration=duration;
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
	public double getDuration()
	{
		return duration;
	}
	public void setDuration(double duration)
	{
		this.duration=duration;
	}
	public String getGenre()
	{
		return genre;
	}
	public void setGenre(String genre)
	{
		this.genre=genre;
	}
	public String toString()
	{
		StringBuilder str=new StringBuilder();
		return str.append(id).append(' ').append("\"").append(this.name).append("\"").append(' ').append(this.duration).append(' ').append(this.genre).toString();
	}
	public boolean equals(Object o)
	{
		return (id==((AMusicalComposition)o).getId())? true:false;
	}
	
}
