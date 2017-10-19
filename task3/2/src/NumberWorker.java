
public class NumberWorker {
	public static int getSumOfDigits(int number)
	{
		int[]digits=getDigitsArrayFromNumber(number);
		int sum=0;
		for(int i=0;i<digits.length;i++)
		{
			sum+=digits[i];
		}
		return sum;
	}
	private static int[]getDigitsArrayFromNumber(int number)
	{
		int digitsCount=getDigitsCount(number);
		int[]digits=new int[digitsCount];
		for(int i=0;i<digitsCount;i++)
		{
			digits[i]=number%10;
			number=number/10;
		}
		return digits;
	}
	private static int getDigitsCount(int number)
	{
		int count=0;
		while(number!=0)
		{
			number/=10;
			count++;
		}
		return count;
	}
}
