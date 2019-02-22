// Lab05 
// The Federal Tax Rate Program 
// This is the student, starting version of Lab05.
import java.util.Scanner;
import java.text.DecimalFormat;

public class FederalTaxRate {
	
	public static void main(String args[]) {
		System.out.println("Lab05: The Federal Tax Rate Program");
		Scanner myboi = new Scanner(System.in);
		System.out.println("Question 1: Are you married? Yes or no?");
		String maritalStatus = myboi.nextLine();
		System.out.println("Question 2: What is your taxable income this year? Enter the number (in dollars) only please");
		double user_income = 0;
		try {
			user_income = myboi.nextDouble();
		}
		catch (Exception e) {
			System.out.println("You did not enter a valid taxable income for question 2.");
		}
		myboi.close();
		// Setting base values for a, b, and c. Does not actually mean anything.
		double a = 0;
		double b = 0;
		double c = 0;
		boolean x = true;
		boolean y = true;
		boolean w = true;
		if (maritalStatus.equals("Yes") || maritalStatus.equals("yes")) {
			if (user_income > 0 && user_income <= 18650) {
				a = 0;
				b = 0.1;
				c = 0;
			}
			else if (user_income > 18650 && user_income <= 75900) {
				a = 1865;
				b = 0.15;
				c = 18650;
			}
			else if (user_income > 75900 && user_income <= 153100) {
				a = 10452.5;
				b = 0.25;
				c = 75900;
			}
			else if (user_income > 153100 && user_income <= 233350) {
				a = 29752.5;
				b = 0.28;
				c = 153100;
			}
			else if (user_income > 233350 && user_income <= 416700) {
				a = 52222.5;
				b = 0.33;
				c = 233350;
			}
			else if (user_income > 416700 && user_income <= 470700) {
				a = 112728;
				b = 0.35;
				c = 416700;
			}
			else if (user_income > 470700) {
				a = 131628;
				b = 0.396;
				c = 470700;
			}
			else if (user_income == 0) {
				w = false;
			}
			else {
				y = false;
			}
		}
		else if (maritalStatus.equals("No") || maritalStatus.equals("no")) {
			if (user_income > 0 && user_income <= 9325) {
				a = 0;
				b = 0.1;
				c = 0;
			}
			else if (user_income > 9325 && user_income <= 37950) {
				a = 932.5;
				b = 0.15;
				c = 9325;
			}
			else if (user_income > 37950 && user_income <= 91900) {
				a = 5226.25;
				b = 0.25;
				c = 37950;
			}
			else if (user_income > 91900 && user_income <= 191650) {
				a = 18713.75;
				b = 0.28;
				c = 91900;
			}
			else if (user_income > 191650 && user_income <= 416700) {
				a = 46643.75;
				b = 0.33;
				c = 191650;
			}
			else if (user_income > 416700 && user_income <= 418400) {
				a = 120910.25;
				b = 0.35;
				c = 416700;
			}
			else if (user_income > 418400) {
				a = 121505.25;
				b = 0.396;
				c = 418400;
			}
			else if (user_income == 0) {
				w = false;
			}
			else {
				y = false;
			}
		}
		else {
			x = false;
		}
		// The following two lines rounds the income tax to two decimal places.
		double incomeTax = (a + (b * (user_income - c)));
		DecimalFormat df = new DecimalFormat("#.00");
		String rounded_incomeTax = df.format(incomeTax);
		if (!y || !x || !w) {
			if (!y) {
				System.out.println("You did not enter a valid income for question 2.");
			}
			else if (!x) {
				System.out.println("You did not enter yes or no for question 1.");
			}
			else {
				System.out.println("You earn 0 taxable income. This means you don't have any taxes.");
			}
		}
		else {
			System.out.println("You must pay $" + rounded_incomeTax + " in federal income taxes.");
		}
	}
}