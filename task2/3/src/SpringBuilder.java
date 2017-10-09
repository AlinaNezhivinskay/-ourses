
public class SpringBuilder implements ILineStep {
	public IProductPart buildProductPart() {
		System.out.println("Build and Supply Spring");
		return new Spring();
	}

}
