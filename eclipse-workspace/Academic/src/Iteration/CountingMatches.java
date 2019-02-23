package Iteration;
import java.util.Scanner;
/**
 * This program checks how many capital letters are in the input string.
 * @author eric_li
 *
 */
public class CountingMatches {

	public static void main(String[] args) {
		Scanner user = new Scanner(System.in);
		System.out.print("Enter a word or phrase: ");
		String user_input = user.nextLine();
		int counter = 0;
		int i;
		for (i = 0; i < user_input.length(); i ++) {
			char letter = user_input.charAt(i);
			String stringLetter = Character.toString(letter);
			if (letter == 32) {
				// Nothing
			}
			else if (stringLetter.equals(stringLetter.toUpperCase())) {
				counter ++;
			}
		}
		user.close();
		System.out.println("The input has " + counter + " capital letter(s).");
	}

}
