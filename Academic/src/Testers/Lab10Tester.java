package Testers;
// Lab10.java
// The Rational Class Program II
// This is the starting version of the Lab10 assignment.

import java.util.Scanner;

import Labs.Rational;

public class Lab10Tester {

	public static void main(String[] args) {
	    Scanner input = new Scanner(System.in);
	    System.out.print("\nEnter the 1st numerator ----> ");
	    int num1 = input.nextInt();
	    System.out.print("\nEnter the 1st denominator --> ");
	    int den1 = input.nextInt();
	    System.out.print("\nEnter the 2nd numerator ----> ");
	    int num2 = input.nextInt();
	    System.out.print("\nEnter the 2nd denominator --> ");
	    int den2 = input.nextInt();
	    input.close();
	    
	    Rational r1 = new Rational(num1, den1);
	    Rational r2 = new Rational(num2, den2);
	    Rational r3 = new Rational();
	    
	    r3 = r1.multiply(r2);
	    System.out.println("\n\n" + r1.getOriginal() + " * " + r2.getOriginal() + "  =  " + r3.getRational());
	    r3 = r1.divide(r2);
	    System.out.println("\n" +r1.getOriginal() + " / " + r2.getOriginal() + "  =  " + r3.getRational());
	    
	    r3 = r1.add(r2);
	    System.out.println("\n" + r1.getOriginal() + " + " + r2.getOriginal() + "  =  " + r3.getRational());
	    r3 = r1.subtract(r2);
	    System.out.println("\n" + r1.getOriginal() + " - " + r2.getOriginal() + "  =  " + r3.getRational());
	}

}