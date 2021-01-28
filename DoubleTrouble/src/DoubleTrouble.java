import java.util.Scanner;
import java.util.Random;

public class DoubleTrouble {

	public static void main(String[] args) {
		Random rand = new Random();								// Random number generator object basically
		boolean isUserTurn = rand.nextBoolean();				// Randomly decides whose turn it is first
		Game doubleTrouble = new Game();						// Initializes the game object
		Scanner userInput = new Scanner(System.in);					// Handles user input
		
		// Introduction text shown to player
		System.out.println("Welcome to Double Trouble!");
		if (isUserTurn) {
			System.out.print("You were chosen to go first. ");
		}
		else {
			System.out.print("The computer was chosen to go first. ");
		}
		System.out.print("Enter anything to begin.\n");
		userInput.next();
		
		
		// While the game board still has pieces on it
		while (!doubleTrouble.isBoardEmpty()) {
			doubleTrouble.displayBoard();						// Displays the board to the user
			// If it is user's turn
			if (isUserTurn) {
				doubleTrouble.executeUserTurn(userInput);		// Executes user's turn. Refer to the method implementation in the Game class for details
			}
			// If it is computer's turn
			else {
				doubleTrouble.executeComputerTurn();			// Executes the computer's turn. Refer to the method implementation in the Game class for details
			}
			isUserTurn = !isUserTurn;						// It is now the other player's turn
			
			// Formatting stuff
			System.out.println();
			System.out.println();
		}
		
		// doubleTrouble.displayBoard();					Originally considered displaying empty board, but that's pointless really		
		
		// Displays who won the game
		if (isUserTurn) {
			System.out.println("Computer wins. Try again next time!");
		}
		else {
			System.out.println("You win. Congratulations!");
		}
		
		userInput.close();

	}

}
