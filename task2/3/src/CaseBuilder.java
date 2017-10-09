
public class CaseBuilder implements ILineStep {
	public IProductPart buildProductPart()
	{
		System.out.println("Build and Supply Case");
		return new Case();
	}

}
