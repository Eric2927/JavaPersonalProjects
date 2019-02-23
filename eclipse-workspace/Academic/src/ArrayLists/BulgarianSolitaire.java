package ArrayLists;
import java.util.ArrayList;
import java.util.Random;
/**
 * This program models a game of Bulgarian solitaire with a randomly generated starting configuration of cards.
 * @author eric_li
 *
 */
public class BulgarianSolitaire {

	public static void main(String[] args) {
		// N has to be a triangular number. Otherwise, this program will fail (as in it will run an infinite loop).
		int N = 45;
		Random completelyRandom = new Random();
		ArrayList<Integer> cardPiles = new ArrayList<Integer>();
		boolean loop = true;
		int a = 0;
		int b = 0;
		int sum = 0;
		while (loop) {
			a = completelyRandom.nextInt(N) + 1;
			cardPiles.add(a);
			sum = 0;
			for (int i = 0; i < cardPiles.size(); i ++) {
				sum += cardPiles.get(i);
				if (sum > N) {
					b = sum - cardPiles.get(i);
					cardPiles.set(i, N - b);
					loop = false;
				}
				else if (sum == N) {
					loop = false;
				}
			}
		}
		System.out.println(cardPiles);
		boolean loop2 = true;
		int sum2 = 0;
		// The following code assumes that the starting configuration does not satisfy the ending requirements.
		while (loop2) {
			nextStep(cardPiles);
			sum2 = 0;
			for (Integer i = 1; true; i ++) {
				sum2 += i;
				if (sum2 == N) {
					loop2 = false;
					break;
				}
				else if (!cardPiles.contains(i)) {
					break;
				}
			}
		}
		
	}
	// The following method is used to compute each step (remove one card from each pile, add it to a new pile) and then print it
	private static void nextStep(ArrayList<Integer> list) {
		Integer newPile = 0;
		for (int i = list.size() - 1; i >= 0; i--) {
			list.set(i, list.get(i) - 1);
			newPile ++;
			if (list.get(i) == 0) {
				list.remove(i);
			}
		}
		list.add(newPile);
		System.out.println(list);
	}

}
