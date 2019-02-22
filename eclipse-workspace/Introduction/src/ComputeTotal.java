import java.util.Scanner;

public class ComputeTotal {

	public static void main(String[] args) {
		Scanner myboi = new Scanner(System.in);
		// Assumes that user will not enter a non-integer value.
		System.out.print("Enter an integer. ");
		int user_input = myboi.nextInt();
		int sum = 0;
		for (int i = 1; i <= user_input; i ++) {
			sum += i;
		}
		myboi.close();
		System.out.println(sum);
	}

}
