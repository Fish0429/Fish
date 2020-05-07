/**
 * Class RockPaperScissors. Plays repeated games of Rock Paper Scissors
 * with a user.
 *
 * Name: Xingzhou Yu
 * ID: A15853336
 * Account ID: cs12sp20ahq
 * Email: xiy019@ucsd.edu
 */


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Arrays;


import org.junit.*;

public class MyArrayListTester {

  static final int DEFAULT_CAPACITY = 10;
  static final int MY_CAPACITY = 3;

  Object[] arr = new Object[10];
  Object[] arr2 = null;
  Integer[] arrInts1 = {1,2,3};
  Integer[] arrInts = {1,2,3};

  private MyArrayList list1, list2, list3, list4, list5, list6, list7;

  @Before
  public void setUp() throws Exception {
    list1 = new MyArrayList();
    list2 = new MyArrayList(DEFAULT_CAPACITY);
    list3 = new MyArrayList(MY_CAPACITY);
    list4 = new MyArrayList(arr);
    list5 = new MyArrayList<Integer>(arrInts);
    list6 = new MyArrayList(arr2);
    list7 = new MyArrayList<Integer>(arrInts1);
  }

  @Test
  public void testInvalidConstructor() {
    try {
      MyArrayList<Integer> invalid = new MyArrayList<Integer>(-1);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // Pass
    }
  }

  @Test
  public void testDefaultSize() {
    assertEquals("Check size for default constructor", 0, list1.size());
    assertEquals("Check size for constructor with given capacity of 10", 0, list2.size());
    assertEquals("Check size for constructor with given capacity of 3", 0, list3.size());
    assertEquals("Check size for constructor with given array", 10, list4.size());
    assertEquals("Check size for constructor with given int array", 3, list5.size());
    assertEquals("Check size for constructor with given null array", 0, list6.size());
  }

  @Test
  public void testCheckCapacity() {
    list1.checkCapacity(13);
    assertEquals("Check doubling the capacity", 20, list1.getCapacity());
    list1.checkCapacity(2048);
    assertEquals("Check directly setting the capacity to more than double", 2048, list1.getCapacity());
    list1.checkCapacity(13);
    assertEquals("Check not changing the capacity", 2048, list1.getCapacity());
  }

  @Test
  public void testInitialCapacity() {
    assertEquals("Check default capacity", DEFAULT_CAPACITY, list1.getCapacity());
    assertEquals("Check given capacity", MY_CAPACITY, list3.getCapacity());
  }

  @Test
  public void testInsert() {
    int[] nums = {5,3,4};
    list5.insert(1,nums[0]);
    assertEquals("Check insert a valid index", 4, list5.size());

    try {
      list5.insert(-1,nums[0]);
      fail("Expected IndexOutOfBoundsException");
    } catch (IndexOutOfBoundsException e) {
      // Pass
    }

    try {
      list5.insert(10,nums[0]);
      fail("Expected IndexOutOfBoundsException");
    } catch (IndexOutOfBoundsException e) {
      // Pass
    }

    list5.insert(4,nums[0]);
    assertEquals("Check insert at size", 5, list5.size());
  }

  @Test
  public void testAppend() {
    int[] nums = {2,4};
    list1.append(nums[0]);
    assertEquals("Check that append increments size", 1, list1.size());
    list1.append(nums[1]);
    assertEquals("Check that capacity is unchanged", DEFAULT_CAPACITY, list1.getCapacity());
  }

  @Test
  public void testPrepend() {
    int[] nums = {2,4};
    list2.prepend(nums[0]);
    assertEquals("Check that prepend increments size", 1, list2.size());
    list2.prepend(nums[1]);
    assertEquals("Check that capacity is unchanged", DEFAULT_CAPACITY, list2.getCapacity());
  }

  @Test
  public void testGet() {
    assertEquals("Check get valid index", 2, list5.get(1));

    try {
      list5.get(-1);
      fail("Expected IndexOutOfBoundsException");
    } catch (IndexOutOfBoundsException e) {
      // Pass
    }

    try {
      list5.get(20);
      fail("Expected IndexOutOfBoundsException");
    } catch (IndexOutOfBoundsException e) {
      // Pass
    }
  }

  @Test
  public void testSet() {
    list5.set(0,2);
    assertEquals("Check set valid index", 2, list5.get(0));

    try {
      list5.set(-1,2);
      fail("Expected IndexOutOfBoundsException");
    } catch (IndexOutOfBoundsException e) {
      // Pass
    }

    try {
      list5.set(20,2);
      fail("Expected IndexOutOfBoundsException");
    } catch (IndexOutOfBoundsException e) {
      // Pass
    }
  }

  @Test
  public void testRemove() {
    list5.remove(0);
    assertEquals("Check set valid index", 2, list5.get(0));

    try {
      list5.remove(-1);
      fail("Expected IndexOutOfBoundsException");
    } catch (IndexOutOfBoundsException e) {
      // Pass
    }

    try {
      list5.remove(20);
      fail("Expected IndexOutOfBoundsException");
    } catch (IndexOutOfBoundsException e) {
      // Pass
    }
  }


  @Test
  public void testTrimToSize() {
    list2.trimToSize();
    assertEquals("Check trims the capacity", list2.size(), list2.getCapacity());

    list7.trimToSize();
    assertEquals("Check trims the capacity", list7.size(), list7.getCapacity());
  }

}
