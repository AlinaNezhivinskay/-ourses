
public class WordsConcatenation {
	public String getStrOfWordsWithCapitalLetter(String [] words)
	{
		StringBuilder str=new StringBuilder();
		for(int i=0;i<words.length;i++)
		{
			words[i]=setFirstLetterCapital(words[i]); 
			str.append(words[i]).append(' ');
		}
		return str.toString();
	}
	
	private String setFirstLetterCapital(String word)
	{
		char[] letters=word.toCharArray();
		letters[0]=Character.toUpperCase(letters[0]);
		return new String(letters);
	}
}
