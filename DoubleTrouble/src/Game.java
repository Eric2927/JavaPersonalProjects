import java.util.Random;
import java.util.Scanner;

public class Game {
	
	private int numGreenMarkers;
	private int numYellowMarkers;
	private int numOrangeMarkers;
	

	public Game() {
		numGreenMarkers = 3;
		numYellowMarkers = 7;
		numOrangeMarkers = 5;
	}
	
	public boolean isBoardEmpty() {
		// If there are no markers on the board
		if (numGreenMarkers + numYellowMarkers + numOrangeMarkers == 0) {
			return true;
		}
		return false;
	}
	
	// Displays the game board in the console
	public void displayBoard() {
		System.out.println("Board:");
		int lineCounter = 1;
		int markerCounter = 0;
		for (int i = 0; i < 3; i ++) {
			if (i < numGreenMarkers) {
				System.out.print("G ");
			}
			markerCounter ++;
			if (markerCounter == lineCounter) {
				lineCounter ++;
				markerCounter = 0;
				System.out.println();
			}
		}
		for (int i = 0; i < 7; i ++) {
			if (i < numYellowMarkers) {
				System.out.print("Y ");
			}
			markerCounter ++;
			if (markerCounter == lineCounter) {
				lineCounter ++;
				markerCounter = 0;
				System.out.println();
			}
		}
		for (int i = 0; i < 5; i ++) {
			if (i < numOrangeMarkers) {
				System.out.print("O ");
			}
			markerCounter ++;
			if (markerCounter == lineCounter) {
				lineCounter ++;
				markerCounter = 0;
				System.out.println();
			}
		}		
	}
	
	// Executes user turn and processes user input
	public void executeUserTurn(Scanner userInput) {
		int desiredColor = -1;				// Color of pieces to remove; 1 is green, 2 is yellow, 3 is orange
		int numToRemove = -1;				// Number of pieces of some color to remove
		System.out.print("What color markers would you like to remove? (Please input the number next to the color you want)\n"
				+ "1. Green\n"
				+ "2. Yellow\n"
				+ "3. Orange\n");
		// Loops until user enters a valid input
		while (desiredColor == -1) {
			try {
				desiredColor = userInput.nextInt();				// "Scans" for user's inputted integer
				// Checks if the color is valid
				if (desiredColor < 1 || desiredColor > 3) {
					desiredColor = -1;
					System.out.println("You did not enter a valid choice. Make sure you choose from one of the options presented.");
				}
				else {
					switch (desiredColor) {
					// If user chose green
					case 1:
						// Checks if there are any green markers left before trying to remove one
						if (numGreenMarkers == 0) {
							System.out.println("There are no green markers left to remove. Please choose another color.");
							desiredColor = -1;
						}
						break;
					// If user chose yellow
					case 2:
						// Checks if there are any yellow markers left before trying to remove one
						if (numYellowMarkers == 0) {
							System.out.println("There are no yellow markers left to remove. Please choose another color.");
							desiredColor = -1;
						}
						break;
					// If user chose orange
					case 3:
						// Checks if there are any orange markers left before trying to remove one
						if (numOrangeMarkers == 0) {
							System.out.println("There are no orange markers left to remove. Please choose another color.");
							desiredColor = -1;
						}
						break;
					}
				}
			}
			// If user did not enter an integer
			catch (Exception e) {
				System.out.println("You did not enter a valid choice. Make sure you entered the number next to the color you want.");
			}
		}
		System.out.println("How many markers of that color would you like to remove?");
		// Loops until user enters valid input
		while (numToRemove == -1) {
			try {
				numToRemove = userInput.nextInt();			// Scans for user's integer input
				switch (desiredColor) {
				// If user chose green earlier
				case 1:
					// Checks if the user can remove that number of green markers
					if (numToRemove <= 0) {
						System.out.println("You cannot remove no green markers at all.");
						numToRemove = -1;
					}
					if (numToRemove > numGreenMarkers) {
						System.out.println("There are less than " + numToRemove + " green markers remaining on the board.");
						numToRemove = -1;
					}
					break;
				// If user chose yellow earlier
				case 2:
					// Checks if the user can remove that number of yellow markers
					if (numToRemove <= 0) {
						System.out.println("You cannot remove no yellow markers at all.");
						numToRemove = -1;
					}
					if (numToRemove > numYellowMarkers) {
						System.out.println("There are less than " + numToRemove + " yellow markers remaining on the board.");
						numToRemove = -1;
					}
					break;
				// If user chose orange earlier	
				case 3:
					// Checks if the user can remove that number of orange markers
					if (numToRemove <= 0) {
						System.out.println("You cannot remove no orange markers at all.");
						numToRemove = -1;
					}
					if (numToRemove > numOrangeMarkers) {
						System.out.println("There are less than " + numToRemove + " orange markers remaining on the board.");
						numToRemove = -1;
					}
					break;
				}	
			}
			// If user did not enter an integer
			catch (Exception e) {
				System.out.println("You did not enter a number. Please don't actively try to break this program.");
			}
		}
		
		// Removes the desired number of pieces of the selected color
		switch (desiredColor) {
		case 1:
			numGreenMarkers -= numToRemove;
			break;
		case 2:
			numYellowMarkers -= numToRemove;
			break;
		case 3:
			numOrangeMarkers -= numToRemove;
			break;
		}
	}
	
	// Executes computer's turn
	// Will win if possible
	public void executeComputerTurn() {
		int state = numGreenMarkers ^ numYellowMarkers ^ numOrangeMarkers;			// Current state of the game (winning or losing position)
		Random rand = new Random();				// Random number generator
		int numToRemove = -1;
		// If computer cannot make a winning move this turn
		if (state == 0) {
			// Chooses a random color marker to remove
			switch (rand.nextInt(3) + 1) {
			// If green
			case 1:
				numToRemove = rand.nextInt(numGreenMarkers - 1) + 1;			// Chooses a random number of green markers
				numGreenMarkers -= numToRemove;							// Removes that number of green markers
				System.out.println("Computer removes " + numToRemove + " green markers.");
				break;
			// If yellow
			case 2:
				numToRemove = rand.nextInt(numYellowMarkers - 1) + 1;			// Chooses a random number of yellow markers
				numYellowMarkers -= numToRemove;						// Removes that number of yellow markers
				System.out.println("Computer removes " + numToRemove + " yellow markers.");
				break;
			// If orange
			case 3:
				numToRemove = rand.nextInt(numOrangeMarkers - 1) + 1;			// Chooses a random number of orange markers
				numOrangeMarkers -= numToRemove;						// Removes that number of orange markers
				System.out.println("Computer removes " + numToRemove + " orange markers.");
				break;
			}
		}
		// If computer can make a winning move (and keep winning)
		else {
			boolean winningMoveFound = false;
			// Loops through every color to remove pieces from until a winning move is found (winning move guaranteed to exist)
			for (int i = 1; i < 4 && !winningMoveFound; i ++) {
				switch (i) {
				// Checking if the winning move involves removing green markers
				case 1:
					numToRemove = numGreenMarkers - (numYellowMarkers ^ numOrangeMarkers);
					if (numToRemove > 0) {
						winningMoveFound = true;
						numGreenMarkers -= numToRemove;
						System.out.println("Computer removes " + numToRemove + " green markers.");
					}
					break;
				// Checking if the winning move involves removing yelloww markers
				case 2:
					numToRemove = numYellowMarkers - (numGreenMarkers ^ numOrangeMarkers);
					if (numToRemove > 0) {
						winningMoveFound = true;
						numYellowMarkers -= numToRemove;
						System.out.println("Computer removes " + numToRemove + " yellow markers.");
					}
					break;
				// Checking if the winning move involves removing orange markers
				case 3:
					numToRemove = numOrangeMarkers - (numGreenMarkers ^ numYellowMarkers);
					if (numToRemove > 0) {
						winningMoveFound = true;
						numOrangeMarkers -= numToRemove;
						System.out.println("Computer removes " + numToRemove + " orange markers.");
					}
					break;
				}
			}
			// This should never happen. Ever.
			if (!winningMoveFound) {
				System.out.println("Something went wrong.");
			}
		}
	}

	
}
