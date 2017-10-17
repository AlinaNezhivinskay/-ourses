public class Disk {
	private AMusicalComposition[]compositions;
	public Disk()
	{
		compositions=new AMusicalComposition[1];
	}
	public AMusicalComposition[] getCompositions()
	{
		return compositions;
	}
	public void setCompositions(AMusicalComposition[]compositions)
	{
		this.compositions=compositions;
	}
	public void addComposition(AMusicalComposition composition)
	{
		if(compositions[0]==null)compositions[0]=composition;
		else
		{
		 AMusicalComposition[]compositions=new AMusicalComposition[this.compositions.length+1];
		 System.arraycopy(this.compositions, 0, compositions, 0,this.compositions.length );
		 //compositions=this.compositions.clone();
		 compositions[this.compositions.length]=composition;
		 this.compositions=compositions;
		}
	}
	public void removeComposition(AMusicalComposition composition)
	{
		for(int i=0;i<compositions.length;i++)
		{
			if(compositions[i].equals(composition))compositions[i]=null;
		}
		AMusicalComposition[] compositionsCopy=compositions.clone();
		compositions=new AMusicalComposition[compositions.length-1];
		int j=0;
		for(int i=0;i<compositionsCopy.length;i++)
		{
			if(compositionsCopy[i]!=null)
			{
				compositions[j]=compositionsCopy[i];
				j++;
			}
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
		int[] genres=new int[5];
		for(int i=0;i<this.compositions.length;i++)
		 {
			switch(compositions[i].getGenre()) {
		    case "Country": 
			    genres[0]++;
				break;
			case "Hip-Hop": 
			    genres[1]++;
				break;
			case "Jazz": 
			    genres[2]++;
				break;
			case "Pop":
			    genres[3]++;
			    break;
			case "Rock": 
				genres[4]++;
			    break;
		}
		 }
		int max=genres[0];
		int maxPossition=0;
		for(int i=1;i<genres.length;i++) {
			if(genres[i]>max)
			{
				max=genres[i];
				maxPossition=i;
			}
		}
		switch(maxPossition) {
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
	public void printCompositions()
	{
		for(int i=0;i<compositions.length;i++)
		{
			System.out.println(compositions[i]);
		}
	}
}
