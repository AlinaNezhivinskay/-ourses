
public class DiskBuilder {
	
	public static void main(String[] args) {
		Disk disk=new Disk();
		disk.addComposition(new Country—omposition(1,"Ladies Love Outlaws", 3.25,"Outlaw" ));
		disk.addComposition(new Country—omposition(2,"I Wish My Baby Was Born", 3,"Bluegrass"));
		disk.addComposition(new Jazz—omposition(3,"Birds Of Fire", 1.25,"Fusion"));
		disk.addComposition(new PopComposition(4,"Turn Me On", 3.26,"House"));
		AMusicalComposition composition1=new HipHopComposition(5,"Rapperís Delight", 2.03,"Old School");
		disk.addComposition(composition1);
		disk.addComposition(new RockComposition(6,"Down", 2.31,"Rapcor"));
		disk.printCompositions();
		System.out.println("Total Duration:"+disk.getTotalDuration());
		System.out.println("Total Genre:"+disk.getTotalGenre());
		System.out.println();
		disk.removeComposition(composition1);
		disk.printCompositions();
		System.out.println("Total Duration:"+disk.getTotalDuration());
		System.out.println("Total Genre:"+disk.getTotalGenre());
		
	}

}
