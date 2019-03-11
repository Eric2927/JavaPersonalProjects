package Miscellaneous;
import java.util.Scanner;
import java.util.Random;

public class HiLo {

	public static void main(String[] args) {
		boolean playAgain = true;
		Scanner user = new Scanner(System.in);
		while (playAgain) {
			System.out.println("Welcome to Hi-Lo.");
			System.out.println("If you would like to quit the game at any time, just enter anything invalid (Examples: '-23', 'I don't wanna play anymore!').");
			boolean quit = false;
			int user_guess = -37;
			Random myboi2 = new Random();
			int answer = myboi2.nextInt(100) + 1;
			int counter = 0;
			while (!quit) {
				System.out.println("Guess a number between 1 and 100.");
				try {
					user_guess = user.nextInt();
				}
				catch (Exception e) {
					quit = true;
					System.out.println("You have quit the game.");
					break;
				}
				if (user_guess <= 0 || user_guess > 100) {
					quit = true;
					System.out.println("You have quit the game.");
				}
				else if (user_guess < answer) {
					System.out.println("Too low!");
					counter ++;
				}
				else if (user_guess > answer) {
					System.out.println("Too high!");
					counter ++;
				}
				else if (user_guess == answer) {
					System.out.println("Correct! Good Job!");
					System.out.println("It took you " + counter + " tries to get the answer.");
					quit = true;
				}
			}
			System.out.println("Would you like to play again? y/n?");
			user.nextLine();
			String user_playAgain = user.nextLine();
			if (user_playAgain.equals("y")) {
				playAgain = true;
			}
			else {
				playAgain = false;
				System.out.println("Ok cool, bye.");
			}
		}
		user.close();
	}

}
