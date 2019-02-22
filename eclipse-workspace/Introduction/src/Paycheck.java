/* This program determines how much pay an employee receives each week based on hours worked */

import java.util.Scanner;

public class Paycheck {

	public static void main(String[] args) {
		double weekly_pay = 0;
		double overtime_pay = 0;
		Scanner myboi = new Scanner(System.in);
		System.out.println("What is the pay rate (per hour)?");
		double pay_rate = myboi.nextDouble();
		System.out.println("How many hours do you work each week?");
		double hours_worked = myboi.nextDouble();
		myboi.close();
		if (hours_worked > 40) {
			weekly_pay = 40 * pay_rate;
			overtime_pay = (hours_worked - 40) * 1.5 * pay_rate;
			weekly_pay = weekly_pay + overtime_pay;
		}
		else if (hours_worked <= 40) {
			weekly_pay = hours_worked * pay_rate;
		}
		System.out.println("Your weekly pay is: $" + weekly_pay);
	}

}
