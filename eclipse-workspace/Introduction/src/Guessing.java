/* This program generates a random number between 1 and 10, and the user can try to guess the number. */

import java.util.Scanner;
import java.util.Random;

public class Guessing {

	public static void main(String[] args) {
		Random myboi2 = new Random();
		int computerNumber = myboi2.nextInt(10) + 1;
		Scanner myboi = new Scanner(System.in);
		System.out.println("Welcome to Guess My Number! I am thinking of a number between 1 and 10. Try to guess my number!");
		int user_guess = myboi.nextInt();
		myboi.close();
		if (user_guess == computerNumber) {
			System.out.println("You guessed correctly! Good job!");
		}
		else {
			System.out.println("Wrong! My number was " + computerNumber);
		}
	}
}
