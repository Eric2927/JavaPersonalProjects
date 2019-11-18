package Testers;

import java.util.HashMap;
import java.util.Iterator;

public class randomTesting {
	
	class MyArrayList<T> implements Iterable<T> {
		private T[] _array;
		private int _numElements;
		
		private class ArrayListIterator<T> implements Iterator<T> {
			int _index = 0;
			
			public boolean hasNext() {
				return _array[_index + 1] != null;
			}
			
			public T next() {
				return (T) _array[_index ++];
			}
			
			public void remove () {
				for (int i = _index; i < _numElements; i ++) {
					_array[_index - 1] = _array[_index];
				}
				_index --;
				_numElements --;
			}
		}
		
		public Iterator<T> iterator() {
			return new ArrayListIterator<T>();
		}
	}
	
	public static void main(String[] args) {
		String hi = "1";
		System.out.println(Integer.valueOf(hi));
	}

}
