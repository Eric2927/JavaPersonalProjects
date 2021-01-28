package Decisions;
/* This program takes three numbers and identifies the smallest number. */

import java.util.Scanner;

public class SmallestNumber {

	public static void main(String[] args) {
		Scanner user = new Scanner(System.in);
		System.out.println("Please enter three numbers.");
		double a = user.nextDouble();
		double b = user.nextDouble();
		double c = user.nextDouble();
		System.out.println("The three numbers you entered were " + a + ", " + b + ", and " + c + ".");
		user.close();
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
