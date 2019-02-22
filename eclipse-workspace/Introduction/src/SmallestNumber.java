/* This program takes three numbers and identifies the smallest number. */

import java.util.Scanner;

public class SmallestNumber {

	public static void main(String[] args) {
		Scanner myboi = new Scanner(System.in);
		System.out.println("Please enter three numbers.");
		double a = myboi.nextDouble();
		double b = myboi.nextDouble();
		double c = myboi.nextDouble();
		System.out.println("The three numbers you entered were " + a + ", " + b + ", and " + c + ".");
		myboi.close();
		if (a == b) {
			if (a == c) {
				System.out.println("The smallest number is " + a);
			}
			else if (a > c) {
				System.out.println("The smallest number is " + c);
			}
			else {
				System.out.println("The smallest number is " + a);
			}
		}
		else if (a > b) {
			if (a == c) {
				System.out.println("The smallest number is " + b);
			}
			else if (a > c) {
				if (b > c) {
					System.out.println("The smallest number is " + c);
				}
				else {
					System.out.println("The smallest number is " + b);
				}
			}
			else {
				System.out.println("The smallest number is " + b);
			}
		}
		else {
			if (a == c) {
				System.out.println("The smallest number is " + a);
			}
			else if (a > c) {
				System.out.println("The smallest number is " + c);
			}
			else {
				System.out.println("The smallest number is " + a);
			}
		}
	}
}
