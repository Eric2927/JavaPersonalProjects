/* This program takes an inputed string and extracts the letter(s) in the middle of string. If the string has an odd number of characters, it will extract the character in the middle. If the string has an even number of characters, it will extract the two characters in the middle. */

import java.util.Scanner;

public class MiddleExtractor {

	public static void main(String[] args) {
		Scanner myboi = new Scanner(System.in);
		System.out.println("Enter a word.");
		String word = myboi.next();
		myboi.close();
		int word_length = word.length();
		int half_word_length = word_length / 2;
		int even_odd_detector = word_length % 2;
		char middleletter1 = word.charAt(half_word_length);
		char middleletter2 = word.charAt(half_word_length - 1);
		if (even_odd_detector == 0) {
			System.out.print(middleletter1);
			System.out.print(middleletter2);
		}
		else {
			System.out.println(middleletter1);
		}
	}
}
