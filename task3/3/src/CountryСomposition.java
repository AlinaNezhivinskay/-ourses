
public class Country—omposition extends AMusicalComposition{
	private String countryType;
	public Country—omposition(int id,String name,double duration,String countryType)
	{
		super(id,name,duration);
		super.setGenre("Country");
		this.countryType=countryType;
	}
	public String getCountryType()
	{
		return countryType;
	}
	public void setCountryType(String countryType)
	{
		this.countryType=countryType;
	}
	public String toString()
	{
		return super.toString()+"-"+countryType;
	}
}
