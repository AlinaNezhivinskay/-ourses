
public class WordsTransformer {
	public String transformWordsToString(String [] words)
	{
		StringBuilder str=new StringBuilder();
		for(int i=0;i<words.length;i++)
		{
			 str.append(setFirstLetterCapital(convertStringToCharacters(words[i]))).append(' ');
		}
		return str.toString();
	}
	private char[] convertStringToCharacters(String word)
	{
		return word.toCharArray();
	}
	private String setFirstLetterCapital(char[] letters)
	{
		letters[0]=Character.toUpperCase(letters[0]);
		return new String(letters);
	}
	
}
