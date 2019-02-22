// Lab03
// The Mortgage Payment Program
// monthlyPayment, totalPayment are actually equal to the respective payments times 100, in order to faciliate the rounding.
public class MortgagePayment {
	public static void main(String args[]) {
		System.out.println("Lab03: The Mortgage Payment Program\n");
		double principal = 259000;
		double annualRate = 5.75;
		double numYears = 30;
		double annualInterest = annualRate/100;
		double monthlyInterest = annualInterest/12;
		double numMonths = numYears*12;
		double numerator = monthlyInterest * Math.pow(1 + monthlyInterest, numMonths);
		double denominator = Math.pow(1 + monthlyInterest, numMonths) - 1;
		double monthlyPayment = Math.round((numerator/denominator) * principal*100);
		double roundedMonthlyPayment = monthlyPayment/100;
		double totalPayment = Math.round(roundedMonthlyPayment * numMonths * 100);
		double roundedTotalPayment = totalPayment/100;
		double totalInterest = Math.round((roundedTotalPayment - principal)*100);
		double roundedtotalInterest = totalInterest/100;
		System.out.println("Principal: $" + principal);
		System.out.println("Annual Rate: " + annualRate + "%");
		System.out.println("Number of Years: " + numYears);
		System.out.println("Monthly Payment: $" + roundedMonthlyPayment);
		System.out.println("Total Payment: $" + roundedTotalPayment);
		System.out.println("Total Interest: $" + roundedtotalInterest);
	}
}