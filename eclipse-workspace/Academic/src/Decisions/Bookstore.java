package Decisions;
/* This program determines the price of a computer during a sale, where the discount changes based on the price of the computer. */

import java.util.Scanner;

public class Bookstore {

	public static void main(String[] args) {
		Scanner user = new Scanner(System.in);
		System.out.println("How much is the computer?");
		double computerPrice = user.nextDouble();
		user.close();
		double finalcomputerPrice = 0;
		if (computerPrice >= 128) {
			finalcomputerPrice = computerPrice * 0.84;
		}
		else {
			finalcomputerPrice = computerPrice * 0.92;
		}
		System.out.println("Price after Discount: " + finalcomputerPrice);
	}

}
