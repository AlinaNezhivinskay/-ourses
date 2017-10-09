
public class AssemblyLine implements IAssemblyLine {

	IProductPart part1;
	IProductPart part2;
	IProductPart part3;
	
	public AssemblyLine(ILineStep step1,ILineStep step2,ILineStep step3)
	{
		part1=step1.buildProductPart();
		part2=step2.buildProductPart();
		part3=step3.buildProductPart();
	}
	
	public IProduct assembleProduct(IProduct product) {
		product.installFirstPart(part1);
		product.installSecondPart(part2);
		product.installThirdPart(part3);
		return product;
	}

}
