import java.util.HashMap;

/**
 * An implementation of <tt>Cache</tt> that uses a least-recently-used (LRU)
 * eviction policy.
 */
public class LRUCache<T, U> implements Cache<T, U> {
	
	private DataProvider<T, U> _provider;
	private HashMap<T, SpecialNode<T,U>> _cacheData; // HashTable to store the key-value pairs, except with nodes to link togetherr
	private int _maxCapacity; // Max capacity of the cache
	private int _numMisses; // Number of cache misses
	private SpecialLinkedList<T,U> _recentlyUsed; // LinkedList that links together the values of HashMap, in order of least-recently used to most-recently used
	
	private class SpecialNode<T,U> {
		SpecialNode<T,U> _next;
		SpecialNode<T,U> _previous;
		
		// Two pieces of data are stored in these nodes, key and value
		U _value;
		T _key;
		
		public SpecialNode (T key, U value, SpecialNode<T,U> previousNode, SpecialNode<T,U> nextNode) {
			_key = key;
			_value = value;
			_previous = previousNode;
			_next = nextNode;
		}
	}
	
	private class SpecialLinkedList<T,U> {
		private SpecialNode<T,U> _head;
		private SpecialNode<T,U> _tail;
		
		public SpecialLinkedList () {
			_head = null;
			_tail = null;
		}
		
		/**
		 * Adds a node to the end of the linked list
		 * @param key the key stored in the node
		 * @param value the value stored in the node
		 */
		public void addToEnd (T key, U value) {
			if (_head == null) {
				final SpecialNode<T,U> node = new SpecialNode<T,U>(key, value, null, null);
				_head = node;
				_tail = node;
			} else {
				final SpecialNode<T,U> node = new SpecialNode<T,U>(key, value, _tail, null);
				_tail._next = node;
				_tail = node;
			}
		}
		
		/**
		 * Removes the first node in the linked list
		 */
		public void removeFirstNode () {
			_head._next._previous = null;
			_head = _head._next;
		}
		
		/**
		 * Gets the key of the first node
		 * @return the key of the first node in the linked list
		 */
		public T getFirstKey() {
			return _head._key;
		}
		
		/**
		 * Removes a node given a pointer to the node
		 * @param node the node to remove
		 * @return the value stored in the removed node (not the key)
		 */
		public U removeNode (SpecialNode<T,U> node)
		{
			// Accounts for removing first node and last node
			if (node._previous == null) {
				U value = _head._value;
				removeFirstNode();
				return value;
			}
			if (node._next == null) {
				U value = _tail._value;
				_tail._previous._next = null;
				_tail = _tail._previous;
				return value;
			}
			
			// Removing the node
			node._previous._next = node._next;
			node._next._previous = node._previous;
			U value = node._value;
			node = null;
			return value;
		}
		
	}
		
	/**
	 * @param provider the data provider to consult for a cache miss
	 * @param capacity the exact number of (key,value) pairs to store in the cache
	 */
	public LRUCache (DataProvider<T, U> provider, int capacity) {
		_provider = provider;
		_cacheData = new HashMap<T, SpecialNode<T,U>>(capacity);
		_maxCapacity = capacity;
		_recentlyUsed = new SpecialLinkedList<T,U>();
		_numMisses = 0;
	}

	/**
	 * Returns the value associated with the specified key. If the specified key does not exist,
	 * the function returns a message and null.
	 * @param key the key
	 * @return the value associated with the key
	 */
	public U get (T key) {
		if (!_cacheData.containsKey(key)) {
			U value = _provider.get(key);
			if (value == null) {
				System.out.println("The key requested does not exist!");
				return null;
			}
			if (_cacheData.size() == _maxCapacity) {
				_cacheData.remove(_recentlyUsed.getFirstKey());
				_recentlyUsed.removeFirstNode();
			}
			_numMisses ++;
			_recentlyUsed.addToEnd(key, value);
			_cacheData.put(key, _recentlyUsed._tail);
		} else {
			// removeNode() returns the value stored in the removed node
			_recentlyUsed.addToEnd(key, _recentlyUsed.removeNode(_cacheData.get(key)));
		}
		return _cacheData.get(key)._value;
	}
	
	

	/**
	 * Returns the number of cache misses since the object's instantiation.
	 * @return the number of cache misses since the object's instantiation.
	 */
	public int getNumMisses () {
		return _numMisses;
	}
}
