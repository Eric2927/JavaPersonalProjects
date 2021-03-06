package ArrayLists;
import java.util.ArrayList;
/**
 * The removeEvens method takes in an arraylist of strings and removes the strings that have an even number of characters.
 * @author eric_li
 *
 */
public class RemoveEvenLength {

	public static void main(String[] args) {
		ArrayList<String> ourList = new ArrayList<String>();
		ourList.add("Something");
		ourList.add("Odd");
		ourList.add("Even");
		ourList.add("oofu");
		System.out.println(ourList);
		removeEvens(ourList);
		System.out.println(ourList);
	}
	
	public static void removeEvens(ArrayList<String> list) {
		for (int i = list.size() - 1; i >= 0; i --) {
			if (list.get(i).length() % 2 == 0) {
				list.remove(i);
			}
		}
	}

}
