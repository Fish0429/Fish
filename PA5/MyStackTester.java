/**
 *
 * Name: Xingzhou Yu
 * ID: A15853336
 * Account ID: cs12sp20ahq
 * Email: xiy019@ucsd.edu
 */

import org.junit.*;
import static org.junit.Assert.*;

public class MyStackTester{

    private MyStack<Integer> capZero;
    private MyStack<Integer> empty;
    private MyStack<Integer> oneElem;
    private MyStack<Integer> capOne;
    private final int SIZE = 10;
    private final int SEVERAL = 3;
    private final int DEFAULT_CAP = 10;
    private final int EXPAND_FACTOR = 2;

    @Before
    public void setUp(){
        capZero = new MyStack<>(0);
        empty = new MyStack<>(SIZE);
        oneElem = new MyStack<>(SIZE);
        capOne = new MyStack<>(1);


        


    }

    @Test
    public void testConstructorException(){
        try{
            MyStack<Integer> illegal = new MyStack(-1);
            fail("Did not catch constructor exception");
        }
        catch(IllegalArgumentException exc){

        }
    }

    @Test
    public void testConstructorNormal(){
        MyStack<Integer> normal = new MyStack(SIZE);

        assertEquals(true, normal.empty());
    }


    @Test
    public void testEmpty(){
        assertEquals(true,  empty.empty());
        empty.empty();
        assertEquals(true,  empty.empty());
    }



      @Test
      public void testPush(){
          assertEquals(true,  empty.empty());
          empty.push(new Integer(3));
          assertEquals(new Integer(3), empty.peek());
      }


    @Test
    public void testPop(){
        assertEquals(null,  empty.pop());
        empty.push(new Integer(3));
        assertEquals(new Integer(3), empty.pop());
        assertEquals(null,  empty.pop());
    }

    @Test
    public void testPeek(){
        assertEquals(null,  empty.peek());
        empty.push(new Integer(3));
        assertEquals(new Integer(3), empty.peek());
        assertEquals(new Integer(3), empty.peek());
    }


}
