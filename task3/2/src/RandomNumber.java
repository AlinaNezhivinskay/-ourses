public class RandomNumber {
	private int digitNumber;
	public RandomNumber(int digitNumber)
	{
		this.digitNumber=digitNumber;
	}
	
	public int getNumber()
	{
		int[]digits=new int[digitNumber];
		digits=fillDigitsArray(digits);
		int number=convertDigitsArrayToNumber(digits);
		return number;
	}
	private int[] fillDigitsArray(int[]digits)
	{
		do
		{
			digits[0]=new java.util.Random().nextInt(10);
		}while(digits[0]==0);
		
		for(int i=1;i<digits.length;i++)
		{
			digits[i]=new java.util.Random().nextInt(10);
		}
		return digits;
	}
	private int convertDigitsArrayToNumber(int[]digits)
	{
		StringBuilder strNumber=new StringBuilder();
		for(int i=0;i<digitNumber;i++)
		{
			strNumber.append(digits[i]);
		}
		return Integer.parseInt(strNumber.toString());
	}
}
