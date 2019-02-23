package Miscellaneous;
import java.util.Arrays;

public class FoodDistribution {

	public static void main(String[] args) {
		int[] Dan = {1,1,1,1};
		System.out.println(Distribution(Dan));
	}
	
	public static int Distribution(int[] arr) {
		int sandwitches = arr[0];
		int difference = 0;
		for (int i = 1; i < arr.length - 1; i ++) {
			if (arr[i+1] > arr[i]) {
				difference += arr[i+1] - arr[i];
			}
			else difference += arr[i] - arr[i+1];
		}
		return difference;
	}

}
