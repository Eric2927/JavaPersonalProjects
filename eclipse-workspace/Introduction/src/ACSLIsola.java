import java.util.ArrayList;

public class ACSLIsola {

	public static void main(String[] args) {
		String input = new String("46, 4, 45, 47, 38, 40, 0");
		input = input.replaceAll(",", "");
		int startIndex = 0;
		int counter = 0;
		int plusPosition = 0;
		int crossPosition = 0;
		ArrayList<Integer> barriers = new ArrayList<Integer>();
		for (int i = 0; i < input.length(); i ++) {
			if (input.charAt(i) == ' ') {
				if (counter == 0) {
					plusPosition = Integer.parseInt(input.substring(startIndex, i));
				}
				else if (counter == 1) {
					crossPosition = Integer.parseInt(input.substring(startIndex, i));
				}
				else {
					barriers.add(Integer.parseInt(input.substring(startIndex, i)));
				}
				counter ++;
				startIndex = i + 1;
			}
		}
		
		ArrayList<Integer> topRow = new ArrayList<Integer>(43);
		topRow.add(44);
		topRow.add(45);
		topRow.add(46);
		topRow.add(47);
		topRow.add(48);
		topRow.add(49);
		topRow.add(49);
		topRow.add(50);
		topRow.add(51);
		topRow.add(52);
		topRow.add(53);
		topRow.add(54);
		topRow.add(55);
		ArrayList<Integer> bottomRow = new ArrayList<Integer>(1);
		bottomRow.add(2);
		bottomRow.add(3);
		bottomRow.add(4);
		bottomRow.add(5);
		bottomRow.add(6);
		bottomRow.add(7);
		bottomRow.add(0);
		bottomRow.add(-1);
		bottomRow.add(-2);
		bottomRow.add(-3);
		bottomRow.add(-4);
		bottomRow.add(-5);
		bottomRow.add(-6);
		ArrayList<Integer> leftColumn = new ArrayList<Integer>(1);
		leftColumn.add(8);
		leftColumn.add(15);
		leftColumn.add(22);
		leftColumn.add(29);
		leftColumn.add(36);
		leftColumn.add(43);
		leftColumn.add(0);
		leftColumn.add(7);
		leftColumn.add(14);
		leftColumn.add(21);
		leftColumn.add(28);
		leftColumn.add(35);
		leftColumn.add(42);
		ArrayList<Integer> rightColumn = new ArrayList<Integer>(7);
		rightColumn.add(14);
		rightColumn.add(21);
		rightColumn.add(28);
		rightColumn.add(35);
		rightColumn.add(42);
		rightColumn.add(49);
		rightColumn.add(8);
		rightColumn.add(15);
		rightColumn.add(22);
		rightColumn.add(29);
		rightColumn.add(36);
		rightColumn.add(43);
		rightColumn.add(50);
		ArrayList<Integer> blockingPotential = new ArrayList<Integer>();
		if (!leftColumn.contains(plusPosition - 1)) {
			blockingPotential.add(plusPosition - 1);
		}
		if (!rightColumn.contains(plusPosition + 1)) {
			blockingPotential.add(plusPosition + 1);
		}
		if (!bottomRow.contains(plusPosition - 7)) {
			blockingPotential.add(plusPosition - 7);
		}
		if (!topRow.contains(plusPosition + 7)) {
			blockingPotential.add(plusPosition + 7);
		}
		if (!bottomRow.contains(plusPosition - 8) && !leftColumn.contains(plusPosition - 8)) {
			blockingPotential.add(plusPosition - 8);
		}
		if (!topRow.contains(plusPosition + 8) && !rightColumn.contains(plusPosition + 8)) {
			blockingPotential.add(plusPosition + 8);
		}
		if (!bottomRow.contains(plusPosition - 6) && !rightColumn.contains(plusPosition - 6)) {
			blockingPotential.add(plusPosition - 6);
		}
		if (!topRow.contains(plusPosition + 6) && !leftColumn.contains(plusPosition + 6)) {
			blockingPotential.add(plusPosition + 6);
		}
		

	}

}
