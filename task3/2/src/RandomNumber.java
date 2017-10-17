
public class RandomNumber {
	private int digitNumber;
	private int[]digits;
	public RandomNumber(int digitNumber)
	{
		this.digitNumber=digitNumber;
		digits=new int[digitNumber];
	}
	
	public int getNumber()
	{
		fillDigitsArray();
		StringBuilder strNumber=new StringBuilder();
		for(int i=0;i<digitNumber;i++)
		{
			strNumber.append(digits[i]);
		}
		return Integer.parseInt(strNumber.toString());
	}
	private void fillDigitsArray()
	{
		do
		{
			digits[0]=new java.util.Random().nextInt(10);
		}while(digits[0]==0);
		
		for(int i=1;i<digitNumber;i++)
		{
			digits[i]=new java.util.Random().nextInt(10);
		}
	}
	public int getDigitsSum()
	{
		int sum=0;
		for(int i=0;i<digitNumber;i++)
		{
			sum=sum+digits[i];
		}
		return sum;
	}
}
