
public class Pen implements IProduct {

	public Pen() {
		System.out.println("Constructor of Pen");
	}
	public void installFirstPart(IProductPart productPart) {
		System.out.println("Install First Part->"+productPart);
		
	}

	
	public void installSecondPart(IProductPart productPart) {
		System.out.println("Install Second Part->"+productPart);
		
	}

	
	public void installThirdPart(IProductPart productPart) {
		System.out.println("Install Third Part->"+productPart);
		
	}

}
