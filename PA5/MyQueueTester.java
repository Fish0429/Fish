/**
 *
 * Name: Xingzhou Yu
 * ID: A15853336
 * Account ID: cs12sp20ahq
 * Email: xiy019@ucsd.edu
 */

import org.junit.*;
import static org.junit.Assert.*;

public class MyQueueTester{

    private MyQueue<Integer> capZero;
    private MyQueue<Integer> empty;
    private MyQueue<Integer> oneElem;
    private MyQueue<Integer> capOne;
    private final int SIZE = 10;
    private final int SEVERAL = 3;
    private final int DEFAULT_CAP = 10;
    private final int EXPAND_FACTOR = 2;

    @Before
    public void setUp(){
        capZero = new MyQueue<>(0);
        empty = new MyQueue<>(SIZE);
        oneElem = new MyQueue<>(SIZE);
        capOne = new MyQueue<>(1);

    }

    @Test
    public void testConstructorException(){
        try{
            MyQueue<Integer> illegal = new MyQueue<>(-1);
            fail("Did not catch constructor exception");
        }
        catch(IllegalArgumentException exc){

        }
    }

    @Test
    public void testConstructorNormal(){
        MyQueue<Integer> normal = new MyQueue(SIZE);
        assertEquals(true, normal.empty());

    }


    @Test
    public void testEmpty(){
        assertEquals(true,  empty.empty());
        empty.empty();
        assertEquals(true,  empty.empty());
    }



      @Test
      public void testEnqueue(){
          assertEquals(true,  empty.empty());
          empty.enqueue(new Integer(3));
          assertEquals(new Integer(3), empty.peek());
      }


    @Test
    public void testDequeue(){
        assertEquals(null,  empty.dequeue());
        empty.enqueue(new Integer(3));
        assertEquals(new Integer(3), empty.dequeue());
        assertEquals(null,  empty.dequeue());
    }

    @Test
    public void testPeek(){
        assertEquals(null,  empty.peek());
        empty.enqueue(new Integer(3));
        assertEquals(new Integer(3), empty.peek());
        assertEquals(new Integer(3), empty.peek());
    }


}
