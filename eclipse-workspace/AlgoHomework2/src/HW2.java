
public class HW2 {

	public static void main(String[] args) {
		System.out.print("Lucas Numbers: ");
		listLucasNumbers1(10);
		System.out.print("\n\n\n");
		listLucasNumbers2(40);
		System.out.print("\n\n\n");
		System.out.println("Sum:\t\t\t\t\tNumber of Combinations");
		// Loops through every possible sum that the subirach square can make
		// (132 is every element of the square summed up)
		for (int i = 0; i < 132; i ++) {
			System.out.print(i + "\t\t\t\t\t" + subirachSumCounter(i) + "\n");
		}
	}
	
	// For problem 1
	public static void listLucasNumbers1(int n) {
		for (int i = 0; i < n; i ++) {
			System.out.print(getLucasNumber(i) + ",");
		}
		System.out.print(getLucasNumber(n) + "\n");
	}
	
	// For problem 2
	public static void listLucasNumbers2(int n) {
		double startTime = -1;
		double endTime = -1;
		int lucasNum = -1;
		double timeTaken = -1;
		double prevTimeTaken = 0;
		double timeRatio = -1;
		for (int i = 0; i <= n; i ++) {
			startTime = System.currentTimeMillis();
			lucasNum = getLucasNumber(i);
			endTime = System.currentTimeMillis();
			timeTaken = endTime - startTime;
			if (prevTimeTaken == 0) {
				timeRatio = timeTaken;
			}
			else {
				timeRatio = timeTaken / prevTimeTaken;
			}
			// I decided to modify the console output so that instead of displaying L(0), L(1), ..., it displays each lucas number
			// followed by the time it took to calculate the lucas number on each new line. I think it looks nicer.
			System.out.println("L(" + i + ")=" + lucasNum + " (" + timeTaken + " milliseconds)     Ratio to Prev Time: " + timeRatio);
			prevTimeTaken = timeTaken;
		}
	}
	
	// Returns the nth lucas number with a naive algorithm
	public static int getLucasNumber(int n) {
		switch (n) {
		case 0:
			return 2;
		case 1:
			return 1;
		default:
			return getLucasNumber(n-1) + getLucasNumber(n-2);
		}
	}
	
	// Problem 3
	public static int subirachSumCounter(int desiredSum) {
		int[] subirachSquare = {1, 14, 14, 4, 11, 7, 6, 9, 8, 10, 10, 5, 13, 2, 3, 15};	// Stores the square
		int currentComboSum = 0;	// Stores sum of current combination being tested
		int temp = 0;	// Helps convert each number to the binary form
		int positionCursor = 0;		// Position in array
		int numCombos = 0;		// Stores total number of combinations that add up to desired sum
		// Loops from 0 to (2^16)-1. In binary, this goes through every possible combination of 16 1s and 0s. Therefore,
		// using those 1s and 0s to determine if an element in the 16-element subirach square should be included in a sum will
		// go through every single possible combination of elements from the subirach square.
		for (int i = 0; i <= 65535; i ++) {
			currentComboSum = 0;
			temp = i;
			positionCursor = 0;
			// Keeps dividing i by 2 until i is 0
			while (temp != 0) {
				// If i is divisible by 2, the value at the positionCursor in the subirachSquare is added to the current sum
				if ((temp%2) == 1) {
					currentComboSum += subirachSquare[positionCursor];
				}
				temp = temp/2;
				positionCursor ++;		// position cursor is incremented
			}
			// If the current sum is equal to the desired sum, number of total combinations that add up to desired sum
			// is incremented
			if (currentComboSum == desiredSum) {
				numCombos ++;
			}
		}
		return numCombos;
	}
	
	
	

}
