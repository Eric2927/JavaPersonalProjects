import java.util.ArrayList;

public class FilterRange {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		filterRange(list, 1, 3);
		System.out.println(list);
	}
	
	public static void filterRange(ArrayList<Integer> numberSet, int a, int b) {
		for (int i = numberSet.size() - 1; i >= 0; i --) {
			if (numberSet.get(i) >= a && numberSet.get(i) <= b) {
				numberSet.remove(i);
			}
		}
	}

}
