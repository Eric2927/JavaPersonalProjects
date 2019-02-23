package Miscellaneous;



public class Anagramania 
{
	public static void isAnagram(String first, String second) 
	{
		String numericFirst = new String();
		String numericSecond = new String();
		int sumFirst = 0;
		int sumSecond = 0;
		
		if(first.length() < 50 && second.length() < 50) 
		{
		
		first = first.replaceAll("\\s","");
		second = second.replaceAll("\\s","");
		
		
		for(int i = 0; i <= first.length() - 1; i++) 
		{
			char firstChar = first.charAt(i);
			
			int firstCharVal = Character.getNumericValue(firstChar);
			
			numericFirst = numericFirst + firstCharVal;
					
		}
		for(int j = 0; j <= second.length() - 1; j++) 
		{
			char secondChar = second.charAt(j);
			
			int secondCharVal = Character.getNumericValue(secondChar);
			
			numericSecond = numericSecond + secondCharVal;
			
		}
		
		String numericFirstStr = numericFirst.toString();
		String numericSecondStr = numericSecond.toString();
		
		for(int i = 0; i <= numericFirstStr.length() - 1; i++) 
		{
			sumFirst = sumFirst + Character.getNumericValue(numericFirstStr.charAt(i));
					
		}
		for(int j = 0; j <= numericSecondStr.length() - 1; j++) 
		{
			sumSecond = sumSecond + Character.getNumericValue(numericSecondStr.charAt(j));
			
		}
		
		if(sumFirst == sumSecond) 
		{
			System.out.println("Anagram");
			
		}
		else 
		{
			System.out.println("Not Anagram");
			
		}
	}
		else 
		{
			System.out.println("Bigger than 50 characters");
			
		}
	}

	public static void main(String[] args) 
	{
		String str = new String("train tracks");
		String str2 = new String("rats art nick");
		isAnagram(str, str2);
		
	}

}
