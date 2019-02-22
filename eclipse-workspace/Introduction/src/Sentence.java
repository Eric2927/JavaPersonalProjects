/**
 * This is a class that models a sentence
 * @author eric_li
 *
 */
public class Sentence {
	
	private String text;
	
	public Sentence(String aText) {
		text = aText;
	}
	
	/**
	 * Determines if the sentence is a palindrome
	 * @return true if the sentence is a palindrome
	 */
	public boolean isPalindrome() {
		if (text.charAt(0) == text.charAt(text.length()-1)) {
			Sentence a = new Sentence(text.substring(1, text.length()-2));
			a.isPalindrome();
		}
		else {
			return false;
		}
		return true;
	}
	
	/**
	 * Reverses the sentence
	 * @return the reverse of the sentence
	 */
	public String reverse() {
		if (text.length() > 1) {
			char a = text.charAt(0);
			Sentence b = new Sentence(text.substring(1, text.length()));
			return b.reverse() + a;
		}
		else {
			return text;
		}
	}
	
	/**
	 * Checks to see if the inputted string is found in the sentence
	 * @param sub the string that the method is checking for
	 * @return true if the inputted string is in the sentence, false otherwise
	 */
	public boolean find(String sub) {
		if (text.length() < sub.length()) {
			return false;
		}
		else {
			if (text.substring(0, sub.length()).equals(sub)) {
				return true;
			}
			else {
				Sentence yeet = new Sentence(text.substring(1, text.length()));
				return yeet.find(sub);
			}
		}
	}
	
	/**
	 * Finds the input string in the sentence and returns its index
	 * @param str the string that the method is looking for the index of
	 * @return the index of the position of the input string in the sentence
	 */
	public int indexOf(String str) {
		return privateindexOf(str, 0);
	}
	
	/**
	 * Finds the input string in the sentence and returns its index
	 * @param str the string that the method is looking for the index of
	 * @param index a variable used to aid the recursion
	 * @return the index of the position of the input string in the sentence
	 */
	private int privateindexOf(String str, int index) {
		int counter = index;
		if (text.length() < str.length()) {
			return -1;
		}
		else {
			if (text.substring(0, str.length()).equals(str)) {
				return counter;
			}
			else {
				Sentence yeet = new Sentence(text.substring(1, text.length()));
				counter ++;
				return yeet.privateindexOf(str, counter);
			}
		}
	}
	
	/**
	 * Lists out all of the possible substrings that the sentence can make
	 * @return the possible substrings
	 */
	public String substringGenerator() {
		if (text.length() == 0) {
			return "\"\"";
		}
		else {
			String partialOutput = "";
			for (int i = 1; i <= text.length(); i ++) {
				partialOutput = partialOutput + "\"" + text.substring(0, i) + "\", ";
			}
			Sentence shorter = new Sentence(text.substring(1, text.length()));
			return partialOutput + shorter.substringGenerator();
		}
	}
	
	/**
	 * Gets the text of the sentence
	 * @return the text of the sentence
	 */
	public String getText() {
		return text;
	}
}
