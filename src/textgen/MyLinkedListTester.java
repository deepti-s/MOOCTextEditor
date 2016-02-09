/**
 * 
 */
package textgen;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());

		/* commenting out the prev/next pointer code because it only works if in the same package
		assertEquals(list1.node(1), list1.node(0).next);
		assertEquals(list1.head, list1.node(0).prev);
		assertEquals(list1.node(0), list1.head.next);
		assertEquals(list1.node(0), list1.node(1).prev);
		*/

		try {
			list1.remove(-1);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
			// expected, do nothing
		}
		try {
			list1.remove(2);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
			// expected, do nothing
		}

		try {
			emptyList.remove(0);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
			// expected, do nothing
		}

	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		//test adding to empty list
		emptyList.add(12345);
		verifyEmptyListAdd(12345);

		// test adding to short list with elements already in it
		shortList.add("C");
		verifyShortListAdd("C");

		// test adding to short list with elements already in it
		longerList.add(10);
		verifyLongerListAdd(10);

		try {
			list1.add(null);
		} catch (NullPointerException e) {
			//expected, do nothing
		}
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		assertEquals("Size of empty list should be 0!", 0, emptyList.size());
		assertEquals("Size of short list must be 2!", 2, shortList.size());
		assertEquals("Size of long list must be 10!", LONG_LIST_LENGTH, longerList.size());
		assertEquals("Size of list1 must be 3!", 3, list1.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		//test adding to empty list, get should throw an exception
		try {
			// less than lower index (size, i.e. 0)
			emptyList.add(-1, 999);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
			//expected, do nothing
		}

		try {
			// more than higher index (size, i.e. 0)
			emptyList.add(1, 999);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
			//expected, do nothing
		}

		// valid case of add
		emptyList.add(0, 999);
		verifyEmptyListAdd(999);

		// test short list
		try {
			shortList.add(-1, "E");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			//expected, do nothing
		}
		try {
			shortList.add(3, "E");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			//expected, do nothing
		}
		// valid case of add
		shortList.add(1, "E");
		assertEquals("E", shortList.get(1));
		assertEquals(3, shortList.size());
		assertEquals("A", shortList.get(0));
		assertEquals("B", shortList.get(2));

		/* commenting out the prev/next pointer code because it only works if in the same package
		assertEquals(shortList.node(2), shortList.node(1).next);
		assertEquals(shortList.node(0), shortList.node(1).prev);
		assertEquals(shortList.node(1), shortList.node(0).next);
		assertEquals(shortList.node(1), shortList.node(2).prev);
		*/

		// test longer list
		try {
			longerList.add(LONG_LIST_LENGTH+1, 333);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			//expected, do nothing
		}
		// valid case of add
		longerList.add(LONG_LIST_LENGTH, 333);
		assertEquals(333, longerList.get(LONG_LIST_LENGTH));
		assertEquals(11, longerList.size());
		assertEquals(9, longerList.get(LONG_LIST_LENGTH-1));

		/* commenting out the prev/next pointer code because it only works if in the same package
		assertEquals(longerList.tail, longerList.node(LONG_LIST_LENGTH).next);
		assertEquals(longerList.node(LONG_LIST_LENGTH-1), longerList.node(LONG_LIST_LENGTH).prev);
		assertEquals(longerList.node(LONG_LIST_LENGTH), longerList.node(LONG_LIST_LENGTH-1).next);
		assertEquals(longerList.node(LONG_LIST_LENGTH), longerList.tail.prev);
		*/

		// test list1
		try {
			list1.add(2, null);
		} catch (NullPointerException e) {
		//expected, do nothing
		}
		// valid case of add to list1
		list1.add(0, 16);
		assertEquals(16, list1.get(0));
		assertEquals(4, list1.size());
		assertEquals(65, list1.get(1));

		/* commenting out the prev/next pointer code because it only works if in the same package
		assertEquals(list1.node(1), list1.node(0).next);
		assertEquals(list1.head, list1.node(0).prev);
		assertEquals(list1.node(0), list1.head.next);
		assertEquals(list1.node(0), list1.node(1).prev);
		*/
	}

	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		// test set list
		try {
			list1.set(-1, 333);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
			//expected, do nothing
		}
		try {
			longerList.set(LONG_LIST_LENGTH, 333);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
			//expected, do nothing
		}
		try {
			emptyList.set(0, 1);
		} catch (IndexOutOfBoundsException e) {
		    //expected, do nothing
		}
		try {
			shortList.set(1, null);
		} catch (NullPointerException e) {
			//expected, do nothing
		}
		String element = shortList.set(1, "Z");
		assertEquals("B", element);
		assertEquals("Z", shortList.get(1));
	}

	private void verifyEmptyListAdd(Integer element) {
		assertEquals(element, emptyList.get(0));
		assertEquals(1, emptyList.size());

		/* commenting out the prev/next pointer code because it only works if in the same package
		assertEquals(emptyList.tail, emptyList.node(0).next);
		assertEquals(emptyList.head, emptyList.node(0).prev);
		assertEquals(emptyList.node(0), emptyList.head.next);
		assertEquals(emptyList.node(0), emptyList.tail.prev);
		*/
	}

	private void verifyShortListAdd(String element) {
		assertEquals(element, shortList.get(2));
		assertEquals(3, shortList.size());

		/* commenting out the prev/next pointer code because it only works if in the same package
		assertEquals(shortList.tail, shortList.node(2).next);
		assertEquals(shortList.node(1), shortList.node(2).prev);
		assertEquals(shortList.node(2), shortList.node(1).next);
		assertEquals(shortList.node(2), shortList.tail.prev);
		*/
	}

	private void verifyLongerListAdd(Integer element) {
		assertEquals(element, longerList.get(10));
		assertEquals(11, longerList.size());

		/* commenting out the prev/next pointer code because it only works if in the same package
		assertEquals(longerList.tail, longerList.node(10).next);
		assertEquals(longerList.node(9), longerList.node(10).prev);
		assertEquals(longerList.node(10), longerList.node(9).next);
		assertEquals(longerList.node(10), longerList.tail.prev);
		*/
	}
	
}
