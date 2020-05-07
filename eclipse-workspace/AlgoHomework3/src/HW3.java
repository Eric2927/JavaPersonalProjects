
public class HW3 {

	public static void main(String[] args) {
		String myStr = "abcdcba";
		System.out.println(palindromecheck(myStr));
		
		int[] myArray = {4, 3, 2, 1, 8, 7, 6, 5};
		int[] myArray2 = {38, 27, 43, 3, 9, 82, 10};
		System.out.println(easyinversioncount(myArray));
		System.out.println(easyinversioncount(myArray2));
		System.out.println(hardinversioncount(myArray));
		System.out.println(hardinversioncount(myArray2));
		
		String[] childrenNames = {"Alice", "Bob", "Chris", "Dylan", "Eve", "Felix", "Gerry", "Helen", "Ian", "Jane", "Karl"};
		graycodesarefun(4, childrenNames);

	}
	
	// Problem 1
	public static boolean palindromecheck(String text) {
		// Base case (if test has passed so far, then it must be a palindrome)
		if (text.length() <= 1) {
			return true;
		}
		
		// Checks if the first and last characters are equal
		else if (text.charAt(0) == text.charAt(text.length()-1)) {
			// Makes a new string that is the same as the old string, except without the first and last characters
			String subText = text.substring(1, text.length()-1);
			return palindromecheck(subText);		// Recursively calls palindromecheck on the new string
		}
		return false;
	}
	
	// Naive O(n^2) inversion count alogrithm for problem 2
	public static int easyinversioncount(int[] array) {
		int[] arrayCopy = array.clone();		// Makes a copy of input array to put through bubble sort (don't want to alter original array)
		boolean sorted = false;			// Represents whether the array is sorted or not
		int temp = -1;			// Used to store a value while swapping two values in the array
		int numInversions = 0;		// Stores number of inversions
		while (!sorted) {
			sorted = true;			// Assumption
			for (int i = 0; i < arrayCopy.length - 1; i ++) {
				// If an inversion is found
				if (arrayCopy[i] > arrayCopy[i+1]) {
					sorted = false;				// Assumption is false
					// Swap the inverted pair
					temp = arrayCopy[i];
					arrayCopy[i] = arrayCopy[i+1];
					arrayCopy[i+1] = temp;
					numInversions ++;		// Number of inversions is incremented
				}
			}
		}
		return numInversions;
	}
	
	// O(nlogn) inversion count algorithm for problem 2
	public static int hardinversioncount(int[] array) {
		int[] arrayCopy = array.clone();		// Makes a copy of the input array as to not alter the original array while sorting
		int finalCount = mergeSortInvCounter(arrayCopy);
		return finalCount;
	}
	
	// First Helper for hardinversioncount method
	// Essentially, this method divides the array into halves repeatedly and recursively sorts them, like mergesort
	// However, it also keeps track of the number of inversions along the way
	public static int mergeSortInvCounter(int[] array) {
		if (array.length > 1) {
			int inversionCounter = 0;		// Number of inversions
			int[] leftArray = new int[array.length/2];
			int[] rightArray = new int[array.length - array.length/2];
			// Divides the input array into two equal-sized subarrays
			for (int i = 0; i < array.length; i ++) {
				if (i < array.length/2) {
					leftArray[i] = array[i];
				}
				else {
					rightArray[i - array.length/2] = array[i];
				}
			}
			
			// Recursively sorts the left and right subarrays, also counting the number of inversions along the way
			inversionCounter += mergeSortInvCounter(leftArray);
			inversionCounter += mergeSortInvCounter(rightArray);
			// Keeps track of the number of inversions and adds them all up to form a final sum at the end
			return inversionCounter + merge(array , leftArray, rightArray);
		}
		return 0;
	}
	
	// Second Helper for hardinversioncount method
	// This performs the merge part of the mergesort, but in addition counts the number of inversions
	public static int merge(int[] mergedArray, int[] leftArray, int[] rightArray) {
		int inversionCounter = 0;
		int leftCursor = 0;		// Tracks which element in the left array is being compared (index of the "first" element in left array)
		int rightCursor = 0;		// Tracks which element in the right array is being compared (index of the "first" element in right array)
		int cursor = 0;			// Tracks where the smaller element should be inserted into in the mergedArray
		// Keeps looping until either the left array or the right array is "empty"
		while (leftCursor < leftArray.length && rightCursor < rightArray.length) {
			// Compares the "first" element of the left array and "first" element of the right array
			if (leftArray[leftCursor] > rightArray[rightCursor]) {
				// If the first element in the left array is larger than the first element in the right array, then
				// every single element in the left array forms an inversion with the first element in the right array
				inversionCounter += leftArray.length - leftCursor;
				mergedArray[cursor] = rightArray[rightCursor];
				rightCursor ++;
			}
			else {
				mergedArray[cursor] = leftArray[leftCursor];
				leftCursor ++;
			}
			cursor ++;
		}
		
		// The leftover elements in the left/right array are put into the merged array
		while (leftCursor < leftArray.length) {
			mergedArray[cursor] = leftArray[leftCursor];
			cursor ++;
			leftCursor ++;
		}
		while (rightCursor < rightArray.length) {
			mergedArray[cursor] = rightArray[rightCursor];
			cursor ++;
			rightCursor ++;
		}
		return inversionCounter;		// Number of inversions is returned
	}
	
	// Problem 3
	// Note: The input array names needs to be longer than or equal to n, the other input.
	public static void graycodesarefun(int n, String[] names) {
		String[] childrenArrangements = BRGC(n);
		String[] moves = new String[childrenArrangements.length];		// Stores the actions
		moves[0] = "None";		// First action is none, because first photo involves no children at all, only backdrop
		int movePerformed = 0;
		// Loops through every subset of children
		for (int i = 0; i < childrenArrangements.length - 1; i ++) {
			// Loops through every child
			for (int j = 0; j < n; j ++) {
				// movePerformed is the difference between the bits of consecutive children arrangements at digit j
				movePerformed = Character.getNumericValue(childrenArrangements[i+1].charAt(j)) - Character.getNumericValue(childrenArrangements[i].charAt(j));
				// If the difference is positive, action was to add someone to the photo
				if (movePerformed > 0) {
					moves[i+1] = names[n-j-1] + " in";
				}
				// If difference is negative, action was to remove someone from the photo
				else if (movePerformed < 0) {
					moves[i+1] = names[n-j-1] + " out";
				}
			}
		}
		
		// Prints out the info needed for the table in a nice way (not in a table because it looks nicer this way)
		for (int i = 0; i < childrenArrangements.length; i ++) {
			System.out.println("Index: " + i);
			System.out.println("Gray Code: " + childrenArrangements[i]);
			System.out.print("Child(ren) in Photo: ");
			for (int j = 0; j < n; j ++) {
				if (childrenArrangements[i].charAt(j) == '1') {
					System.out.print(names[n-j-1] + " ");
				}
			}
			System.out.println();
			System.out.print("Actions: " + moves[i]);
			System.out.println();
			System.out.println();
		}
		
	}
	
	// Generates all binary reflected gray code of order n in strings
	public static String[] BRGC(int n) {
		// Base case
		if (n == 1) {
			String[] L = {"0", "1"};
			return L;
		}
		else {
			String[] L1 = BRGC(n-1);		// Recursive call to generate brgc of n-1 order
			String[] L2 = new String[L1.length*2];			// Stores complete brgc of n order (double size of brgc of n-1 order)
			// Populates L2
			for (int i = 0; i < L2.length; i ++) {
				// First half should be the brgc of n-1 order, with a zero tagged on to the beginnings
				if (i < L1.length) {
					L2[i] = L1[i];
					L2[i] = "0" + L2[i];
				}
				// Second half should be the brgc of n-1 order in reverse order, with a one tagged on to the beginnings
				else {
					L2[i] = L1[L2.length - i - 1];
					L2[i] = "1" + L2[i];
				}
			}
			return L2;
		}
	}
}
