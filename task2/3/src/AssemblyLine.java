
public class AssemblyLine implements IAssemblyLine {

	ILineStep caseBuilder;
	ILineStep springBuilder;
	ILineStep kernelBuilder;

	public AssemblyLine(ILineStep step1, ILineStep step2, ILineStep step3) {
		caseBuilder = step1;
		springBuilder = step2;
		kernelBuilder = step3;
	}

	public IProduct assembleProduct(IProduct product) {
		product.installFirstPart(caseBuilder.buildProductPart());
		product.installSecondPart(springBuilder.buildProductPart());
		product.installThirdPart(kernelBuilder.buildProductPart());
		return product;
	}

}
