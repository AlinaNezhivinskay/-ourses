
public class Test {

	public static void main(String[] args) {
		RandomNumber randomNumber=new RandomNumber(3);
		int number=randomNumber.getNumber();
		System.out.println(number);
		int digitsSum=NumberWorker.getSumOfDigits(number);
		System.out.println("sum="+digitsSum);

	}

}
