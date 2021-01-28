package Miscellaneous;
import java.util.ArrayList;

public class bowlFactory {

	public static void main(String[] args) {
		ArrayList<Integer> bowlOrder = new ArrayList<Integer>();
		bowlOrder.add(5);
		bowlOrder.add(6);
		bowlOrder.add(3);
		bowlOrder.add(2);
		bowlOrder.add(4);
		bowlOrder.add(1);
		System.out.println(bowlOrder);

		boolean loop = true;
		int counter = 0;
		while (loop) {
			counter = 1;
			bowlSort(bowlOrder);
			for (int i = 0; i < bowlOrder.size() - 1; i ++) {
				if (bowlOrder.get(i) != i + 1) {
					break;
				}
				else if (bowlOrder.get(i) == i + 1) {
					counter ++;
				}
			}
			if (counter == 6) {
				loop = false;
			}
		}
	}
	
	private static void bowlSort(ArrayList<Integer> bowls) {
		int a = 0;
		int b = 0;
		for (int i = bowls.size() - 1; i > 0; i --) {
			a = bowls.get(i);
			b = bowls.get(i - 1);
			if (a < b) {
				bowls.set(i, b);
				bowls.set(i - 1, a);
			}
		}
		System.out.println(bowls);
	}

}
