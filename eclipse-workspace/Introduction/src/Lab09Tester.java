// Lab09.java
// The Rational Class Program I
// This is the starting version of the Lab09 assignment.

import java.util.Scanner;

public class Lab09Tester {

	public static void main(String[] args) {
	    Scanner input = new Scanner(System.in);
	    System.out.print("\nEnter the numerator ----> ");
	    int num = input.nextInt();
	    System.out.print("\nEnter the denominator --> ");
	    int den = input.nextInt();
	    input.close();
	    
	    Rational rat = new Rational(num, den);
	    System.out.print("\n" + rat.getOriginal() + " equals " + rat.getDecimal() + "\n\nand reduces to " + rat.getRational());
	}
	
}