
public class HW4 {

	public static void main(String[] args) {
		// Problem 3
		double[][] coefficientMatrix = {{1,1,1,1,1,1,1,1}, 
				{1,2,1,1,1,1,2,1}, 
				{1,1,3,1,1,3,1,1},
				{1,1,1,4,4,1,1,1},
				{11,1,1,1,1,1,1,1},
				{1,1,1,1,-1,-1,-1,-1},
				{1,2,3,4,5,6,7,8},
				{1,-1,1,-1,1,-1,1,-1}};
		double[] constantMatrix = {0,0,0,0,20,34,-51,-6};
		double[] answers = gaussjordanElimination(coefficientMatrix, constantMatrix);
		System.out.println("Problem 3:");
		System.out.print("Answers: ");
		for (int i = 0; i < answers.length; i ++) {
			System.out.print(answers[i] + " ");
		}
		System.out.println();
		System.out.println();
		
		
		// Problem 4
		System.out.println("Problem 4:");
		int[][] room = {{98,70,73,83,97,33,44,99},
						{46,23,90,76,10,42,1,53},
						{66,52,27,5,91,94,82,30},
						{22,92,68,12,56,63,47,67},
						{13,71,48,14,78,11,89,95},
						{31,4,64,25,32,41,17,16},
						{79,38,24,49,15,6,40,74},
						{81,96,19,20,34,51,93,65}};
		findMostPreciousPath(room);
		
	}
	
	// Coded with the assumption that the input arrays are sized appropriately
	// Aka the A matrix should be a square matrix
	public static double[] gaussjordanElimination(double[][] A, double[] B) {
		// Augmenting the matrix and storing it inside augmentedMatrix
		int n = A.length;
		double[][] augmentedMatrix = new double[n][n + 1];
		for (int row = 0; row < n; row ++) {
			for (int col = 0; col < n; col ++) {
				augmentedMatrix[row][col] = A[row][col];
			}
			augmentedMatrix[row][n] = B[row];
		}
		
		// The majority of this code is basically the BetterForwardElimination algorithm
		// The parts that are different are commented
		double[] answers = new double[n];
		int pivotRow = 0;
		double temp = 0;
		for (int i = 0; i < n; i ++) {
			pivotRow = i;
			for (int j = i + 1; j < n; j ++) {
				if (Math.abs(augmentedMatrix[j][i]) > Math.abs(augmentedMatrix[pivotRow][i])) {
					pivotRow = j;
				}
			}
			// If system has infinite solutions
			if (augmentedMatrix[pivotRow][i] < 0.0001 && augmentedMatrix[pivotRow][i] > -0.0001) {
				System.out.println("Infinite number of solutions");
				System.out.println(augmentedMatrix[pivotRow][i]);
				return answers;
			}
			for (int k = i; k < n + 1; k ++) {
				temp = augmentedMatrix[i][k];
				augmentedMatrix[i][k] = augmentedMatrix[pivotRow][k];
				augmentedMatrix[pivotRow][k] = temp;
			}
			for (int j = i + 1; j < n; j ++) {
				temp = augmentedMatrix[j][i] / augmentedMatrix[i][i];
				for (int k = i; k < n + 1; k ++) {
					augmentedMatrix[j][k] = augmentedMatrix[j][k] - augmentedMatrix[i][k]*temp;
				}
			}
			// Zeros out the elements above the main diagonal
			for (int j = i - 1; j >= 0; j --) {
				temp = augmentedMatrix[j][i] / augmentedMatrix[i][i];
				for (int k = i; k < n + 1; k ++) {
					augmentedMatrix[j][k] = augmentedMatrix[j][k] - augmentedMatrix[i][k]*temp;
				}
			}
			
			// Deals with round-off errors
			for (int row = 0; row < n; row ++) {
				for (int col = 0; col < n+1; col ++) {
					if (Math.abs(augmentedMatrix[row][col] - Math.round(augmentedMatrix[row][col])) < 0.0001) {
						augmentedMatrix[row][col] = Math.round(augmentedMatrix[row][col]);
					}
				}
			}
		}
		
		// Divides every row by its leading diagonal coefficient to convert it into identity matrix
		for (int row = 0; row < n; row ++) {
			augmentedMatrix[row][n] = augmentedMatrix[row][n] / augmentedMatrix[row][row];
			if (Math.abs(augmentedMatrix[row][n] - Math.round(augmentedMatrix[row][n])) < 0.0001) {
				augmentedMatrix[row][n] = Math.round(augmentedMatrix[row][n]);
			}
			augmentedMatrix[row][row] = augmentedMatrix[row][row] / augmentedMatrix[row][row];
		}
		
		// Extracts the rightmost column and stores it in the answers arrray, which is then returned
		for (int i = 0; i < n; i ++) {
			answers[i] = augmentedMatrix[i][n];
		}
		return answers;
	}
	
	// Helper method to display the matrix whenever needed during testing/debugging
	public static void displayArray(double[][] array) {
		for (int row = 0; row < array.length; row ++) {
			for (int col = 0; col < array[0].length; col ++) {
				System.out.print(array[row][col] + "\t");
			}
			System.out.println();
		}
	}
	
	// Finds and displays information about the most precious path leading to Arkenstone
	public static void findMostPreciousPath(int[][] room) {
		int[][] sums = new int[room.length][room[0].length];		// Stores the highest possible sums upon reaching the given vault at the given row (similar to the knapsack problem)
		// The three temporary variables are to find the maximum between the three possible vaults in the previous row
		int temp1 = 0;
		int temp2 = 0;
		int temp3 = 0;
		for (int row = room.length - 1; row >= 0; row --) {
			for (int col = 0; col < room[0].length; col ++) {
				try {
					temp1 = sums[row+1][col-1];
				} catch (ArrayIndexOutOfBoundsException e) {
					temp1 = 0;
				}
				try {
					temp2 = sums[row+1][col];
				} catch (ArrayIndexOutOfBoundsException e) {
					temp2 = 0;
				}
				try {
					temp3 = sums[row+1][col+1];
				} catch (ArrayIndexOutOfBoundsException e) {
					temp3 = 0;
				}
				sums[row][col] = room[row][col] + Math.max(Math.max(temp1, temp2), temp3);
			}
		}
	
		int maxTreasure = 0;
		int[] reversePath = new int[room.length];		// Stores the most precious path in reverse order
		// Finds the vault in the final row that results in the most precious path (sorry bad word choice, but i hope you understand)
		for (int col = 0; col < room[0].length; col ++) {
			if (sums[0][col] > maxTreasure) {
				maxTreasure = sums[0][col];
				reversePath[0] = col;
			}
		}
		
		// Traces the path taken to get to each successive vault
		for (int row = 1; row < room.length; row ++) {
			for (int i = -1; i <= 1; i ++) {
				try {
					if (sums[row-1][reversePath[row-1]] - sums[row][reversePath[row-1] + i] == room[row-1][reversePath[row-1]]) {
						reversePath[row] = reversePath[row-1] + i;
						break;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					// Nothing
				}
			}
		}
		
		// Displays the most precious path and information about it
		System.out.println("Starting vault: " + (reversePath[room.length-1] + 1));
		System.out.println("Vault containing Arkenstone: " + (reversePath[0] + 1));
		System.out.println("Path:");
		for (int i = room.length - 1; i >= 0; i --) {
			System.out.println("Row: " + (room.length - i) + ", Vault: " + (reversePath[i]+1));
		}
		
		System.out.println("Max Treasure: " + maxTreasure);
		
		
		
	}
}
