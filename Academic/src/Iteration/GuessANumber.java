package Iteration;
import java.util.Scanner;
import java.util.Random;

// This is a spin-off of the Hi-Lo code, so it has some extra features that is not part of the requirements.

public class GuessANumber {

	public static void main(String[] args) {
		boolean playAgain = true;
		Scanner myboi = new Scanner(System.in);
		while (playAgain) {
			System.out.println("Welcome to Guess A Number.");
			System.out.println("If you would like to quit the game at any time, just enter anything invalid (Examples: '-23', 'I don't wanna play anymore!').");
			boolean quit = false;
			int user_guess = -37;
			Random myboi2 = new Random();
			int answer = myboi2.nextInt(20) + 1;
			while (!quit) {
				System.out.println("Guess a number between 1 and 20.");
				try {
					user_guess = myboi.nextInt();
				}
				catch (Exception e) {
					quit = true;
					System.out.println("You have quit the game.");
					break;
				}
				if (user_guess <= 0 || user_guess > 20) {
					quit = true;
					System.out.println("You have quit the game.");
				}
				else if (user_guess != answer) {
					System.out.println("Wrong! Try again!");
				}
				else if (user_guess == answer) {
					System.out.println("Correct! Good Job!");
					quit = true;
				}
			}
			System.out.println("Would you like to play again? y/n?");
			myboi.nextLine();
			String user_playAgain = myboi.nextLine();
			if (user_playAgain.equals("y")) {
				playAgain = true;
			}
			else {
				playAgain = false;
				System.out.println("Ok cool, bye.");
			}
		}
		myboi.close();
	}

}
