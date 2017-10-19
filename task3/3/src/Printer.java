public class Printer {
	public static void printDisk(Disk disk)
	{
		AComposition[]compositions=disk.getCompositions();
		for(int i=0;i<compositions.length;i++)
		{
			System.out.println(compositions[i]);
		}
	}
	public static void printMessage(String message)
	{
		System.out.println(message);
	}
	public static void printTotalDuration(double totalDuration)
	{
		System.out.println("Total Duration:"+totalDuration);
	}
	public static void printTotalGenre(String totalGenre)
	{
		System.out.println("Total Genre:"+totalGenre);
	}
}
