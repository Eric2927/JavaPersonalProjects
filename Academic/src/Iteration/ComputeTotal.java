package Iteration;
import java.util.Scanner;
/**
 * This program adds up all the consecutive integers from 1 to the user input
 * @author eric_li
 *
 */
public class ComputeTotal {

	public static void main(String[] args) {
		Scanner user = new Scanner(System.in);
		// Assumes that user will not enter a non-integer value.
		System.out.print("Enter an integer. ");
		int user_input = user.nextInt();
		int sum = 0;
		for (int i = 1; i <= user_input; i ++) {
			sum += i;
		}
		user.close();
		System.out.println(sum);
	}

}
