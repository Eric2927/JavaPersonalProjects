package StaticArrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
/**
 * This program generates a user-specified number of random integers from 0 to 20 and calculates the mean, median, and mode of those integers.
 * @author eric_li
 *
 */
public class Statistics {

	public static void main(String[] args) {
		Scanner user = new Scanner(System.in);
		Random completelyRandom = new Random();
		System.out.print("Enter a number between 1 and 20: ");
		int length = user.nextInt();
		int[] Array = new int[length];
		user.close();
		for (int i = 0; i < length; i ++) {
			Array[i] = completelyRandom.nextInt(20) + 1;
		}
		Arrays.sort(Array);
		System.out.println(Arrays.toString(Array));
		
		// Calculate mean
		double sum = 0;
		for (int j = 0; j < length; j ++) {
			sum += Array[j];
		}
		double mean = sum / length;
		System.out.println("The mean is " + mean);
		
		// Calculate median
		double median = 0;
		if (length == 1) {
			median = Array[0];
		}
		else {
			median = Array[((length + 1) / 2)];
		}
		
		System.out.println("The median is " + median);
		
		// Calculate mode
		int mode = 0;
		int highestQuantity = 0;
		for (int k = 0; k < length; k ++) {
			int counter = 0;
			for (int l = 0; l < length; l ++) {
				if (Array[l] == Array[k]) {
					counter ++;
				}
			}
			if (counter > highestQuantity) {
				highestQuantity = counter;
				mode = Array[k];
			}
		}
		System.out.println("The mode is " + mode);
	}

}
