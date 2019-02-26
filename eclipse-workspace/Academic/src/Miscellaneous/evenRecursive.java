package Miscellaneous;

public class evenRecursive {

	public static void main(String[] args) {
		System.out.println(evenProducts(-1));
		System.out.println(evenProducts(0));
		System.out.println(evenProducts(1));
		System.out.println(evenProducts(2));
		System.out.println(evenProducts(3));
		System.out.println(evenProducts(4));
		System.out.println(evenProducts(8));

	}
	/**
	 * A recursive function that returns the product of the first n even integers
	 * @param n the number of even integers
	 * @return the product of the first n even integers, or 0 if n < 1
	 */
	public static int evenProducts(int n) {
		if (n < 1) {
			return 0;
		}
		if (n == 1) {
			return 2;
		}
		else {
			int loop = 0;
			int evenNumber = 0;
			while (loop < n) {
				evenNumber += 2;
				loop ++;
			}
			return evenNumber * evenProducts(n - 1);
		}
	}

}
