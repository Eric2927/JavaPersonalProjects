package ArrayLists;
import java.util.ArrayList;
/**
 * The toFront method takes in an arraylist of integers and moves the smallest integer to the front of the arraylist.
 * @author eric_li
 *
 */
public class MintoFront {

	public static void main(String[] args) {
		ArrayList<Integer> ourList = new ArrayList<Integer>();
		ourList.add(5);
		ourList.add(13);
		ourList.add(53);
		ourList.add(27);
		ourList.add(1);
		System.out.println(ourList);
		toFront(ourList);
		System.out.println(ourList);
	}
	
	public static void toFront(ArrayList<Integer> list) {
		Integer min = 0;
		for (int i = 0; i < list.size() - 1; i ++) {
			if (list.get(i) < list.get(i + 1)) {
				min = list.get(i);
			}
			else if (list.get(i) > list.get(i + 1)) {
				min = list.get(i + 1);
			}
		}
		list.remove(min);
		list.add(0, min);

	}

}
