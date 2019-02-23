package StaticArrays;
import java.util.Random;
import java.util.Arrays;
/**
 * This program generates an array of 25 random integers from 0 to 50. If there is a number larger than 30 in the array, it will print the first number larger than 30.
 * @author eric_li
 *
 */
public class FindThePosition {

	public static void main(String[] args) {
		Random myman = new Random();
		int arrayLength = 25;
		int randomBound = 50;
		int[] list = new int[arrayLength];
		int position = -1;
		int value = -1;
		for (int i = 0; i < arrayLength; i ++) {
			list[i] = myman.nextInt(randomBound) + 1;
		}
		for (int j = 0; j < arrayLength; j ++) {
			if (list[j] > 30) {
				position = j;
				value = list[j];
				break;
			}
			else {
			}
		}
		// The next line was used to test if it works.
		// System.out.println(Arrays.toString(list));
		if (value == -1) {
			System.out.println("There is no number larger than 30.");
		}
		else {
			System.out.println("The first number larger than 30 is " + value + ", which is at position " + position);
		}
	}
}
