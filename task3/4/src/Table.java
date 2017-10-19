
public class Table {
	private int number;
	private boolean occupancy;
	public Table(int number)
	{
		this.number=number;
		occupancy=false;
	}
	public int getNumber()
	{
		return number;
	}
	public void setNumber(int number)
	{
		this.number=number;
	}
	public boolean getOccupancy()
	{
		return occupancy;
	}
	public void setOccupancy(boolean occupancy)
	{
		this.occupancy=occupancy;
	}
	public boolean equals(Object o)
	{
		return (number==((Table)o).getNumber())? true:false;
	}
	public String toString()
	{
		return Integer.toString(number);
	}
}
