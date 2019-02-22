import java.util.Arrays;
import java.util.Random;

public class Runs {

	public static void main(String[] args) {
		Random myman = new Random();
		int numberofRolls = 20;
		int[] diceRolls = new int[numberofRolls];
		for (int i = 0; i < numberofRolls; i ++) {
			diceRolls[i] = myman.nextInt(6) + 1;
		}
		System.out.println(Arrays.toString(diceRolls));
		String output = new String();
		for (int i = 0; i < numberofRolls; i ++) {
			if (i == 0 && diceRolls[i] == diceRolls[i + 1]) {
				output = output.concat("(" + diceRolls[i] + " ");
			}
			else if (i == numberofRolls - 1 && diceRolls[i] == diceRolls[i - 1]) {
				output = output.concat(diceRolls[i] + ")");
			}
			else if (i == 0) {
				output = output.concat(diceRolls[i] + " ");
			}
			else if (i == numberofRolls - 1) {
				output = output.concat(diceRolls[i] + " ");
			}
			else if (diceRolls[i] != diceRolls[i - 1] && diceRolls[i] == diceRolls[i + 1]) {
				output = output.concat("(" + diceRolls[i] + " ");
			}
			else if (diceRolls[i] == diceRolls[i - 1] && diceRolls[i] != diceRolls[i + 1]) {
				output = output.concat(diceRolls[i] + ")" + " ");
			}
			else {
				output = output.concat(diceRolls[i] + " ");
			}
		}
		System.out.println(output);
		
		/* int previous = 0;
		int counter = 0;
		int[] beginningPositions = new int[numberofRolls];
		int[] endingPositions = new int[numberofRolls];
		int dictionary = 0;
		for (int j = 0; j < 20; j ++) {
			if (diceRolls[j] == previous) {
				counter ++;
			}
			else if (diceRolls[j] != previous) {
				beginningPositions[dictionary] = j;
				endingPositions[dictionary] = j + counter;
				dictionary ++;
				counter = 0;
			}
			previous = diceRolls[j];
		}
		System.out.println(Arrays.toString(diceRolls));
		System.out.println(Arrays.toString(beginningPositions));
		System.out.println(Arrays.toString(endingPositions)); */
		
		
	}

}
