package Testers;

import java.util.*;

public class randomTesting {
	
	class Person {
		Person _mom, _dad;
		List<Person> _kids;
		
		public boolean hasParent(Person parent) {
			return parent == _mom || parent == _dad;
		}
	}
	
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
	
	class BSTNode<T extends Comparable<T>> {
		BSTNode<T> _parent, _left, _right;
		T _value;
	}

	<T extends Comparable<T>> boolean contains(BSTNode<T> root, T value) {
		if (root._value.compareTo(value) == 0) {
			return true;
		}
		else if (root._value.compareTo(value) < 1) {
			if (root._right == null)
			{
				return false;
			}
			return contains(root._right, value);
		}
		else {
			if (root._left == null) {
				return false;
			}
			return contains(root._left, value);
		}
	}
	
	
	
	public static void main(String[] args) {	
		
	}

}
