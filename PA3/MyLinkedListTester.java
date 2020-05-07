import org.junit.*;
import static org.junit.Assert.*;
import java.util.LinkedList;
import java.util.ListIterator;


/**
 *  Title: class MyLinkedListTester
 *  @author Xingzhou Yu
 *  @version 3.0 05-April-2015
 *  Student ID: A15853336
 *  CSE12 Account: cs12sp20ahq
 *  Date: 04/19/2020
 *
 *  Description: The file is created to test MyLinkedList
 * */

public class MyLinkedListTester
{
	private MyLinkedList<Integer> empty ;
	private MyLinkedList<Integer> one ;
	private MyLinkedList<Integer> several ;
	private MyLinkedList<String>  slist ;
	static final int DIM = 5;
	static final int FIBMAX = 30;
	private ListIterator<Integer> iterTest;

	/**
	 * Standard Test Fixture. An empty list, a list with one entry (0) and
	 * a list with several entries (0,1,2)
	 */
	@Before
	public void setUp()
	{
		empty = new MyLinkedList<Integer>();
		one = new MyLinkedList<Integer>();
		one.add(0,new Integer(0));
		several = new MyLinkedList<Integer>() ;
		// List: 1,2,3,...,Dim
		for (int i = DIM; i > 0; i--)
			several.add(0,new Integer(i));

		// List: "First","Last"
		slist = new MyLinkedList<String>();
		slist.add(0,"First");
		slist.add(1,"Last");
	}

	/** Test if first node of the lists are correct */
	@Test
	public void testGetFirst()
	{
		assertEquals("Check 0",new Integer(0),one.get(0)) ;
		assertEquals("Check 0",new Integer(1),several.get(0)) ;
	}

	/** Test if size of lists are correct */
	@Test
	public void testListSize()
	{
		assertEquals("Check Empty Size",0,empty.size()) ;
		assertEquals("Check One Size",1,one.size()) ;
		assertEquals("Check Several Size",DIM,several.size()) ;
	}

	/** Test setting a specific entry */
	@Test
	public void testSet()
	{
		slist.set(1,"Final");
		assertEquals("Setting specific value", "Final",slist.get(1));
	}

	/** Test isEmpty */
	@Test
	public void testEmpty()
	{
		assertTrue("empty is empty",empty.isEmpty()) ;
		assertTrue("one is not empty",!one.isEmpty()) ;
		assertTrue("several is not empty",!several.isEmpty()) ;
	}

	/** Test out of bounds exception on get */
	@Test
	public void testGetException()
	{
		try
		{
			empty.get(0);
			// This is how you can test when an exception is supposed
			// to be thrown
			fail("Should have generated an exception");
		}
		catch(IndexOutOfBoundsException e)
		{
			//  normal
		}
	}

	/** Test iterator on empty list and several list */
	@Test
	public void testIterator()
	{
		int counter = 0 ;
		ListIterator<Integer> iter;
		for (iter = empty.listIterator() ; iter.hasNext(); )
		{
			iter.hasPrevious();
			fail("Iterating empty list and found element") ;
		}
		counter = 0 ;
		for (iter = several.listIterator() ; iter.hasNext(); iter.next()){
			iter.hasPrevious();
			counter++;
		}

		assertEquals("Iterator several count", counter, DIM);

		//add
		iter = several.listIterator();
		iter.add(new Integer(1));
		assertEquals(new Integer(1), iter.previous());

		try
		{
			iter.add(null);
			fail("Should have generated an exception");
		}
		catch(NullPointerException e)
		{
			//  normal
		}


	}

	/* Add your own tests here */

	//////////////////////////////////////////
	//Begin testing on List Iterator methods//
	/////////////////////////////////////////


	/** Test listiterator hasnext method while it goes through the empty
	 * and one list
	 */
	@Test
	public void testIteratorHasNext() {

		ListIterator<Integer> iter = empty.listIterator();
		ListIterator<Integer> iter1 = one.listIterator();

		assertTrue(!iter.hasNext());
		assertTrue(iter1.hasNext());
	}

	/** Test listiterator next method */
	@Test
	public void testIteratorNext() {

		iterTest = several.listIterator();

		assertEquals(new Integer(1),iterTest.next());
		assertEquals(new Integer(2),iterTest.next());
		assertEquals(new Integer(3),iterTest.next());
		assertEquals(new Integer(4),iterTest.next());
		assertEquals(new Integer(5),iterTest.next());
	}

	/** Test nextIndex method of list iterator */
	@Test
	public void testIteratorNextIndex() {
		iterTest = several.listIterator();

		//Test the nextIndex method at the start of and end
		//of the list as well as middle of the list
		assertEquals(0, iterTest.nextIndex());

		iterTest.next();
		iterTest.next();
		iterTest.next();

		assertEquals(3, iterTest.nextIndex());

		iterTest.next();
		iterTest.next();

		assertEquals(5, iterTest.nextIndex());
	}

	/** Test the remove method of list iterator */
	@Test
	public void testIteratorRemove() {
		iterTest = several.listIterator();

		//Test whether removes method work after next method
		iterTest.next();
		iterTest.next();
		iterTest.remove();

		assertEquals(new Integer(1), iterTest.previous());

		//Test whether remove method work after previous method
		iterTest.next();
		iterTest.next();
		iterTest.previous();
		iterTest.remove();

		assertEquals(new Integer(4), iterTest.next());
	}

	/* Add your own tests here */

	@Test
	public void testNode() {
		//Construct nodes by passing in data
		MyLinkedList.Node node= new MyLinkedList().new Node(new Integer(1));
		assertEquals(new Integer(1), node.getElement());

		//Construct a node by passing in null as the argument
		MyLinkedList.Node node2= new MyLinkedList().new Node(null);
		assertEquals(null, node2.getElement());

		MyLinkedList.Node node3= new MyLinkedList().new Node(new Integer(3));
		MyLinkedList.Node node4= new MyLinkedList().new Node(new Integer(4));
		MyLinkedList.Node node5= new MyLinkedList().new Node(new Integer(5));

		//Calls getElement on node after its element is changed
		node.setElement(new Integer(3));
		assertEquals(new Integer(3), node.getElement());

		//Calls getNext on a single new node
		assertEquals(null, node.getNext());

		//Calls getNext on a node pointing to another node
		node.setNext(node3);
		assertEquals(node3, node.getNext());

		//Calls getPrev on a single new node
		assertEquals(null, node4.getNext());

		//Calls getPrev on a node pointing to another node
		node4.setPrev(node5);
		assertEquals(node5, node4.getPrev());

		//Calls setElement with an int argument
		node.setElement(new Integer(1));
		assertEquals(new Integer(1), node.getElement());

		//Calls setElement with a String argument
		node.setElement(new String("Fish"));
		assertEquals(new String("Fish"), node.getElement());

		//Calls setElement with a null argument
		node.setElement(null);
		assertEquals(null, node.getElement());

		//Calls setNext on a node with a next already
		node.setNext(node4);
		assertEquals(node4, node.getNext());

		//Calls setPrev on a node with a prev already
		node4.setPrev(node3);
		assertEquals(node3, node4.getPrev());
 	}

	@Test
	public void testMyLinkedList() {

		MyLinkedList<String> stringList = new MyLinkedList<String>();
		MyLinkedList<String> stringList1 = new MyLinkedList<String>();
		MyLinkedList<String> stringList2 = new MyLinkedList<String>();
		MyLinkedList<String> stringList3 = new MyLinkedList<String>();

		//Add a string to an empty string MyLinkedList
		stringList.add(new String("Fish"));
		assertEquals(new String("Fish"), stringList.get(0));

		//Add a string to a one element string MyLinkedList
		stringList.add(new String("Apple"));
		assertEquals(new String("Apple"), stringList.get(1));

		//Add many strings to an empty string MyLinkedList
		//Add random ints and make sure each add returns true
		for(int i = 0; i <=9; i++){
			boolean flag = stringList1.add(new String(""+i));
			assertEquals(new String(""+i), stringList1.get(i));
			assertEquals(true, flag);
		}

		//add null
		try
		{
			stringList.add(null);
			fail("Should have generated an exception");
		}
		catch(NullPointerException e)
		{
			//  normal
		}

		//Add a string to an empty string MyLinkedList using index 0
		stringList2.add(0, new String("Fish"));
		assertEquals(new String("Fish"), stringList.get(0));

		//Add a string to a three element string MyLinkedList using index 4
		stringList2.add(0, new String("Fish"));
		stringList2.add(0, new String("Fish"));
		try
		{
			stringList2.add(4, new String("Fish"));
			fail("Should have generated an exception");
		}
		catch(IndexOutOfBoundsException e)
		{
			//  normal
		}

		//Add a string to a three element string MyLinkedList using index -1
		try
		{
			stringList2.add(-1, new String("Fish"));
			fail("Should have generated an exception");
		}
		catch(IndexOutOfBoundsException e)
		{
			//  normal
		}

		//Add a string to a two element string MyLinkedList
		stringList.add(1,new String("java"));
		assertEquals(new String("java"), stringList.get(1));

		//Add null with index
		try
		{
			stringList.add(0,null);
			fail("Should have generated an exception");
		}
		catch(NullPointerException e)
		{
			//  normal
		}

		//Call clear on a list with 3 elements
		//Call isEmpty on a list with 3 elements
		assertEquals(3, stringList.size());
		assertEquals(false, stringList.isEmpty());
		stringList.clear();
		assertEquals(0, stringList.size());


		//Call clear on a list with 1 element
		stringList.add(new String("Fish"));
		assertEquals(1, stringList.size());
		assertEquals(false, stringList.isEmpty());
		stringList.clear();
		assertEquals(0, stringList.size());

		//Call clear on a list with 0 elements
		assertEquals(0, stringList.size());
		assertEquals(true, stringList.isEmpty());
		stringList.clear();
		assertEquals(0, stringList.size());

		//Make sure head points to tail and their elements are null initially
		assertEquals(stringList3.tail, stringList3.head.getNext());
		assertEquals(null, stringList3.head.getElement());
		assertEquals(null, stringList3.tail.getElement());
		//Make sure initial nelems is 0
		assertEquals(0, stringList3.size());

		//Remove an int in an Integer MyLinkedList
		assertEquals(several.get(1),several.remove(1));

		//Remove the first int in an Integer MLL
		assertEquals(several.get(0),several.remove(0));

		//Remove the last int in an Integer MLL
		assertEquals(several.get(several.size()-1),several.remove(several.size()-1));

		//Remove index 0 in empty list
		try
		{
			stringList3.remove(0);
			fail("Should have generated an exception");
		}
		catch(IndexOutOfBoundsException e)
		{
			//  normal
		}

		stringList3.add(new String("Fish"));
		stringList3.add(new String("Fish"));
		stringList3.add(new String("Fish"));

		try
		{
			stringList3.remove(3);
			fail("Should have generated an exception");
		}
		catch(IndexOutOfBoundsException e)
		{
			//  normal
		}

		try
		{
			stringList3.remove(-1);
			fail("Should have generated an exception");
		}
		catch(IndexOutOfBoundsException e)
		{
			//  normal
		}

		//set
		several.set(0,new Integer(1025));
		assertEquals(new Integer(1025),several.get(0));
		several.set(several.size()-1,new Integer(1025));
		assertEquals(new Integer(1025),several.get(several.size()-1));

		//Set index 0 in empty list
		try
		{
			stringList.set(0,new String("Fish"));
			fail("Should have generated an exception");
		}
		catch(IndexOutOfBoundsException e)
		{
			//  normal
		}

		try
		{
			stringList3.set(3,new String("Fish"));
			fail("Should have generated an exception");
		}
		catch(IndexOutOfBoundsException e)
		{
			//  normal
		}

		try
		{
			stringList3.set(-1,new String("Fish"));
			fail("Should have generated an exception");
		}
		catch(IndexOutOfBoundsException e)
		{
			//  normal
		}

		try
		{
			several.set(0, null);
			fail("Should have generated an exception");
		}
		catch(NullPointerException e)
		{
			//  normal
		}
	}















}
