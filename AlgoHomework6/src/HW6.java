
public class HW6 {

	public static void main(String[] args) {
		//int[] boardOne = {1, 6, 8, 3, 7, 4, 2, 5};
		//int[] boardTwo = {1, 6, 8, 3, 7, 0, 0, 0};
		//int[] boardThree = {1, 6, 8, 3, 5, 0, 0, 0};

		//System.out.println(isLegalPosition(boardOne, 8));
		//System.out.println(isLegalPosition(boardTwo, 8));
		//System.out.println(isLegalPosition(boardThree, 8));
		
		// int[] answer = nextLegalPosition(boardOne, 8);
		//answer = nextLegalPosition(boardTwo, 8);
		//answer = nextLegalPosition(boardThree, 8);
		
		/*for (int i = 0; i < 8; i ++) {
			System.out.print(answer[i] + " ");
		} */
		
		//problem3();
		//problem4();
	}
	
	// Displays the first solution to every n-queens problem from n = 4 to 100
	public static void problem3() {
		for (int n = 4; n <= 100; n ++) {
			int[] board = new int[n];
			while (board != null && getFirstEmptyRow(board) != n) {
				board = nextLegalPosition(board, n);
			}
			for (int i = 0; i < n; i ++) {
				System.out.print(board[i] + " ");
			}
			System.out.println();
		}
	}
	
	// Displays the number of solutions to every n-queens problem from n = 4 to 20
	public static void problem4() {
		for (int n = 4; n <= 20; n ++) {
			int[] board = new int[n];
			int solutionCounter = 0;
			board = nextLegalPosition(board, n);
			while (board != null) {
				if (getFirstEmptyRow(board) == n) {
					solutionCounter ++;
				}
				board = nextLegalPosition(board, n);
			}
			System.out.println("There are " + solutionCounter + " solutions to the " + n + "-Queens Problem.");
		}
	}
	
	// Checks if the queens on the given board are in a legal position
	public static boolean isLegalPosition(int[] board, int n) {
		int[] zeroIndexedBoard = new int[n];
		// Converts the input board into a zero-indexed version
		// This algorithm would have worked without doing this (with a few tweaks, of course)
		// I just wanted to do this for my own preference
		for (int i = 0; i < n; i ++) {
			zeroIndexedBoard[i] = board[i] - 1;
		}
		boolean valid = true;		// assumption
		for (int i = 0; (i < n) && valid; i ++) {
			// Checks if the ith row is not filled (if so, no need to keep checking)
			if (zeroIndexedBoard[i] == -1) {
				break;
			}
			for (int j = 0; j < i; j ++) {
				// Checks for vertical conflicts
				if (zeroIndexedBoard[i] == zeroIndexedBoard[j]) {
					/*System.out.println("Vertical Conflict");
					System.out.println("Current Row: " + i);
					System.out.println("Current Col: " + zeroIndexedBoard[i]);
					System.out.println("Conflict Row: " + j);
					System.out.println("Conflict Col: " + zeroIndexedBoard[j]); */
					valid = false;
					break;
				}
				// Checks for diagonal conflicts
				if (Math.abs(i - j) == Math.abs(zeroIndexedBoard[i] - zeroIndexedBoard[j])) {
					/*System.out.println("Diagonal Conflict");
					System.out.println("Current Row: " + i);
					System.out.println("Current Col: " + zeroIndexedBoard[i]);
					System.out.println("Conflict Row: " + j);
					System.out.println("Conflict Col: " + zeroIndexedBoard[j]); */
					valid = false;
					break;
				}
			}
		}
		return valid;
	}
	
	// Performs the same thing as isLegalPosition(), except it is implemented under the assumption that
	// the board is filled from top down in a way that only the queen in the bottom row should ever be
	// in an illegal position.
	public static boolean isLegalPositionEfficient(int[] board, int n) {
		int[] zeroIndexedBoard = new int[n];
		// Converts the input board into a zero-indexed version
		// This algorithm would have worked without doing this (with a few tweaks, of course)
		// I just wanted to do this for my own preference
		for (int i = 0; i < n; i ++) {
			zeroIndexedBoard[i] = board[i] - 1;
		}
		
		int lastFilledRow = getFirstEmptyRow(board) - 1;
		boolean valid = true;
		for (int i = 0; i < lastFilledRow; i ++) {
			// Checks for vertical conflicts
			if (zeroIndexedBoard[lastFilledRow] == zeroIndexedBoard[i]) {
				/*System.out.println("Vertical Conflict");
				System.out.println("Current Row: " + i);
				System.out.println("Current Col: " + zeroIndexedBoard[i]);
				System.out.println("Conflict Row: " + j);
				System.out.println("Conflict Col: " + zeroIndexedBoard[j]); */
				valid = false;
				break;
			}
			// Checks for diagonal conflicts
			if (Math.abs(lastFilledRow - i) == Math.abs(zeroIndexedBoard[lastFilledRow] - zeroIndexedBoard[i])) {
				/*System.out.println("Diagonal Conflict");
				System.out.println("Current Row: " + i);
				System.out.println("Current Col: " + zeroIndexedBoard[i]);
				System.out.println("Conflict Row: " + j);
				System.out.println("Conflict Col: " + zeroIndexedBoard[j]); */
				valid = false;
				break;
			}
		}
		return valid;
	}
	
	// Finds the next legal position of the given board
	public static int[] nextLegalPosition(int[] board, int n) {
		int[] nextBoard = board.clone();
		int i = getFirstEmptyRow(board);
		boolean isFullBoard = false;
		if (i == n) {
			isFullBoard = true;
		}
		if (isLegalPositionEfficient(nextBoard, n)) {
			if (isFullBoard) {
				return backtrack(nextBoard, n);
			}
			else {
				nextBoard[i] = nextBoard[i] + 1;
				if (isLegalPositionEfficient(nextBoard, n)) {
					return nextBoard;
				}
				return nextLegalPosition(nextBoard, n);
			}
		}
		else {
			nextBoard[i-1] = nextBoard[i-1] + 1;
			if (nextBoard[i-1] > n) {
				nextBoard[i-1] = 0;
				return backtrack(nextBoard, n);
			}
			
			if (isLegalPositionEfficient(nextBoard, n)) {
				return nextBoard;
			}
			else {
				return nextLegalPosition(nextBoard, n);
			}
		}
	}
	
	// A helper method to the nextLegalPosition() method
	public static int[] backtrack(int[] board, int n) {
		int[] nextBoard = board.clone();
		int i = getFirstEmptyRow(board);
		if (i == 0) {
			return null;
		}
		
		nextBoard[i-1] = nextBoard[i-1] + 1;
		if (nextBoard[i-1] > n) {
			nextBoard[i-1] = 0;
			return backtrack(nextBoard, n);
		}
		
		if (isLegalPositionEfficient(nextBoard, n)) {
			return nextBoard;
		}
		else {
			return backtrack(nextBoard, n);
		}
		
	}
	
	// Takes a board as input and finds the row to which the board is filled to (by queens), and
	// returns the row number of the next row (the empty row), under the assumption that the board is filled
	// from top-down
	public static int getFirstEmptyRow(int[] board) {
		int i = 0;
		while (board[i] != 0) {
			i ++;
			if (i == board.length) {
				break;
			}
		}
		return i;
	}
}
