import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class linearSearch {

	public static void main(String[] args) {
		Scanner security = new Scanner(System.in);
		Random myman = new Random();
		int arrayLength = 25;
		int randomBound = 50;
		int[] list = new int[arrayLength];
		for (int i = 0; i < arrayLength; i ++) {
			list[i] = myman.nextInt(randomBound) + 1;
		}
		System.out.print("Enter a number: ");
		int user_input = security.nextInt();
		security.close();
		boolean inArray = false;
		for (int j = 0; j < arrayLength; j ++) {
			if (list[j] == user_input) {
				inArray = true;
			}
		}
		// This was used to test if the code works.
		// System.out.println(Arrays.toString(list));
		System.out.println(inArray);
	}

}
