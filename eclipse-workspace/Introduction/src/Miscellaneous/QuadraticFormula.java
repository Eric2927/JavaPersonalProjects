package Miscellaneous;
import java.util.Scanner;

public class QuadraticFormula {

	public static void main(String[] args) {
		System.out.println("Given the form Ax^2 + Bx + C = 0");
		Scanner myboi = new Scanner(System.in);
		System.out.println("Please enter the coefficient for A: ");
		double A = myboi.nextDouble();
		System.out.println("Please enter the coefficient for B: ");
		double B = myboi.nextDouble();
		System.out.println("Please enter the coefficient for C: ");
		double C = myboi.nextDouble();
		myboi.close();
		double discriminant = Math.pow(B, 2) - 4*A*C;
		if (discriminant < 0) {
			System.out.println("There are no real solutions.");
		}
		else if (discriminant == 0 ) {
			double x = (-B/(2*A));
			System.out.println("x = " + x);
		}
		else {
			double x1 = (-B + Math.sqrt((Math.pow(B, 2) - 4*A*C)))/(2*A);
			double x2 = (-B - Math.sqrt((Math.pow(B, 2) - 4*A*C)))/(2*A);
			System.out.println("x = " + x1 + " or x = " + x2);
		}
	}

}
