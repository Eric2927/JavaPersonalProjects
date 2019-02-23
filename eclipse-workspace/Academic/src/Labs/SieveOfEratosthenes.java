package Labs;
import java.util.Scanner;
import java.text.DecimalFormat;

public class SieveOfEratosthenes  {
	
	public static void main(String args[]) {
		System.out.println("\nSieve of Eratosthenes\n");
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the primes upper bound  ===>>  ");
		final int MAX = input.nextInt();
		boolean primes[] = new boolean[MAX];
		computePrimes(primes);
		displayPrimes(primes);
		input.close();
	}
	
	public static void computePrimes(boolean primeArray[]) {
		for (int i = 2; i < (int)Math.sqrt(primeArray.length); i ++) {
			if (!primeArray[i]) {
				for (int j = i * 2; j < primeArray.length; j += i) {
					primeArray[j] = true;
				}
			}
		}
	}
	
	
	public static void displayPrimes(boolean primeArray[]) {
		DecimalFormat df = new DecimalFormat("0000");
		int counter = 0;
		for (int i = 2; i < primeArray.length; i ++) {
			if (!primeArray[i]) {
				counter ++;
				if (counter % 16 == 0) {
					System.out.print(df.format(i) + "\n");
				}
				else {
					System.out.print(df.format(i) + " ");
				}
			}
		}
	}

}
