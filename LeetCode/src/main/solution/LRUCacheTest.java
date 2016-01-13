package solution;

import static org.junit.Assert.*;

import org.junit.Test;

public class LRUCacheTest {

	@Test
	public void testGetOne() {
		LRUCache c = new LRUCache(1);
		assertEquals(-1, c.get(1));
		c.set(1, 1);
		assertEquals(1, c.get(1));
		assertEquals(-1, c.get(2));
	}

	@Test
	public void testGetMore() {
		LRUCache c = new LRUCache(2);
		assertEquals(-1, c.get(1));
		c.set(1, 1);
		c.set(2, 2);
		assertEquals(1, c.get(1));
		assertEquals(2, c.get(2));
		c.set(3, 3);
		assertEquals(3, c.get(3));
		assertEquals(2, c.get(2));
		assertEquals(-1, c.get(1));
	}

	@Test
	public void testGet1() {
		LRUCache c = new LRUCache(2);
		c.set(2, 1);
		c.set(2, 2);
		assertEquals(2, c.get(2));
		c.set(1, 1);
		c.set(4, 1);
		assertEquals(-1, c.get(2));
		//2,[set(2,1),set(2,2),get(2),set(1,1),set(4,1),get(2)]
	}


	@Test
	public void testGet2() {
		LRUCache c = new LRUCache(2);
		c.set(2, 1);
		c.set(1, 1);
		c.set(2, 3);
		c.set(4, 1);
		assertEquals(-1, c.get(1));
		assertEquals(3, c.get(2));
		//2,[set(2,1),set(1,1),set(2,3),set(4,1),get(1),get(2)]
	}
}
