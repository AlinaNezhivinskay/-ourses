
public class KernelBuilder implements ILineStep{

	public IProductPart buildProductPart() {
		System.out.println("Build and Supply Kernel");
		return new Kernel();
	}

}
