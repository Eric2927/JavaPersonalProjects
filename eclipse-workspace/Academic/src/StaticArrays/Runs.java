package StaticArrays;
import java.util.Arrays;
import java.util.Random;
/**
 * This program simulates rolling a dice 20 times, listing out the results, and putting parenthesis around consecutive, repeated rolls. 
 * @author eric_li
 *
 */
public class Runs {

	public static void main(String[] args) {
		Random completelyRandom = new Random();
		int numberofRolls = 20;
		int[] diceRolls = new int[numberofRolls];
		for (int i = 0; i < numberofRolls; i ++) {
			diceRolls[i] = completelyRandom.nextInt(6) + 1;
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
		
		
	}

}
