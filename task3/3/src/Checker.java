public class Checker {
	public static boolean isEmptySpace(AComposition[]compositions)
	{
		if(compositions[compositions.length-1]==null)return true;
		return false;
	}
	public static int getFreePosition(AComposition[]compositions)
	{
		for(int i=0;i<compositions.length;i++)
		{
			if(compositions[i]==null)return i;
		}
		return -1;
	}

}
