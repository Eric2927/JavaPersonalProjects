package Recursion;

/**
 * This class models a dataset
 * @author eric_li
 *
 */
public class Dataset {
	
	//Data
	int[] data;
	int first;
	int last;
	
	//Constructors
	/**
	 * Constructs a dataset with a given set of numbers and a set range
	 * @param values the set of numbers
	 * @param first the starting index of the range 
	 * @param last the final index of the range
	 */
	public Dataset(int[] values, int first, int last) {
		data = values;
		this.first = first;
		this.last = last;
	}
	
	/**
	 * Gets the maximum of the values in the specific range of the set of numbers (from first index to last index)
	 * @return the maximum
	 */
	public int getMaximum() {
		int[] values = new int[last - first + 1];
		for (int i = 0; i <= last - first; i ++) {
			values[i] = data[first + i];
		}
		if (values.length == 1) {
			return values[0];
		}
		else {
			values[1] = Math.max(values[0], values[1]);
			int[] updatedValues = new int[values.length - 1];
			for (int i = 0; i < updatedValues.length; i ++) {
				updatedValues[i] = values[i + 1];
			}
			Dataset a = new Dataset(updatedValues, 0, updatedValues.length - 1);
			return a.getMaximum();
		}
	}
	
	/**
	 * Gets the sum of all the values within the given range
	 * @return the sum
	 */
	public int getSum() {
		int[] values = new int[last - first + 1];
		for (int i = 0; i <= last - first; i ++) {
			values[i] = data[first + i];
		}
		if (values.length == 1) {
			return values[0];
		}
		else {
			int[] vals = new int[values.length - 1];
			for (int i = 0; i < vals.length; i ++) {
				vals[i] = values[i + 1];
			}
			Dataset b = new Dataset(vals, 0, vals.length - 1);
			return b.getSum() + values[0];
		}
	}

}
