package StaticArrays;
import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
/**
 * This program prompts the user from an integer and then checks if it is in a randomly generated = array of 25 integers. 
 * @author eric_li
 *
 */
public class linearSearch {

	public static void main(String[] args) {
		Scanner user = new Scanner(System.in);
		Random completelyRandom = new Random();
		int arrayLength = 25;
		int randomBound = 50;
		int[] list = new int[arrayLength];
		for (int i = 0; i < arrayLength; i ++) {
			list[i] = completelyRandom.nextInt(randomBound) + 1;
		}
		System.out.print("Enter a number: ");
		int user_input = user.nextInt();
		user.close();
		boolean inArray = false;
		for (int j = 0; j < arrayLength; j ++) {
			if (list[j] == user_input) {
				inArray = true;
			}
		}
		// The next line was used to test if the code works.
		// System.out.println(Arrays.toString(list));
		System.out.println(inArray);
	}

}
