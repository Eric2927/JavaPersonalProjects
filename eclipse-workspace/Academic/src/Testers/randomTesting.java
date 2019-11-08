package Testers;

import java.util.HashMap;

public class randomTesting {
	
	class Stack<T> {
		private static final int MAX_HEIGHT = 128;
		private T[] _stack = (T[]) new Object[MAX_HEIGHT];
		int _top = -1;
		
		void push (T o) {
			_top ++;
			_stack[_top] = o;
		}
		
		T pop() {
			T removedObject = _stack[_top];
			_stack[_top] = null;
			_top --;
			return removedObject;
		}
		
		T peek() {
			if (_top == -1) {
				return null;
			}
			return _stack[_top];
		}
		
	}
	
	public static void main(String[] args) {
		
	}

}
