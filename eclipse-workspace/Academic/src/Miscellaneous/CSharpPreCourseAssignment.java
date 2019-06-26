// The language used here is Java

package Miscellaneous;
import java.util.ArrayList;
import java.util.Scanner;

public class CSharpPreCourseAssignment {

	public static void main(String[] args) {
		Scanner user = new Scanner(System.in);
		
		// With Array
		ArrayList<Integer> userNumbers = new ArrayList<>();
		while (true) {
			System.out.println("Enter the next number:");
			try {
				int a = Integer.parseInt(user.nextLine());
				if (a == -1) {
					break;
				}
				else if (a <= 0) {
					System.out.println("You did not enter a positive number.");
				}
				else {
					userNumbers.add(a);
				}
			}
			catch (Exception e) {
				System.out.println("You did not enter a valid number.");
			}
		}
		int maxNum = userNumbers.get(0);
		int minNum = userNumbers.get(0);
		if (userNumbers.size() != 1) {
			for (int i = 0; i < userNumbers.size() - 1; i ++) {
				if (Math.max(userNumbers.get(i), userNumbers.get(i + 1)) > maxNum) {
					maxNum = Math.max(userNumbers.get(i), userNumbers.get(i + 1));
				}
				if (Math.min(userNumbers.get(i), userNumbers.get(i + 1)) < minNum) {
					minNum = Math.min(userNumbers.get(i), userNumbers.get(i + 1));
				}
			}
		}
		System.out.println("Code With Array");
		System.out.println("The number of positive integers in the series: " + userNumbers.size());
		System.out.println("The largest number in the series: " + maxNum);
		System.out.println("The smallest number in the series: " + minNum);
		
		// Without Array
		int numberOfTerms = 0;
		int max = 0;
		int min = 0;
		while (true) {
			System.out.println("Enter the next number:");
			if (numberOfTerms == 0) {
				try {
					int firstNum = Integer.parseInt(user.nextLine());
					if (firstNum == -1) {
						break;
					}
					else if (firstNum <= 0) {
						System.out.println("You did not enter a positive number.");
					}
					else {
						max = firstNum;
						min = firstNum;
						numberOfTerms ++;
					}
				}
				catch (Exception e) {
					System.out.println("You did not enter a valid number.");
				}
			}
			else {
				try {
					int userInput = Integer.parseInt(user.nextLine());
					if (userInput == -1) {
						break;
					}
					else if (userInput <= 0) {
						System.out.println("You did not enter a positive number.");
					}
					else {
						max = Math.max(max, userInput);
						min = Math.min(min, userInput);
						numberOfTerms ++;
					}
				}
				catch (Exception e) {
					System.out.println("You did not enter a valid number.");
				}
			}
		}
		System.out.println("Code Without Array");
		System.out.println("The number of positive integers in the series: " + numberOfTerms);
		System.out.println("The largest number in the series: " + max);
		System.out.println("The smallest number in the series: " + min);
	}

}
