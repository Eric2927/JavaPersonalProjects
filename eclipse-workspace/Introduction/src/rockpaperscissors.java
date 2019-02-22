import java.util.Random;
import java.util.Scanner;

public class rockpaperscissors {

	public static void main(String[] args) {
		System.out.println("Welcome to Rock Paper Scissors!");
		Scanner myboi = new Scanner(System.in);
		System.out.println("Rock, Paper, or Scissors?");
		String raw_user_choice = myboi.next();
		myboi.close();
		String user_choice = raw_user_choice.toLowerCase();
		System.out.println(user_choice);
		Random myboi2 = new Random();
		int randy = myboi2.nextInt(3);
		String computer_choice;
		if (randy == 0) {
			computer_choice = "rock";
			System.out.println("You chose " + user_choice + ". The computer chose " + computer_choice);
			if (user_choice.equals("rock")) {
				System.out.println("It's a tie!");
			}
			else if (user_choice.equals("paper")) {
				System.out.println("You win!");
			}
			else if (user_choice.equals("scissors")) {
				System.out.println("You lose!");
			}
			else {
				System.out.println("You did not enter a valid choice!");
			}
		}
		else if (randy == 1) {
			computer_choice = "paper";
			System.out.println("You chose " + user_choice + ". The computer chose " + computer_choice);
			if (user_choice.equals("rock")) {
				System.out.println("You lose!");
			}
			else if (user_choice.equals("paper")) {
				System.out.println("It's a tie!");
			}
			else if (user_choice.equals("scissors")) {
				System.out.println("You win!");
			}
			else {
				System.out.println("You did not enter a valid choice!");
			}

		}
		else {
			computer_choice = "scissors";
			System.out.println("You chose " + user_choice + ". The computer chose " + computer_choice);
			if (user_choice.equals("rock")) {
				System.out.println("You win!");
			}
			else if (user_choice.equals("paper")) {
				System.out.println("You lose!");
			}
			else if (user_choice.equals("scissors")) {
				System.out.println("It's a tie!");
			}
			else {
				System.out.println("You did not enter a valid choice!");
			}

		}
	}
}
