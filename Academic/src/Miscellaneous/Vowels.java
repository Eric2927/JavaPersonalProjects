package Miscellaneous;
import java.util.Arrays;
public class Vowels {
	public static void main(String[] args) {
		int[] vowels = countVowels("Hello Computer Science students; here is your programming problem. Happy Coding!");
		int[] example = countVowels("It's a good day to have a GREAT day");
		// Print the integer array for display purposes.
		System.out.println(Arrays.toString(vowels));
		System.out.println(Arrays.toString(example));
		}    
	// Accepts a String as a parameter and returns an array of integers
	// representing the counts of each vowel in the String. The array
	// returned by the method should hold 5 elements: the first is the count
	// of A’s, the second is the count of E’s, the third I’s, the fourth
	// O’s, and the fifth U’s.  The String may contain both uppercase and
	// lowercase letters (case insensitive).
	public static int[] countVowels(String input) {
		input = input.toUpperCase();
		System.out.println(input);
		int[] vowel = new int[5];
		for (int i = 0; i < input.length(); i ++) {
			if (input.charAt(i) == 'A') {
				vowel[0] ++;
			}
			else if (input.charAt(i) == 'E') {
				vowel[1] ++;
			}
			else if (input.charAt(i) == 'I') {
				vowel[2] ++;
			}
			else if (input.charAt(i) == 'O') {
				vowel[3] ++;
			}
			else if (input.charAt(i) == 'U') {
				vowel[4] ++;
			}
		}
		return vowel;
	}
}