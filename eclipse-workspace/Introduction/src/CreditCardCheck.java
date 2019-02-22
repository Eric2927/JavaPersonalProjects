import java.util.Scanner;

public class CreditCardCheck {

	public static void main(String[] args) {
		Scanner myboi = new Scanner(System.in);
		System.out.print("Please enter your credit card number: ");
		long cardNumber = myboi.nextLong();
		myboi.close();
		int oddSum = 0;
		int evenSum = 0;
		int evenDigit = 0;
		int oddDigit = 0;
		String double_oddDigitString;
		String cardString = String.valueOf(cardNumber);
		String oddString = new String("");
		int numberofDigits = cardString.length();
		for (int i = 0; i < numberofDigits; i ++) {
			if (i % 2 != 0) {
				evenDigit = Character.getNumericValue(cardString.charAt(i));
				evenSum += evenDigit;
			}
			else {
				oddDigit = Character.getNumericValue(cardString.charAt(i));
				double_oddDigitString = String.valueOf(oddDigit * 2);
				// This creates a string of the digits of the doubled values of the remaining numbers.
				oddString = oddString.concat(double_oddDigitString);
			}
		}
		int numberofDigits2 = oddString.length();
		int double_oddDigit = 0;
		for (int i = 0; i < numberofDigits2; i++) {
			double_oddDigit = Character.getNumericValue(oddString.charAt(i));
			oddSum += double_oddDigit;
		}
		int checkDigit = 0;
		for (int z = 0; z <= 9; z ++) {
			if ((evenSum + oddSum - Character.getNumericValue(cardString.charAt(numberofDigits - 1)) + z) % 10 == 0) {
				checkDigit = z;
			}
		}
		if ((evenSum + oddSum) % 10 == 0) {
			System.out.println("This credit card is valid.");
		}
		else {
			System.out.println("This credit card is invalid.");
			System.out.println("The check digit that would have made this card valid is " + checkDigit);
		}
	}

}
