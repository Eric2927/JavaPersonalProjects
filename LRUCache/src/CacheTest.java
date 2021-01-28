import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Code to test an <tt>LRUCache</tt> implementation.
 */
public class CacheTest {
	
	public class TestProvider<Integer,String> implements DataProvider<Integer,String> {
		
		private HashMap<Integer,String> _storedData;
		
		public TestProvider() {
			_storedData = new HashMap<Integer,String>();
		}
		
		public void add (Integer key, String value) {
			_storedData.put(key, value);
		}
		
		public String get (Integer key) {
			return _storedData.get(key);
		}
	}
	
	@Test
	public void leastRecentlyUsedIsCorrect () {
		DataProvider<Integer,String> provider = new TestProvider<Integer,String>();
		Cache<Integer,String> cache = new LRUCache<Integer,String>(provider, 4);
		
		((TestProvider) provider).add(1, "Apple");
		((TestProvider) provider).add(2, "Bean");
		((TestProvider) provider).add(3, "Citrus");
		((TestProvider) provider).add(4, "Darwin");
		((TestProvider) provider).add(5, "Elephant");
		((TestProvider) provider).add(6, "Fire");
		((TestProvider) provider).add(7, "Great");
		((TestProvider) provider).add(8, "Harvest");

		// Number of misses should start off as 0
		assertEquals(cache.getNumMisses(),0);
		
		// Checks if the cache gets the value correctly from provider
		assertEquals(cache.get(1), "Apple");
		
		// Checks if number of misses was updated
		assertEquals(cache.getNumMisses(),1);
		
		// Checks a couple more get requests to the provider (also to set up for future tests)
		assertEquals(cache.get(2), "Bean");
		assertEquals(cache.get(3), "Citrus");
		assertEquals(cache.get(4), "Darwin");
		
		// Checks if number of misses was updated correctly once more
		assertEquals(cache.getNumMisses(), 4);
		
		// Requests something that should cause the eviction of key-value pair (1, "Apple")
		assertEquals(cache.get(5), "Elephant");
		
		// Checks if requesting something that already exists in the cache works
		/*
		 * Also checks if the eviction of the least-recently used key-value pair
		 * affects the second-least-recently used key-value pair (it shouldn't)
		 */
		assertEquals(cache.get(2), "Bean");
		
		// checks if getNumMisses() works correctly, even after requesting something already in the cache
		assertEquals(cache.getNumMisses(), 5);
		
		// Checks if key-value pair (1, "Apple") was evicted
		assertEquals(cache.get(1), "Apple");
		assertEquals(cache.getNumMisses(), 6);
		
		// Adds something that isn't yet in the cache, should evict something
		assertEquals(cache.get(8), "Harvest");
		assertEquals(cache.getNumMisses(), 7);
		
		// Checks that key-value pair (3, "Citrus") was evicted by the adding of (8, "Harvest")
		assertEquals(cache.get(3), "Citrus");
		assertEquals(cache.getNumMisses(), 8);
	}
}
