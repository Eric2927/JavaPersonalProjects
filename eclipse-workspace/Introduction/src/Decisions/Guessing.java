package Decisions;
/* This program generates a random number between 1 and 10, and the user can try to guess the number. */

import java.util.Scanner;
import java.util.Random;

public class Guessing {

	public static void main(String[] args) {
		Random completelyRandom = new Random();
		int computerNumber = completelyRandom.nextInt(10) + 1;
		Scanner user = new Scanner(System.in);
		System.out.println("Welcome to Guess My Number! I am thinking of a number between 1 and 10. Try to guess my number!");
		int user_guess = user.nextInt();
		user.close();
		if (user_guess == computerNumber) {
			System.out.println("You guessed correctly! Good job!");
		}
		else {
			System.out.println("Wrong! My number was " + computerNumber);
		}
	}
}
