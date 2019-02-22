import java.util.Scanner;

public class CountingMatches {

	public static void main(String[] args) {
		Scanner myboi = new Scanner(System.in);
		System.out.print("Enter a word or phrase: ");
		String user_input = myboi.nextLine();
		int counter = 0;
		int i;
		for (i = 0; i < user_input.length(); i ++) {
			char letter = user_input.charAt(i);
			String stringLetter = Character.toString(letter);
			if (stringLetter.equals(stringLetter.toUpperCase())) {
				counter ++;
			}
			else {
				// Nothing
			}
		}
		myboi.close();
		System.out.println("The input has " + counter + " capital letters.");
	}

}
