public class Disk {
	private AComposition[]compositions;
	public Disk(int compositionsNumber)
	{
		compositions=new AComposition[compositionsNumber];
	}
	public AComposition[] getCompositions()
	{
		return compositions;
	}
	public void setCompositions(AComposition[]compositions)
	{
		this.compositions=compositions;
	}
	public void addComposition(AComposition composition)
	{
		if(Checker.isEmptySpace(compositions))
		{
			int position=Checker.getFreePosition(compositions);
			compositions[position]=composition;
		}
		else
		{
			Printer.printMessage("There no free space");
		}
	}
	public double getTotalDuration()
	{
		double duration=0;
		for(int i=0;i<this.compositions.length;i++)
		 {
			duration+=compositions[i].getDuration();
		 }
		return duration;
	}
	public String getTotalGenre()
	{
		int[] genresCounts=getGenresCounts();
		int max=genresCounts[0];
		int maxPossition=0;
		for(int i=1;i<genresCounts.length;i++) {
			if(genresCounts[i]>max)
			{
				max=genresCounts[i];
				maxPossition=i;
			}
		}
		return getGenreNameByPosition(maxPossition);
		
	}
	private int[] getGenresCounts()
	{
		int[] genresCounts=new int[5];
		for(int i=0;i<this.compositions.length;i++)
		 {
			switch(compositions[i].getGenre()) {
		    case "Country": 
		    	genresCounts[0]++;
				break;
			case "Hip-Hop": 
				genresCounts[1]++;
				break;
			case "Jazz": 
				genresCounts[2]++;
				break;
			case "Pop":
				genresCounts[3]++;
			    break;
			case "Rock": 
				genresCounts[4]++;
			    break;
		}
		 }
		return genresCounts;
	}
	private String getGenreNameByPosition(int position)
	{
		switch(position) {
	    case 0: 
		    return "Country";
		case 1: 
		    return "Hip-Hop";
		case 2: 
		    return "Jazz";
		case 3:
		    return "Pop";
		case 4: 
			return "Rock";
		}
		return null;
	}
}
