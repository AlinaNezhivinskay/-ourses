
public class DiskBuilder {
	
	public static void main(String[] args) {
		Disk disk=new Disk(5);
		disk.addComposition(new Country—omposition(1,"Ladies Love Outlaws", 3.25));
		Printer.printDisk(disk);
		disk.addComposition(new Country—omposition(2,"I Wish My Baby Was Born", 3));
		disk.addComposition(new Jazz—omposition(3,"Birds Of Fire", 1.25));
		disk.addComposition(new PopComposition(4,"Turn Me On", 3.26));
		disk.addComposition(new HipHopComposition(5,"Rapperís Delight", 2.03));
		Printer.printDisk(disk);
		disk.addComposition(new RockComposition(6,"Down", 2.31));
		Printer.printDisk(disk);
		Printer.printTotalDuration(disk.getTotalDuration());
		Printer.printTotalGenre(disk.getTotalGenre());
		
	}

}
