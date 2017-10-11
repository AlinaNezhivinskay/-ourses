
public class Pen implements IProduct {

	IProductPart penCase;
	IProductPart penSpring;
	IProductPart penKernel;
	
	public Pen() {
		System.out.println("Construct empty pen");
	}
	public void installFirstPart(IProductPart productPart) {
		System.out.println("Install First Part->"+productPart);
		penCase=productPart;
	}

	
	public void installSecondPart(IProductPart productPart) {
		System.out.println("Install Second Part->"+productPart);
		penSpring=productPart;
	}

	
	public void installThirdPart(IProductPart productPart) {
		System.out.println("Install Third Part->"+productPart);
		penKernel=productPart;
	}

}
