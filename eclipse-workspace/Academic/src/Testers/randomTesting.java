package Testers;

public class randomTesting {
	
	private Object[] _array;
	private int _numElements;
	
	public Object remove (int index)
	{
		if (index < 0 || index >= _numElements)
		{
			throw new IllegalArgumentException("Invalid Index");
		}
		
		Object[] newArray = new Object[_array.length - 1];
		Object removedItem = _array[index];
		
		for (int i = 0; i < index; i ++)
		{
			newArray[i] = _array[i];
		}
		for (int i = index + 1; i < _array.length; i ++)
		{
			newArray[i - 1] = _array[i];
		}
		
		_array = newArray;
		
		return removedItem;
	}
	
	// Test cases for removeAll (T obj) method:
	// Check that it returns the number of objects removed
	// Check that it actually removes all objects equal to obj (use ArrayList with multiple objects equal to obj)
	// Check that the array indexes are shifted accordingly
	
	
	public static void main(String[] args) {
		
		
		
	}

}
